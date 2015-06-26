package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.entity.*;
import cm.service.*;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
public class PaperController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private ManageService manageService;
	@Autowired
	private StateService stateService;
	@Autowired
	private ApplicationService applicationService;


	@RequestMapping(value = "student/paper/show/{id}",method = RequestMethod.GET)
	public String studentPaper(@Param UUID id,Model model,HttpSession session) {
		Student student = studentService.get(id);
		Application application = applicationService.find(student.getCuState(), student);
		if (application.getExamState()) {
			model.addAttribute("answer", application.getAnswer());
			model.addAttribute("paper", application.getPaper());
		} else {
			model.addAttribute("examState", application.getExamState());
		}
		return "student/paper_finished";
	}

	@RequestMapping(value = "teacher/papers",method = RequestMethod.GET)
	public String list(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		if (teacher != null) {
			State custate = stateService.get(teacher.getCuState().getId());
			List<Paper> papers = paperService.findByState(custate);
			model.addAttribute("papers", papers);
		}
		return "paper/list";
	}

	@RequestMapping(value = "teacher/paper/add",method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Paper());
		return "paper/addUI";
	}

	@RequestMapping(value = "teacher/paper/add",method = RequestMethod.POST)
	public String add(@Valid Paper paper,BindingResult br,HttpSession session) {
		if (br.hasErrors())
			return "addUI";
		Teacher teacher = (Teacher) session.getAttribute("user");
		if (teacher != null) {
			State custate = teacher.getCuState();
			paper.setState(custate);
			paper.setId(UUID.randomUUID());
			paper.setUpdatedDate(Calendar.getInstance());
			paperService.save(paper);

		}
		return "redirect:/teacher/papers";
	}

	@RequestMapping(value = "teacher/paper/update/{id}",method = RequestMethod.GET)
	public String edit(@PathVariable String id,Model model) {
		Paper paper = paperService.get(id);
		model.addAttribute("paper", paper);
		return "paper/editUI";
	}

	@RequestMapping(value = "teacher/paper/update",method = RequestMethod.POST)
	public String edit(@Valid Paper p,BindingResult br) {
		if (br.hasErrors())
			return "update";
		Paper paper = paperService.get(p.getId());
		paper.setName(p.getName());
		paper.setAnswer(p.getAnswer());
		paper.setProblem(p.getProblem());
		paper.setUpdatedDate(Calendar.getInstance());
		paperService.update(paper);
		return "redirect:/teacher/papers";
	}
	@RequestMapping(value = "teacher/paper/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable String id) {
		paperService.delete(id);
		return "redirect:/teacher/papers";
	}
	@RequestMapping(value = "student/paper/get",method = RequestMethod.GET)
	public String getPaper(Model model,HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		State state = stateService.get(student.getCuState().getId());
		Application application = applicationService.find(state, student);
		List<Paper> papers = new ArrayList<Paper>();
		for (Paper paper : state.getPapers()) {
			if (paper.getType())
				papers.add(paper);
		}
		Paper paper = null;
		if (papers.size() > 0) {
			int index = (int) (Math.random() * papers.size());
			paper = papers.get(index);
			application.setPaper(paper);
			application.setAnswer(null);
			applicationService.update(application);
			model.addAttribute("problem", paper.getProblem());
		}else{
			model.addAttribute("info", "目前题库中还没有题目，请联系任课老师");
			return "public/error";
		}
		return "student/paper_unfinish";
	}

	/**
	 * 提交试卷
	 *
	 * @return
	 */
	@RequestMapping(value = "student/paper/submit",method = RequestMethod.POST)
	public String submitPaper(String answer,Model model,HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		State state = student.getCuState();
		Application application = applicationService.find(state, student);
		if (state.getExamAble()) {
			application.setAnswer(answer);
			application.setExamState(true);
			applicationService.update(application);
		} else {
			return "redirect:/student/paper/get";
		}
		model.addAttribute(application);
		return "student/paper_finished";
	}

	@RequestMapping(value = "teacher/paper/show/{id}",method = RequestMethod.GET)
	public String paperShow(@PathVariable UUID id,Model model) {
		Application application = applicationService.get(id);
		model.addAttribute(application);
		return "teacher/studentPaper";
	}
}

package cm.controller;

import cm.commons.PageBean;
import cm.commons.config.SystemConfiguration;
import cm.dto.SystemDto;
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
import java.util.List;
import java.util.UUID;

@Controller
public class CaseController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ManageService manageService;
	@Autowired
	private StateService stateService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private TimeLogService timeLogService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SystemConfiguration configuration;
	@Autowired
	private ClazzService classService;
	@Autowired
	private CaseService caseService;
	/**
	 * 列出案例
	 *
	 * @return
	 */
	@RequestMapping(value = "teacher/cases",method = RequestMethod.GET)
	public String list(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		List<Case> caseList = caseService.find(teacher.getCuState());
		model.addAttribute("caseList", caseList);
		return "case/list";
	}

	@RequestMapping(value = "teacher/case/add",method = RequestMethod.GET)
	public String add(Model model) {
		Case c = new Case();
		model.addAttribute("case",c);
		return "case/addUI";
	}

	@RequestMapping(value = "teacher/case/add",method = RequestMethod.POST)
	public String add(@Valid Case c,BindingResult br,HttpSession session) {
		if(br.hasErrors()){
			System.out.println(br.getFieldError());
		}
		//	return "case/addUI";
		Teacher teacher = (Teacher) session.getAttribute("user");
		c.setState(teacher.getCuState());
		c.setId(UUID.randomUUID());
		caseService.save(c);
		return "redirect:/teacher/cases";
	}

	@RequestMapping(value = "teacher/case/update/{id}",method = RequestMethod.GET)
	public String update(@PathVariable UUID id,Model model) {
		model.addAttribute("case", caseService.get(id));
		return "case/updateUI";
	}

	@RequestMapping(value = "teacher/case/update",method = RequestMethod.POST)
	public String update(@Valid Case c,BindingResult br,Model model) {
		if(br.hasErrors())
			return "case/updateUI";
		Case cas = caseService.get(c.getId());
		cas.setContent(c.getContent());
		cas.setTitle(c.getTitle());
		caseService.update(cas);
		model.addAttribute("case", cas);
		return "redirect:/teacher/cases";
	}

	@RequestMapping(value = "teacher/case/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable String id) {
		caseService.delete(id);
		return "redirect:/teacher/cases";
	}
	/**
	 * 工程案例
	 *
	 * @return
	 */
	@RequestMapping(value = "student/showCase/{id}",method = RequestMethod.GET)
	public String showCase(@PathVariable String id, Model model) {
		model.addAttribute("Case", caseService.get(UUID.fromString(id)));
		return "student/showCase";
	}

	@RequestMapping(value = "student/cases",method = RequestMethod.GET)
	public String caseList(Model model, HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		model.addAttribute("caseList", caseService.find(student.getCuState()));
		return "student/caseList";
	}
}

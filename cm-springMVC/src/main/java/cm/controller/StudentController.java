package cm.controller;

import cm.commons.PageBean;
import cm.commons.config.SystemConfiguration;
import cm.commons.util.MDCoder;
import cm.entity.*;
import cm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/student/")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentController {
	public static final int PageSize =3;
	@Autowired
	private StudentService studentService;
	@Autowired
	private StateService stateService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private StAttendanceItemService stAttendanceItemService;



	/**
	 * 我的考试
	 *
	 * @return
	 */
	@RequestMapping(value = "myExam",method = RequestMethod.GET)
	public String myExam(Model model, HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		State state = stateService.get(student.getCuState().getId());
		Application application = applicationService.find(state, student);
		Boolean examState = application.getExamState();
		if (!state.getReExamAble() && !examState) {
			Paper paper = application.getPaper();
			if (paper != null) {
				model.addAttribute("problem", paper.getProblem());
				model.addAttribute("id", paper.getId());
				return "student/paper_unfinish";
			}

		}
		model.addAttribute("examState", examState);
		model.addAttribute("examAttention", state.getExamAttention());
		model.addAttribute("reExamAble", state.getReExamAble());
		model.addAttribute("examAble", state.getExamAble());
		return "student/exam";
	}



	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add() {
		return "student/register";
	}

	@RequestMapping(value = "add",method = RequestMethod.POST)
	public String add(@Valid Student student,BindingResult br,Model model) {
         if (br.hasErrors())
			 return "add";
		if (student.getNumber() != null) {
			Student s = studentService.findByNumber(student.getNumber());
			if (s != null) {
				model.addAttribute("error","该学号已注册，注册失败！");
				return "student/register";
			} else {
				student.setId(UUID.randomUUID());
				student.setPassword(MDCoder.encodeMD5Hex(student.getPassword()));
				studentService.save(student);
			}
		}
		     model.addAttribute("success", "恭喜你，注册成功！");
		return "public/other";
	}

	@RequestMapping(value = "attendance",method = RequestMethod.GET)
	public String StAttendances(HttpSession session,Model model) {
		Student student = (Student) session.getAttribute("user");
		State state = stateService.get(student.getCuState().getId());
		List<StAttendanceItem> stAttendanceItems = stAttendanceItemService.getAll(student,state);
	    model.addAttribute("stAttendanceItems",stAttendanceItems);
		return "student/attendanceItems";
	}



}

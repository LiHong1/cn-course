package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.commons.util.MDCoder;
import cm.dto.CourseDto;
import cm.dto.PersonDto;
import cm.entity.*;
import cm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
public class ApplicationController {
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
	 * 申请加入班级
	 *
	 * @return
	 */

	@RequestMapping(value = "/student/application/apply",method = RequestMethod.GET)
	public String apply(Model model) {
		List<Course> courseList = courseService.getAll();
		model.addAttribute("courseList", courseList);
		model.addAttribute("personDto",new PersonDto());
		return "student/apply";
	}

	@RequestMapping(value = "/student/application/apply",method = RequestMethod.POST)
	public String apply(@Valid PersonDto personDto,BindingResult br,Model model) {
		List<Course> courseList = courseService.getAll();
		model.addAttribute("courseList", courseList);
		Student student = studentService.findByNumber(personDto.getNumber());
		if (student == null) {
			model.addAttribute("message", "你还没有注册，请先去注册！");
		} else {
			if (!student.getPassword().equals(MDCoder.encodeMD5Hex(personDto.getPassword()))) {
				model.addAttribute("message", "你的密码输入错误！");
			}else{
				State state = stateService.find(courseService.get(personDto.getCourseId()), classService.get(personDto.getClassId()));
				if (state == null) {
					model.addAttribute("message", "你的申请失败，请与管理员联系！");
				} else{
					Set<Application> applications = student.getApplications();
					for (Application application : applications) {
						if (application.getState().equals(state)) {
							model.addAttribute("message", "你已经申请了该课程！");
							return "student/apply";
						}
					}
					if (state.getJoinAble()) {
						Application application = new Application();
						application.setId(UUID.randomUUID());
						application.setState(state);
						application.setStudent(studentService.findByNumber(personDto.getNumber()));
						application.setType(0l);
						applicationService.save(application);
						model.addAttribute("message", "你的申请已经完成，请等待任课老师批准！");
					}else{
						model.addAttribute("message", "系统已经关闭申请,请联系任课老师！");
					}

				}

			}

		}
		   return "student/apply";
	}

	@RequestMapping(value = "/student/application/getClass",method = RequestMethod.GET)
	public @ResponseBody List<CourseDto> applicationGetClass(String number,String courseId) {
		Student student = studentService.findByNumber(number);
		List <CourseDto> courseDtos = new ArrayList<CourseDto>();
		if (student != null) {
			Course course = courseService.get(UUID.fromString(courseId));
			List<State> stateList = stateService.find(null, course, null);
			for (State a : stateList) {
				courseDtos.add(new CourseDto(a.getClas().getId(), a.getClas().getName()));
			}
		}
		return courseDtos;
	}
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "/teacher/student/application/getThrough/{id}",method = RequestMethod.GET)
	public String getThrough(@PathVariable UUID id,Long type,int option) {
		Application application = applicationService.get(id);
		application.setType(1l);
		application.setJoinClassTime(Calendar.getInstance());
		applicationService.update(application);
		return "redirect:/teacher/classSet?option="+option+"&type="+type;
	}

	@PreAuthorize("hasRole('ROLE_TEACHER')")
	@RequestMapping(value = "teacher/student/application/refuse/{id}",method = RequestMethod.GET)
	public String refuse(@PathVariable UUID id,Long type,int option) {
		Application application = applicationService.get(id);
		application.setType(2l);
		applicationService.update(application);
		return "redirect:/teacher/classSet?option="+option+"&type="+type;
	}
}

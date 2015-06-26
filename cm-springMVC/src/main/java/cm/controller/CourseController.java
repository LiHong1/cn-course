package cm.controller;

import cm.commons.PageBean;
import cm.commons.config.SystemConfiguration;
import cm.entity.Course;
import cm.entity.Student;
import cm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class CourseController {
	public static final int PageSize = 3;
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

	@RequestMapping(value = "manage/course/add",method = RequestMethod.POST)
	public String add(@Valid Course course,BindingResult br) {
		System.out.println(course.getName());
		if (br.hasErrors())
			return "add";
		if (courseService.find("name", course.getName()) == null && course.getName() != null && !course.getName().equals("")) {
			course.setId(UUID.randomUUID());
			courseService.save(course);
		}
		return "redirect:/manage/courses";
	}

	@RequestMapping(value = "manage/courses", method = RequestMethod.GET)
	public String courseList(Model model,Integer pageNum) {
		/*List<Course> list = courseService.getAll();
		model.addAttribute("courseList", list);*/
		if (pageNum == null){
			pageNum=1;
		}
		PageBean<Course> page = courseService.getPage(pageNum, PageSize);
		model.addAttribute("page",page);
		return "manage/courseList";
	}


	/**
	 * 课程信息
	 *
	 * @return
	 */
	@RequestMapping(value = "student/course/show",method = RequestMethod.GET)
	public String showCourse(Model model,HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		student = studentService.get(student.getId());
		model.addAttribute("course", student.getCuState().getCourse());
		return "student/showCourse";
	}

}

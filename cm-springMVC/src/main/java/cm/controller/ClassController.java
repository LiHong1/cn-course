package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.entity.Clazz;
import cm.entity.Course;
import cm.entity.State;
import cm.entity.Teacher;
import cm.service.*;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@Controller
public class ClassController {
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

	@RequestMapping(value = "manage/state/find",method= RequestMethod.POST)
	public String find(String className,String courseName,String teacherName,Model model) {
		List<State> list = stateService.find(classService.find("name", className), courseService.find("name", courseName), teacherService.find("name", teacherName));
		List<Course> courses = courseService.getAll();
		List<Teacher> teachers = teacherService.getAll();
		model.addAttribute("states", list);
		model.addAttribute("teachers", teachers);
		model.addAttribute("courses", courses);
		model.addAttribute("courseName","");
		return "manage/classList";
	}

	@RequestMapping(value = "manage/state/add",method= RequestMethod.POST)
	public String add(String courseId,String  teacherId,String className) {
		State state = null;
		Clazz clazz = new Clazz();
		Teacher teacher = teacherService.get(UUID.fromString(teacherId));
		Course course = courseService.get(UUID.fromString(courseId));
		cm.entity.Clazz clas = classService.find("name", className);

		//判断该班级是否存在
		if (clas == null && className != null && !className.equals("")) {
			clazz.setName(className);
			clazz.setId(UUID.randomUUID());
			classService.save(clazz);
			clas = clazz;
		}
		if (stateService.find(course, clas) != null)
			return "toList";

		state = new State();
		state.setId(UUID.randomUUID());
		state.setJoinAble(false);
		state.setLoginAble(false);
		state.setExamAble(false);
		state.setReExamAble(false);

		teacher.getCourses().add(course);
		teacher.getClasses().add(clas);
		teacherService.update(teacher);
		state.setClas(clas);
		state.setTeacher(teacher);
		state.setCourse(course);
		stateService.save(state);
		return "redirect:/manage/classes";
	}

	@RequestMapping(value = "manage/state/delete/{id}",method= RequestMethod.GET)
	public String delete(@PathVariable UUID id) {
		stateService.delete(id);
		return "redirect:/manage/classes";
	}

	@RequestMapping(value = "manage/state/off/{id}",method= RequestMethod.GET)
	public String turnOffClass(@PathVariable UUID id) {
		State state = stateService.get(id);
		state.setLoginAble(false);
		state.setJoinAble(false);
		stateService.update(state);
		return "redirect:/manage/classes";
	}
}

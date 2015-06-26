package cm.controller;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import cm.commons.bean.MenuItem;
import cm.commons.config.SystemConfiguration;
import cm.commons.service.MenuService;
import cm.commons.util.MDCoder;
import cm.dto.PersonDto;
import cm.dto.SystemDto;
import cm.entity.*;
import cm.service.*;
import cm.util.CacheUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class UserController {
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
	private MenuService menuService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,HttpSession session) {
		Person person = new Person();
		model.addAttribute(person);
		session.removeAttribute("islogin");
		return "public/login";
	}
	@RequestMapping(value = "/login/failure", method = RequestMethod.GET)
	public String LoginFailure(Model model,Integer error) {
		String information = null;
		if(error != null){
			if(error == 1)
				information = "您输入的用户名或密码错误！";
			else if(error == 2)
				information = "请选择班级与班级！";
			else if(error == 3)
				information = "该系统没有开放，请联系管理员！";
		}
		model.addAttribute("error",information);
		return "public/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid PersonDto personDto,BindingResult br, Model model, HttpServletRequest request) {
		State state;
		Person person = new Person();
		model.addAttribute(person);
		SystemDto systemDto = CacheUtil.getSystemConfiguration(configuration);
		Boolean loginAble = systemDto.getLoginAble();
		Boolean replaceMachineAble = systemDto.getReplaceMachineAble();
		if (loginAble == true) {
			Student student = studentService.findByNumber(personDto.getNumber());
			if (student != null) {
				request.getSession().setAttribute("user", student);
				request.getSession().setAttribute("role", "student");
			}else{
				Teacher teacher = teacherService.findByNumber(personDto.getNumber());
				if (teacher != null) {
					request.getSession().setAttribute("role", "teacher");
					request.getSession().setAttribute("user", teacher);
					teacherService.update(teacher);
				}
			}


		}
		Manage manage = manageService.findByNumber(personDto.getNumber());
		if (manage != null) {
			request.getSession().setAttribute("user", manage);
			request.getSession().setAttribute("role", "manage");
			return "public/index";
		}
		if (loginAble != true) {
			return "redirect:/login/failure?error=3";
		}

		return "public/index";
	}



	public Boolean loginAble(State state, Model model, Boolean overtime) {
		Boolean loginAble = true;
		if (state == null) {
			model.addAttribute("error", "请选择课程与班级");
			loginAble = false;
		}
		if (!state.getLoginAble()) {
			model.addAttribute("error", "该班级没有开放，请联系任课教师！");
			loginAble = false;
		}
		if (overtime) {
			model.addAttribute("error", "你已经在另一台机子上登录，可三十分钟后重新登录");
			loginAble = false;
		}
		return loginAble;
	}

	public boolean loginAble(String replaceMachineAble, Application application, Student student, HttpServletRequest request) {
		if (application.getLatestLoginTime() == null)
			return true;
		long time = Calendar.getInstance().getTimeInMillis() - application.getLatestLoginTime().getTimeInMillis();
		if (time > 30 * 60 * 1000 || replaceMachineAble.equals("true"))
			return true;
		else {
			String remoteAddr = request.getRemoteAddr();
			if (remoteAddr.equals(student.getRemoteAddr()))
				return true;
			else return false;
		}
	}

	@RequestMapping(value = "/getCourse", method = RequestMethod.GET)
	public @ResponseBody List<Course> loginGetCourse(String number) {
		Student student = studentService.findByNumber(number);
		List courses = new ArrayList<Course>();
		if (student != null) {
			for (Application application : student.getApplications()) {
				if (application.getType() == 1l) {
					State state = application.getState();
					//为true才能显示课程
					if(state.getLoginAble()){
						Course a = state.getCourse();
						Course b = new Course();
						b.setName(a.getName());
						b.setId(a.getId());
						courses.add(b);
					}
				}

			}
		} else {
			Teacher teacher = teacherService.findByNumber(number);
			if (teacher != null) {
				for (Course a : teacher.getCourses()) {
					Course b = new Course();
					b.setName(a.getName());
					b.setId(a.getId());
					courses.add(b);
				}
			}
		}
		return courses;
	}

	@RequestMapping(value = "/getClass", method = RequestMethod.GET)
	public @ResponseBody List<Clazz> loginGetClass(String number, String courseId) {
		Teacher teacher = teacherService.findByNumber(number);
		List<Clazz> clazzs = new ArrayList<Clazz>();
		if (teacher != null) {
			List<State> stateList = stateService.find(null, courseService.get(UUID.fromString(courseId)), teacher);
			for (State a : stateList) {
				Clazz c = new Clazz();
				c.setId(a.getClas().getId());
				c.setName(a.getClas().getName());
				clazzs.add(c);
			}
		} else {
			Student student = studentService.findByNumber(number);
			if (student != null) {
				Course course = courseService.get(UUID.fromString(courseId));
				for (Application application : student.getApplications()) {
					if (application.getType() == 1l) {
						Clazz a = application.getState().getClas();
						if (application.getState().getCourse().getId() == course.getId()) {
							Clazz c = new Clazz();
							c.setId(a.getId());
							c.setName(a.getName());
							clazzs.add(c);
						}
					}

				}
			}
		}
		return clazzs;
	}

	@RequestMapping(value ="/changePassword",method = RequestMethod.POST)
	public String changePassword(Person p,String newPassword,Model model,HttpSession session) {
		Person person = (Person) session.getAttribute("user");
		if (person != null){
			if (person.getPassword().equals(MDCoder.encodeMD5Hex(p.getPassword()))) {
				person.setPassword(MDCoder.encodeMD5Hex(newPassword));
				if (person instanceof  Manage)
					manageService.update((Manage) person);
				else if (person instanceof  Teacher){
					Teacher teacher = (Teacher) person;
					teacher.setUnencry_password(p.getPassword());
					teacherService.update(teacher);
				}
				else if (person instanceof  Student)
					studentService.update((Student) person);
				model.addAttribute("info","更改成功");
			} else {
				model.addAttribute("info","你的旧口令输入错误!");
			}
		}
		return "public/changePassword";
	}

	@RequestMapping(value ="/changePassword",method = RequestMethod.GET)
	public String changePassword() {
		return "public/changePassword";
	}

	@RequestMapping(value ="/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		Person person =(Person)session.getAttribute("user");
		if (person instanceof Student) {
			Application application = applicationService.find(((Student) person).getCuState(), (Student) person);
			application.setLatestLogoutTime(Calendar.getInstance());
			applicationService.update(application);
		}
		session.removeAttribute("user");
		session.removeAttribute("islogin");
		return "redirect:login";
	}

	/*@RequestMapping(value = "/getJsonp", method = RequestMethod.GET)
	public @ResponseBody JSONPObject loginGetClass(String callBack) {
		Student student = new Student();
		student.setName("ddsfsd");
		student.setPassword("fdsfsd");
		return  new JSONPObject(callBack, student);
	}*/
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@Valid PersonDto personDto,BindingResult br, Model model, HttpServletRequest request) {
		State state;
		Person person = (Person)request.getSession().getAttribute("user");
		SystemDto systemDto = CacheUtil.getSystemConfiguration(configuration);
		Boolean loginAble = systemDto.getLoginAble();
		Boolean replaceMachineAble = systemDto.getReplaceMachineAble();
			if (person instanceof Student) {
				Student student = studentService.get(person.getId());
				if (personDto.getClassId() ==null||personDto.getCourseId()==null) {
					return "redirect:/login/failure?error=2";
				}
				state = stateService.find(courseService.get(personDto.getCourseId()), classService.get(personDto.getClassId()));
				Application application = applicationService.find(state, student);
				Boolean login = loginAble(state, model, !loginAble(replaceMachineAble.toString(), application, student, request));
				if (login == false)
					return "redirect:/login/failure?error=3";
				student.setCuState(state);
				student.setRemoteAddr(request.getRemoteAddr());
				timeLogService.save(application);
				studentService.update(student);
				request.getSession().setAttribute("user",student);
			}else{
				if (person instanceof Teacher) {
					Teacher teacher = (Teacher)teacherService.get(person.getId());
					if (personDto.getClassId() ==null||personDto.getCourseId()==null) {
						return "redirect:/login/failure?error=2";
					}
					State cuState = stateService.find(courseService.get(personDto.getCourseId()), classService.get(personDto.getClassId()));
					teacher.setCuState(cuState);
					teacherService.update(teacher);
					request.getSession().setAttribute("user",teacher);
				}
			}
		request.getSession().setAttribute("islogin",true);
		return "public/index";
	}

}

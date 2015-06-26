package cm.controller;

import cm.bean.AjaxObj;
import cm.commons.PageBean;
import cm.commons.config.SystemConfiguration;
import cm.commons.util.MDCoder;
import cm.dto.SystemDto;
import cm.entity.*;
import cm.entity.Student;
import cm.service.*;
import cm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.jboss.logging.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/teacher/")
@PreAuthorize("hasRole('ROLE_TEACHER')")
public class TeacherController {
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
	@Autowired
	private JobService jobService;

	@RequestMapping(value = "courseSet",method = RequestMethod.GET)
	public String courseSet(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		teacher = teacherService.get(teacher.getId());
		model.addAttribute("course",teacher.getCuState().getCourse());
		return "teacher/courseSet";
	}

	/**
	 * 导入学生与课程
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "import",method = RequestMethod.GET)
	public String importClassAndTeacher(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		teacher = teacherService.get(teacher.getId());
		//cookie为教师登录教务在线的cookie
		String cookie = (String)session.getAttribute("cookie");
		List<String> cookies;
		List<Information> informations;
		if(cookie == null){
			cookies  = LoginHttpPostUtil.getConnect(teacher.getNumber(),teacher.getUnencry_password(),"teacher");
			if(cookies!=null){
				cookie = cookies.get(0);
				session.setAttribute("cookie",cookie);
			}
		}


		if(cookie !=null ){
			informations = GetCourseAndClassHttpPostUtil.getCourseAndClass(cookie,"teacher");
			//informations = GetCourseAndClassHttpPostUtil.getCourseAndClass("JwOAUserSettingNew=UserNum=H2WCSQThTI8=&UserName=M0y4ZTAC+lYcMIX+h+m1pg==&UserType=h4/SEBPSa9Q=&UserLoginTime=2015/6/4 20:29:44&GroupID=R16RIkC3cEk=&UnitID=aRpgHYmrsMs=","teacher");
			model.addAttribute("informations",informations);
		}
		else{
			model.addAttribute("error", "您的用户名或密码与教务在线的不一致，请重置密码或重新注册！！");
		}
				return "teacher/import";
	}

	@RequestMapping(value = "importStudent",method = RequestMethod.GET)
	public @ResponseBody AjaxObj importClassAndTeacher(Information information,HttpSession session)throws Exception{
		Teacher teacher = (Teacher) session.getAttribute("user");
		AjaxObj ajaxObj;
		teacher = teacherService.get(teacher.getId());
		String cookie = (String)session.getAttribute("cookie");
		if(cookie==null){
			ajaxObj = new AjaxObj(1,"导入失败");
			return ajaxObj;
		}
		List<cm.util.Student> students = GetStudentHttpGetUtil.getStudent(cookie,information.getUrl());
		//List<cm.util.Student> students = GetStudentHttpGetUtil.getStudent("JwOAUserSettingNew=UserNum=H2WCSQThTI8=&UserName=M0y4ZTAC+lYcMIX+h+m1pg==&UserType=h4/SEBPSa9Q=&UserLoginTime=2015/6/4 20:29:44&GroupID=R16RIkC3cEk=&UnitID=aRpgHYmrsMs=",information.getUrl());
		Course course = courseService.find("name", information.getCourseName());
		if(course == null){
			course = new Course();
			course.setId(UUID.randomUUID());
			course.setName(information.getCourseName());
			courseService.save(course);
		}
		Clazz clazz = classService.find("name",information.getClassName());
		if(clazz == null){
			clazz = new Clazz();
			clazz.setName(information.getClassName());
			clazz.setId(UUID.randomUUID());
            classService.save(clazz);
		}
		State state = stateService.find(course,clazz);
		if(state == null){
			state = new State();
			state.setId(UUID.randomUUID());
			state.setCourse(course);
			state.setClas(clazz);
			state.setTeacher(teacher);
			stateService.save(state);
		}
		for(cm.util.Student student : students){
			Student s = studentService.findByNumber(student.getNumber());
			if(s == null){
				s = new Student();
				s.setId(UUID.randomUUID());
				s.setName(student.getName());
				s.setPassword(MDCoder.encodeMD5Hex("0000"));
				s.setNumber(student.getNumber());
				studentService.save(s);
				Application application = new Application();
				application.setId(UUID.randomUUID());
				application.setState(state);
				application.setJoinClassTime(Calendar.getInstance());
				application.setStudent(s);
				application.setType(1l);
				applicationService.save(application);

			}
		}
		Set<Course> courses = teacher.getCourses();
		teacher.getClasses().add(clazz);
		teacher.getCourses().add(course);
		teacherService.update(teacher);
		 ajaxObj = new AjaxObj(1,"导入成功");
		return ajaxObj;
	}

	@RequestMapping(value = "courseSet", method = RequestMethod.POST)
	public String courseSet(@Valid Course c,BindingResult br) {
		if (br.hasErrors())
			return "courseSet";
		Course course = courseService.get(c.getId());
		course.setSchedule(c.getSchedule());
		course.setInformation(c.getInformation());
		courseService.update(course);
		return "redirect:/teacher/courseSet";
	}

	@RequestMapping(value = "classSet",method = RequestMethod.POST)
	public String classSet(Boolean joinAble,Boolean loginAble,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = stateService.get(teacher.getCuState().getId());
		state.setJoinAble(joinAble);
		state.setLoginAble(loginAble);
		stateService.update(state);
		return "redirect:classSet";
	}

	@RequestMapping(value = "classSet",method = RequestMethod.GET)
	public String classManage(Integer pageNum,Integer option,Model model,Long type,HttpSession session) {
		if(pageNum == null){
			pageNum=1;
		}
		Teacher teacher = (Teacher) session.getAttribute("user");
		teacher = teacherService.get(teacher.getId());
		List<Application> applicationList = new ArrayList<Application>();
		PageBean<Application> page;
		Application application = new Application();
		application.setCreatedDate(null);
		application.setExamState(null);
		application.setTimeCount(null);
		application.setApplicationTime(null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", teacher.getCuState());
		if (type == null || type == -1 ) {
			application.setType(null);
		} else {
			application.setType(type);
		}
		page = applicationService.getPageByExample(application, pageNum, PageSize, map);
		model.addAttribute("page",page);
		model.addAttribute("teacher", teacher);
		model.addAttribute("type", type);
		model.addAttribute("option", option);
		return "teacher/classManage";
	}


	@RequestMapping(value = "students",method = RequestMethod.GET)
	public String students(Integer pageNum,Model model,HttpSession session) {
		if(pageNum == null){
			pageNum=1;
		}
		Teacher teacher = (Teacher) session.getAttribute("user");
		Application application = new Application();
		application.setCreatedDate(null);
		application.setExamState(null);
		application.setTimeCount(null);
		application.setApplicationTime(null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", teacher.getCuState());
		application.setType(1L);
		PageBean<Application> page = applicationService.getPageByExample(application, pageNum, PageSize,map);
		model.addAttribute("page", page);
		return "teacher/studentList";
	}

	@RequestMapping(value = "student/reExam/{id}",method = RequestMethod.GET)
	public String reExam(@PathVariable UUID id) {
		Application application = applicationService.get(id);
		application.setPaper(null);
		application.setExamState(false);
		applicationService.update(application);

		return "redirect:/teacher/students";
	}

	@RequestMapping(value ="/changePassword",method = RequestMethod.GET)
	public String changePassword() {
		return "teacher/changePassword";
	}

	@RequestMapping(value ="/changePassword",method = RequestMethod.POST)
	public String changePassword(Person p,String newPassword,Model model,HttpSession session) {
		Person person = (Person) session.getAttribute("user");
		if (person != null){
			if (person.getPassword().equals(MDCoder.encodeMD5Hex(p.getPassword()))) {
					Teacher teacher = (Teacher) person;
				teacher.setUnencry_password(p.getPassword());
				    teacher.setPassword(MDCoder.encodeMD5Hex(newPassword));
					teacherService.update(teacher);
				model.addAttribute("info","更改成功");
			} else {
				model.addAttribute("info","你的旧口令输入错误!");
			}
		}
		return "redirect:/teacher/import";
	}


	@RequestMapping(value = "student/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable UUID id,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = teacher.getCuState();
		Student student = studentService.get(id);
		Application application = applicationService.find(state, student);
		applicationService.delete(application.getId());
		return "redirect:/teacher/students";
	}

	@RequestMapping(value = "examSet",method = RequestMethod.GET)
	public String examSetUI(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = null;
		if (teacher != null) {
			state = teacher.getCuState();
			if (state != null)
				state = stateService.get(state.getId());
		}
		model.addAttribute("state", state);
		return "teacher/examSet";
	}

	@RequestMapping(value = "examSet",method = RequestMethod.POST)
	public String examSet(State s) {
		State state = stateService.get(s.getId());
		state.setExamAble(s.getExamAble());
		state.setReExamAble(s.getReExamAble());
		state.setExamAttention(s.getExamAttention());
		stateService.update(state);
		return "redirect:examSet";
	}


	@RequestMapping(value = "student/resetPassword/{id}",method = RequestMethod.GET)
	public String resetPassword(@PathVariable UUID id) {
		Student student = studentService.get(id);
		student.setPassword(MDCoder.encodeMD5Hex("0000"));
		studentService.update(student);
		return "redirect:/teacher/students";
	}

	/**
	 * 所有重新考试
	 *
	 * @return
	 */
	@RequestMapping(value = "student/reExam",method = RequestMethod.GET)
	public String reExam(HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = teacher.getCuState();
		List<Application> applications = applicationService.findByState(state);
		for (Application application : applications) {
			application.setPaper(null);
			application.setExamState(false);
			applicationService.update(application);
		}
		return "redirect:/teacher/examSet";
	}


	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add() {
		return "teacher/register";
	}

	@RequestMapping(value = "add",method = RequestMethod.POST)
	public String add(@Valid Teacher teacher,BindingResult br,Model model) {
		if (br.hasErrors())
			return "add";
		if (teacher.getNumber() != null) {
			Teacher s = teacherService.findByNumber(teacher.getNumber());
			if (s != null) {
				model.addAttribute("error","该教工号已注册，注册失败！");
				return "teacher/register";
			} else {
				teacher.setId(UUID.randomUUID());
				teacher.setUnencry_password(teacher.getPassword());
				teacher.setPassword(MDCoder.encodeMD5Hex(teacher.getPassword()));
				teacherService.save(teacher);
			}
		}
		model.addAttribute("success", "恭喜你，注册成功！");
		return "public/other";
	}

	@RequestMapping(value = "student/show/{id}",method = RequestMethod.GET)
	public String studentShow(@PathVariable UUID id,Boolean nextStudent,Integer option,Integer pageNum,Model model,HttpSession session) {
		if(pageNum ==null) {
			pageNum = 1;
		}
		if(option == null)
			option = 0;
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = teacher.getCuState();
		Student student = null;
		Application application;
		List<Student> studentList = new ArrayList<Student>();
		List<Application> applications = applicationService.findByState(state);

		for (Application a : applications) {
			if (a.getType() == 1l) {
				student = a.getStudent();
				studentList.add(student);
			}
		}
		orderStudentByNumber(studentList);
		if (nextStudent != null&&nextStudent == true) {
			System.out.println("true");
			for (int i = 0; i < studentList.size(); i++) {
				if (studentList.get(i).getId().equals(id)) {
					i = i + 1;
					System.out.println("i="+i);
					if (i < studentList.size()){
						student = studentList.get(i);
						System.out.println(student.getId());
					}
				}
			}

		} else if (id != null)
			student = studentService.get(id);

		studentList.remove(student);
		studentList.add(0, student);
		application = applicationService.find(state, student);
		TimeLogging timeLogging = new TimeLogging();
		timeLogging.setCreatedDate(null);
		timeLogging.setUpdatedDate(null);
		timeLogging.setLoginTime(null);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("application", application);
		PageBean<TimeLogging> page = timeLogService.getPageByExample(timeLogging, pageNum, PageSize,map);
		model.addAttribute("page", page);
		System.out.println(studentList.size());
		model.addAttribute("studentList", studentList);
		model.addAttribute("student", student);
		model.addAttribute("option", option);
		model.addAttribute("application", application);
		model.addAttribute("jobs", jobService.findJobs(student, state));
		return "teacher/studentShow";

	}

	public void orderStudentByNumber(List<Student> students) {
		int k = students.size();
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int j = 1; j < k; j++)
				if (Long.parseLong(students.get(j - 1).getNumber()) > Long.parseLong(students.get(j).getNumber())) {
					Student student = students.get(j - 1);
					students.set(j - 1, students.get(j));
					students.set(j, student);
					flag = true;
				}
			k--;
		}
	}
}

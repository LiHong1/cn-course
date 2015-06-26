package cm.controller;

import cm.commons.PageBean;
import cm.commons.config.SystemConfiguration;
import cm.commons.util.FileUtils;
import cm.commons.util.MDCoder;
import cm.dto.PersonDto;
import cm.dto.SystemDto;
import cm.entity.*;
import cm.service.*;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manage/")
@PreAuthorize("hasRole('ROLE_MANAGE')")
public class ManagerController {
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

	@RequestMapping(value = "/classes", method = RequestMethod.GET)
	public String classList(Model model,Integer pageNum) {
		/*List<State> states = stateService.getAll();*/
		List<Teacher> teachers = teacherService.getAll();
		List<Course> courses = courseService.getAll();
		if (pageNum == null){
			pageNum=1;
		}
		PageBean<State> page = stateService.getPage(pageNum, PageSize);
		model.addAttribute("page",page);
		model.addAttribute("teachers", teachers);
		model.addAttribute("courses", courses);
		model.addAttribute("courseName","");
		return "manage/classList";
	}

	/*@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String courseList(Model model) {
		List<Course> list = courseService.getAll();
		model.addAttribute("courseList",list);
		return "manage/courseList";
	}*/

	@RequestMapping(value = "/teachers", method = RequestMethod.GET)
	public String teacherList(Model model,Integer pageNum) {
		/*List<Teacher> list = teacherService.getAll();
		model.addAttribute("teachers",list);*/
		if (pageNum == null){
			pageNum=1;
		}
		PageBean<Teacher> page = teacherService.getPage(pageNum, PageSize);
		model.addAttribute("page",page);

		return "manage/teacherList";
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String studentList(Model model,Integer pageNum) {
		if (pageNum == null){
			pageNum=1;
		}
		PageBean<Student> page = studentService.getPage(pageNum, PageSize);
		model.addAttribute("page",page);
		return "manage/studentList";
	}

	@RequestMapping(value = "/system", method = RequestMethod.GET)
	public String system(Model model) {
		SystemDto systemDto;
		CacheManager manager = CacheManager.newInstance();
		Cache systemConfigCache = manager.getCache("systemConfigCache");
		Object object = systemConfigCache.get("systemConfig");
		if(object == null){
			systemDto = new SystemDto();
			String loginAble = (String) configuration.getProperty("loginAble");
			String replaceMachineAble = (String) configuration.getProperty("replaceMachineAble");
			String size = (String) configuration.getProperty("jobArea");
			String startTermTime = (String) configuration.getProperty("startTerm");
			String fileRoot = (String) configuration.getProperty("fileRoot");
			systemDto.setLoginAble(Boolean.parseBoolean(loginAble));
			systemDto.setReplaceMachineAble(Boolean.parseBoolean(replaceMachineAble));
			systemDto.setJobArea(Integer.parseInt(size));
			systemDto.setStartTermTime(startTermTime);
			systemDto.setFileRoot(fileRoot);
			systemConfigCache.put(new Element("systemConfig", systemDto));
		}
		model.addAttribute("system",systemConfigCache.get("systemConfig").getObjectValue());
		return "manage/system";
	}

	@RequestMapping(value = "/system", method = RequestMethod.POST)
	public String system(SystemDto systemDto,Model model,HttpSession session) {
		configuration.clearProperty("loginAble");
		configuration.clearProperty("replaceMachineAble");
		configuration.clearProperty("jobArea");
		configuration.clearProperty("startTerm");
		configuration.clearProperty("fileRoot");

		configuration.addProperty("loginAble", systemDto.loginAble);
		configuration.addProperty("replaceMachineAble", systemDto.replaceMachineAble);
		configuration.addProperty("jobArea", systemDto.jobArea);
		configuration.addProperty("startTerm", systemDto.startTermTime);
		String root = systemDto.getFileRoot();
		if(FileUtils.createDir(root) == false){
			model.addAttribute("fileRoot","你设置的上传文件根路径找不到");
		}
		configuration.addProperty("fileRoot", systemDto.getFileRoot());
		CacheManager manager = CacheManager.newInstance();
		Cache systemConfigCache = manager.getCache("systemConfigCache");
		systemConfigCache.put(new Element("systemConfig",systemDto));
		model.addAttribute("system",systemDto);
		return "manage/system";
	}
	@RequestMapping(value = "student/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable UUID id) {
		studentService.delete(id);
		return "redirect:/manage/students";
	}

	@RequestMapping(value = "student/delete",method = RequestMethod.POST)
	public String likeDelete(String number) {
		studentService.likeDelete(number);
		return "redirect:/manage/students";
	}

	@RequestMapping(value = "teacher/add",method = RequestMethod.POST)
	public String add(@Valid Teacher teacher,BindingResult br) {
		if (br.hasErrors())
			return "add";
		if (teacherService.findByNumber(teacher.getNumber()) == null) {
			teacher.setPassword("0000");
			teacher.setId(UUID.randomUUID());
			teacher.setPassword(MDCoder.encodeMD5Hex(teacher.getPassword()));
			teacherService.save(teacher);
		}

		return "redirect:/manage/teachers";
	}


}

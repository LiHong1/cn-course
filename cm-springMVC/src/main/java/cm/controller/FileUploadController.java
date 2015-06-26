package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.commons.util.FileUtils;
import cm.dto.SystemDto;
import cm.entity.*;
import cm.service.*;
import cm.util.SystemConfigUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.io.FilenameUtils;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {
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
	private MaterialService materialService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private JobService jobService;
	@Autowired
	private SystemConfiguration configuration;

	@RequestMapping(value = "student/job/add",method = RequestMethod.POST)
	public String job(HttpSession session,String name,MultipartFile file) throws Exception {
		Student student = (Student) session.getAttribute("user");
		String path = SystemConfigUtil.getRoot(configuration);
		if (file != null) {
			jobService.save(file.getOriginalFilename(), name, student, path, file.getBytes());
		}
		return "redirect:/student/jobs";

	}

	@RequestMapping(value = "teacher/case/material/add/{id}",method = RequestMethod.POST)
	public String caseMaterial(@PathVariable UUID id,HttpSession session,String name,MultipartFile file) throws  Exception{
		Case cas = caseService.get(id);
		String path = SystemConfigUtil.getRoot(configuration);

		if (file != null) {
			materialService.caseMaterialSave(file.getOriginalFilename(), name, path, file.getBytes(), cas);
		}
		return "redirect:/teacher/cases";
	}



	@RequestMapping(value = "teacher/course/material/add",method = RequestMethod.POST)
	public String courseMaterial(HttpSession session,String name,MultipartFile file) throws Exception {
		Teacher teacher = (Teacher) session.getAttribute("user");
		String path = SystemConfigUtil.getRoot(configuration);

		if (file != null) {
			materialService.courseMaterialSave(file.getOriginalFilename(), name, path, session.getServletContext().getRealPath(""),file.getBytes(), teacher);
		}
		return "redirect:/teacher/materials";

	}

}

package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.dto.SystemDto;
import cm.entity.Case;
import cm.entity.Job;
import cm.entity.Student;
import cm.entity.Teacher;
import cm.service.*;
import cm.util.SystemConfigUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class JobController {
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

	/**
	 * 我的作业
	 *
	 * @return
	 */
	@RequestMapping(value = "student/jobs",method = RequestMethod.GET)
	public String jobList(Model model,HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		List<Job> jobs = jobService.findJobs(student, student.getCuState());
		model.addAttribute("jobs", jobs);
		return "student/jobList";
	}

	/**
	 * 删除作业
	 *
	 * @return
	 */
	@RequestMapping(value = "student/job/delete/{id}",method = RequestMethod.GET)
	public String jobDelete(@PathVariable UUID id,HttpSession session) {
		Job job = jobService.get(id);
		if(job != null){


			String path = SystemConfigUtil.getRoot(configuration)+job.getPath()+job.getJobName();
			File file= new File(path);
			file.delete();
			jobService.delete(job);
		}
		return "redirect:/student/jobs";
	}
}

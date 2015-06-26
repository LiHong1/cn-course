package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.commons.exception.AuthException;
import cm.commons.util.ZipUtil;
import cm.entity.*;
import cm.service.*;
import cm.util.SystemConfigUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class FileDownloadController {
	BufferedInputStream bis = null;
	BufferedOutputStream bos = null;
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

	@RequestMapping(value = "student/material/download/{id}",method = RequestMethod.GET)
	public ModelAndView getMaterialFile(@PathVariable UUID id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Material material = materialService.get(id);
		String root = SystemConfigUtil.getRoot(configuration);
		if(material.getType()==0L){
			root = request.getSession().getServletContext().getRealPath("/");
		}
		String downLoadPath = root +material.getMaterialPath() + material.getId() + material.getSuffix();
		File file = new File(downLoadPath);
		response.setContentType("application/octet-stream; charset=UTF-8");

		//设置文件输出类型
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(material.getName().getBytes("utf-8"), "ISO8859-1"));
		//设置输出长度
		response.setHeader("Content-Length", String.valueOf(file.length()));
		//获取输入流
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		//输出流
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		//关闭流
		bis.close();
		bos.close();
		return null;
	}

	/*public String materialFile(UUID id,HttpSession session) {
		Material material = materialService.get(id);
		Person person = (Person) session.getAttribute("user");
		if (person instanceof Student) {
			Student student = (Student) person;
			State state = student.getCuState();
			if (!state.getId().equals(material.getState().getId())) {
				throw new AuthException("你没有权限下载该资源");
			}
		}
		return "getMaterial";
	}*/

	@RequestMapping(value = "teacher/student/job/download/{id}",method = RequestMethod.GET)
	public ModelAndView getJobFile(@PathVariable UUID id,HttpServletResponse response)  {
		Job job = jobService.get(id);
		String downLoadPath = SystemConfigUtil.getRoot(configuration)+job.getPath() + job.getJobName();
		File file = new File(downLoadPath);
		response.setContentType("application/octet-stream; charset=UTF-8");

		try{
			//设置文件输出类型
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(job.getJobName().getBytes("utf-8"), "ISO8859-1"));
			//设置输出长度
			response.setHeader("Content-Length", String.valueOf(file.length()));

			//获取输入流
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			//输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			//关闭流
			bis.close();
			bos.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "teacher/students/jobs/download",method = RequestMethod.GET)
	public ModelAndView getJobsFile(HttpServletResponse response,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = teacher.getCuState();
		String root = SystemConfigUtil.getRoot(configuration);
		root = root+("file"+File.separator+"job"+File.separator + state.getId());
		File srcFiles = new File(root);
		String name = state.getClas().getName();
		File zip = new File(session.getServletContext().getRealPath("") + File.separator+"temp.zip");
		try{
			ZipUtil.ZipFiles(zip, name, srcFiles.listFiles());
			response.setContentType("application/zip; charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((name + ".zip").getBytes("utf-8"), "ISO8859-1"));
			//设置输出长度
			response.setHeader("Content-Length", String.valueOf(zip.length()));
			System.out.println(zip.length());
			//获取输入流
			bis = new BufferedInputStream(new FileInputStream(zip));
			//输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			//关闭流
			bis.close();
			bos.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public String setCode(String displayFileName,HttpServletRequest request) throws UnsupportedEncodingException {
		String agent = request.getHeader("user-agent");
		if (agent.contains("Firefox"))
			return new String(displayFileName.getBytes("GB2312"), "ISO-8859-1");
		else
			return URLEncoder.encode(displayFileName, "UTF-8");
	}
}

package cm.controller;

import cm.commons.config.SystemConfiguration;
import cm.commons.converter.docConverter.DocConverter;
import cm.commons.converter.pdfConverter.OpenOfficePDFConverter;
import cm.commons.converter.pdfConverter.PDFConverter;
import cm.commons.converter.swfConverter.SWFConverter;
import cm.commons.converter.swfConverter.SWFToolsSWFConverter;
import cm.entity.*;
import cm.service.*;
import cm.util.SystemConfigUtil;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
public class MaterialController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private MaterialService materialService;
	@Autowired
    private SystemConfiguration configuration;

	@RequestMapping(value = "teacher/materials",method = RequestMethod.GET)
	public String teacherMaterialList(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		State state = teacher.getCuState();
		List<Material> materialList = materialService.findByState(state);
		model.addAttribute("materialList", materialList);
		return "material/materialList";
	}


	@RequestMapping(value = "teacher/material/delete/{id}",method = RequestMethod.GET)
	public String courseMaterialDelete(@PathVariable UUID id) {
		deleteFile(id, SystemConfigUtil.getRoot(configuration));
		return "redirect:/teacher/materials";
	}

	@RequestMapping(value = "teacher/case/material/delete/{id}",method = RequestMethod.GET)
	public String caseMaterialDelete(@PathVariable UUID id) {
		Material material = deleteFile(id,SystemConfigUtil.getRoot(configuration));
		return "redirect:/teacher/case/update/"+material.getCases().getId();
	}

	/**
	 * 学习材料
	 *
	 * @return
	 */
	@RequestMapping(value = "student/materials",method = RequestMethod.GET)
	public String studentMaterialList(Model model,HttpSession session) {
		Student student = (Student) session.getAttribute("user");
		student = studentService.get(student.getId());
		List<Material> materialList = materialService.findByState(student.getCuState());
		model.addAttribute("materialList", materialList);
		return "material/list";
	}

	public Material deleteFile(UUID id,String realPath) {
		Material material = materialService.get(id);
		File file = new File(realPath + material.getMaterialPath() + material.getId());
		if (file.exists())
			file.delete();
		materialService.delete(id);
		return material;
	}

	@RequestMapping(value = "student/material/show/{id}",method = RequestMethod.GET)
	public String show(@PathVariable UUID id,Model model,HttpSession session) {
		Material material = materialService.get(id);
		PDFConverter pdfConverter = new OpenOfficePDFConverter();
		SWFConverter swfConverter = new SWFToolsSWFConverter();
		DocConverter converter = new DocConverter(pdfConverter, swfConverter);
		String path = material.getMaterialPath();
		String root = session.getServletContext().getRealPath("");
		converter.convert(root.substring(0,root.length())+path + material.getId() + material.getSuffix());
		model.addAttribute("file", path + material.getId() + ".swf");
		return "public/play";
	}

	@RequestMapping(value = "student/material/player/{id}",method = RequestMethod.GET)
	public String player(@PathVariable UUID id,Model model) {
		Material material = materialService.get(id);
		String root = SystemConfigUtil.getRoot(configuration);
		String file = material.getMaterialPath() + material.getId() + material.getSuffix();
		//文件路径为相对路径才行
		model.addAttribute("file", file.substring(1,file.length()));
		return "student/player";
	}

}

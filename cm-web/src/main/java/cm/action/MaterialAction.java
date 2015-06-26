package cm.action;


import cm.auth.AuthClass;
import cm.auth.AuthMethod;
import cm.commons.converter.docConverter.DocConverter;
import cm.commons.converter.pdfConverter.OpenOfficePDFConverter;
import cm.commons.converter.pdfConverter.PDFConverter;
import cm.commons.converter.swfConverter.SWFConverter;
import cm.commons.converter.swfConverter.SWFToolsSWFConverter;
import cm.entity.Material;
import cm.entity.State;
import cm.entity.Student;
import cm.entity.Teacher;
import cm.service.MaterialService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@Scope("prototype")
@AuthClass
public class MaterialAction extends BaseAction<Material> {
    @Autowired
    private MaterialService materialService;

    @AuthMethod(role = "teacher")
    public String addUI() {
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("user");
        State state = teacher.getCuState();
        List<Material> materialList = materialService.findByState(state);
        ActionContext.getContext().put("materialList", materialList);
        return "addUI";
    }

    @Override
    public void addActionError(String anErrorMessage) {
        if (anErrorMessage.startsWith("the request was rejected because its size")) {
            super.addActionError(getText("struts.multipart.maxSize.limit"));
        } else
            super.addActionError(anErrorMessage);
    }

    @AuthMethod(role = "teacher")
    public String delete() {
        deleteFile();
        return "toAddUI";
    }

    public String caseMatedelete() {
        Material material = deleteFile();
        model.setId(material.getCases().getId());
        return "toCase";
    }

    /**
     * 学习材料
     *
     * @return
     */
    public String list() {
        Student student = (Student) ActionContext.getContext().getSession().get("user");
        student = studentService.get(student.getId());
        List<Material> materialList = materialService.findByState(student.getCuState());
        ActionContext.getContext().put("materialList", materialList);
        return "list";
    }

    public Material deleteFile() {
        Material material = materialService.get(model.getId());
        File file = new File(ServletActionContext.getServletContext().getRealPath("/") + material.getMaterialPath() + material.getId());
        if (file.exists())
            file.delete();
        materialService.delete(model.getId());
        return material;
    }

    public String show() {
        Material material = materialService.get(model.getId());
        String webRoot = ServletActionContext.getServletContext().getRealPath("/");
        PDFConverter pdfConverter = new OpenOfficePDFConverter();
        SWFConverter swfConverter = new SWFToolsSWFConverter();
        DocConverter converter = new DocConverter(pdfConverter, swfConverter);
        String path = material.getMaterialPath();
        converter.convert(webRoot + path + material.getId() + material.getSuffix());
        ActionContext.getContext().put("file", path + material.getId() + ".swf");
        return "show";
    }

    public String player() throws UnsupportedEncodingException {

        Material material = materialService.get(model.getId());
        String file = material.getMaterialPath() + material.getId() + material.getSuffix();
        file = file.substring(1, file.length());
        //文件路径为相对路径才行
        ActionContext.getContext().put("file", file);
        return "player";
    }

}

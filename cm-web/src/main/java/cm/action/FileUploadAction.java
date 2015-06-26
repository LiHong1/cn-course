package cm.action;


import cm.entity.*;
import cm.service.CaseService;
import cm.service.JobService;
import cm.service.MaterialService;
import cm.service.StateService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Controller
@Scope("prototype")
public class FileUploadAction extends ActionSupport {
    @Resource
    private StateService stateService;
    @Resource
    private JobService jobService;
    @Resource
    private MaterialService materialService;
    @Resource
    private CaseService caseService;
    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    private String name;
    private String id;
    //提交过来的file的名字
    private String fileFileName;
    //提交过来的file的MIME类型
    private String fileContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public String execute() throws Exception {
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("user");
        State state = stateService.get(teacher.getCuState().getId());
        //绝对路径
        String root = ServletActionContext.getServletContext().getRealPath("/file/learnMaterial/" + state.getId());
        //相对路径
        String path = "/file/learnMaterial/" + state.getId() + "/";
        if (file != null) {
            Material material = new Material();
            if (getType(fileFileName) == 0) {
                root = ServletActionContext.getServletContext().getRealPath("/file/video/" + state.getId());
                //fileFileName=material.getId()+"."+cn.commons.util.FileUtils.getFileSufix(fileFileName);
                path = "/file/video/" + state.getId() + "/";
            }
            if (name == null || name.equals(""))
                name = fileFileName;
            material.setMaterialPath(path);
            material.setName(name);
            material.setOriginalName(fileFileName);
            material.setState(state);
            material.setType(getType(fileFileName));
            System.out.println(FilenameUtils.getExtension(fileFileName));
            material.setSuffix("." + FilenameUtils.getExtension(fileFileName));
            materialService.save(material);
            state.getMaterials().add(material);
            stateService.update(state);
            File savefile = new File(new File(root), material.getId() + material.getSuffix());
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
        }
        return "material";

    }

    public String job() throws Exception {
        Student student = (Student) ActionContext.getContext().getSession().get("user");
        State state = stateService.get(student.getCuState().getId());
        String root = ServletActionContext.getServletContext().getRealPath("/file/job/" + state.getId());
        String suffix = fileFileName.substring(fileFileName.lastIndexOf("."), fileFileName.length());
        this.setName(student.getNumber() + "_" + student.getName() + "_" + this.getName() + suffix);
        fileFileName = this.getName();
        if (file != null) {
            File savefile = new File(new File(root), fileFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
            Job job = new Job();
            job.setJobName(this.getName());
            job.setPath("/file/job/" + state.getId() + "/");
            job.setState(state);
            job.setStudent(student);
            jobService.save(job);

        }
        return "job";

    }

    public String caseMaterial() throws IOException {
        Case cas = caseService.get(id);
        String root = ServletActionContext.getServletContext().getRealPath("/file/case/" + cas.getId() + "/");
        String suffix = fileFileName.substring(fileFileName.lastIndexOf("."), fileFileName.length());
        this.setName(this.getName() + suffix);
        fileFileName = this.getName();
        if (file != null) {
            Material material = new Material();
            material.setName(name);
            material.setOriginalName(fileFileName);
            material.setCases(cas);
            material.setType(getType(fileFileName));
            material.setMaterialPath("/file/case/" + cas.getId() + "/");
            materialService.save(material);
            File savefile = new File(new File(root), material.getId() + material.getSuffix());
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
            cas.getMaterials().add(material);
            caseService.update(cas);
        }
        return "caseMaterial";
    }

    public long getType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        System.out.println("suffix==" + suffix);
        if (suffix.equals(".flv") || suffix.equals(".mp4"))
            return 0L;
        if (suffix.equals(".doc") || suffix.equals(".pdf") || suffix.equals(".txt") || suffix.equals(".ppt")
                || suffix.equals(".pptx") || suffix.equals(".docx"))
            return 1L;
        return 2L;
    }
}
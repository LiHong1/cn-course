package cm.action;

import cm.commons.exception.AuthException;
import cm.commons.util.ZipUtil;
import cm.entity.*;
import cm.service.JobService;
import cm.service.MaterialService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@Scope("prototype")
public class FileDownloadAction extends ActionSupport {
    @Resource
    MaterialService materialService;
    @Resource
    JobService jobService;
    private String id;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InputStream getMaterialFile() throws UnsupportedEncodingException {
        Material material = materialService.get(id);
        String name;
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
        if (isFireFox) {
            name = new String(material.getName().getBytes("UTF-8"), "ISO8859-1");
        } else {
            name = java.net.URLEncoder.encode(material.getName(), "UTF-8");
        }

        ActionContext.getContext().put("name", name);
        InputStream materialFile = ServletActionContext
                .getServletContext()
                .getResourceAsStream(
                        material.getMaterialPath() + material.getId() + material.getSuffix());
        return materialFile;
    }

    public String materialFile() {
        Material material = materialService.get(id);
        Person person = (Person) ActionContext.getContext().getSession().get("user");
        if (person instanceof Student) {
            Student student = (Student) person;
            State state = student.getCuState();
            if (!state.getId().equals(material.getState().getId())) {
                throw new AuthException("你没有权限下载该资源");
            }
        }
        return "getMaterial";
    }

    public String jobFile() {
        return "getJob";
    }

    public String jobsFile() {
        return "getJobs";
    }

    public InputStream getJobFile() throws IOException {
        Job job = jobService.get(id);
        String name = setCode(job.getJobName());
        ActionContext.getContext().put("name", name);
        InputStream jobFile = ServletActionContext.getServletContext()
                .getResourceAsStream(job.getPath() + job.getJobName());
        return jobFile;
    }

    public InputStream getJobsFile() throws IOException {
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("user");
        State state = teacher.getCuState();
        String root = ServletActionContext.getServletContext().getRealPath("/file/job/" + state.getId());
        File srcFiles = new File(root);
        String name = setCode(state.getClas().getName() + ".zip");
        ActionContext.getContext().put("name", name);
        File zip = new File(ServletActionContext.getServletContext().getRealPath("") + "/temp.zip");
        ZipUtil.ZipFiles(zip, state.getClas().getName(), srcFiles.listFiles());
        InputStream jobsFile = ServletActionContext.getServletContext()
                .getResourceAsStream("/temp.zip");
        return jobsFile;
    }

    public String setCode(String displayFileName) throws UnsupportedEncodingException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String agent = request.getHeader("user-agent");
        if (agent.contains("Firefox"))
            return new String(displayFileName.getBytes("GB2312"), "ISO-8859-1");
        else
            return URLEncoder.encode(displayFileName, "UTF-8");
    }

}
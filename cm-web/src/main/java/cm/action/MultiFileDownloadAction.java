package cm.action;


import cm.entity.Material;
import cm.service.MaterialService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Controller
@Scope("prototype")
public class MultiFileDownloadAction extends ActionSupport {
    @Resource
    MaterialService materialService;
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

    public InputStream getDownloadFile() throws UnsupportedEncodingException {
        Material material = materialService.get(id);
        String name = java.net.URLEncoder.encode(material.getName(), "UTF-8");
        ActionContext.getContext().put("name", name);
        InputStream in = ServletActionContext.getServletContext().getResourceAsStream(material.getMaterialPath() + material.getId() + material.getSuffix());
        return in;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
package cm.action;


import cm.auth.AuthClass;
import cm.commons.config.SystemConfiguration;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 案例Action
 *
 * @author li hong
 */

@Controller
@Scope("prototype")
@AuthClass(value = "manage")
public class SystemAction extends ActionSupport {
    @Autowired
    private SystemConfiguration configuration;
    private Boolean loginAble;
    private Boolean replaceMachineAble;
    private String size;
    private String startTermTime;

    public Boolean getLoginAble() {
        return loginAble;
    }

    public void setLoginAble(Boolean loginAble) {
        this.loginAble = loginAble;
    }

    public Boolean getReplaceMachineAble() {
        return replaceMachineAble;
    }

    public void setReplaceMachineAble(Boolean replaceMachineAble) {
        this.replaceMachineAble = replaceMachineAble;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStartTermTime() {
        return startTermTime;
    }

    public void setStartTermTime(String startTermTime) {
        this.startTermTime = startTermTime;
    }

    public String save() {
        configuration.clearProperty("loginAble");
        configuration.clearProperty("replaceMachineAble");
        configuration.clearProperty("jobArea");
        configuration.clearProperty("startTerm");

        configuration.addProperty("loginAble", loginAble);
        configuration.addProperty("replaceMachineAble", replaceMachineAble);
        configuration.addProperty("jobArea", size);
        configuration.addProperty("startTerm", startTermTime);
        ActionContext.getContext().getApplication().put("loginAble", loginAble.toString());
        ActionContext.getContext().getApplication().put("replaceMachineAble", replaceMachineAble.toString());
        ActionContext.getContext().getApplication().put("jobArea", size);
        ActionContext.getContext().getApplication().put("startTerm", startTermTime);
        return "success";
    }

}

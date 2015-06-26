package cm.action;


import cm.auth.AuthClass;
import cm.commons.config.SystemConfiguration;
import cm.entity.Manage;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
@AuthClass("manage")
public class ManageAction extends BaseAction<Manage> {
    @Autowired
    private SystemConfiguration systemConfiguration;

    /**
     * 班级管理
     *
     * @return
     */
    public String classSet() {

        return "classSet";
    }

    /**
     * 系统设置
     *
     * @return
     */
    public String system() {
        String loginAble = (String) systemConfiguration.getProperty("loginAble");
        String replaceMachineAble = (String) systemConfiguration.getProperty("replaceMachineAble");
        String size = (String) systemConfiguration.getProperty("jobArea");
        String startTermTime = (String) systemConfiguration.getProperty("startTerm");
        ActionContext.getContext().put("loginAble", loginAble);
        ActionContext.getContext().put("replaceMachineAble", replaceMachineAble);
        ActionContext.getContext().put("size", size);
        ActionContext.getContext().put("startTermTime", startTermTime);
        return "system";
    }


}

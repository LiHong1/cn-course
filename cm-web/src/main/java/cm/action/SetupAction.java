package cm.action;


import cm.commons.config.SystemConfiguration;
import cm.entity.Manage;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Scope("prototype")
public class SetupAction extends ActionSupport {
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private SystemConfiguration systemConfig;

    @Override
    public String execute() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Manage user = new Manage();
        user.setNumber("admin");
        user.setName("超级管理员");
        user.setPassword(DigestUtils.md5Hex("0000"));
        session.save(user); // 保存
        systemConfig.clear();
        systemConfig.addProperty("loginAble", false);
        systemConfig.addProperty("replaceMachineAble", false);
        systemConfig.addProperty("jobArea", 12);

        systemConfig.addProperty("startTerm", (new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        return null;
    }


}
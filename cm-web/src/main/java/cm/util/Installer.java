package cm.util;

import cm.entity.Manage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.configuration.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Installer {

    private static ApplicationContext ac;
    private static Configuration config;
    @Resource
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        ac = new ClassPathXmlApplicationContext("*applicationContext.xml");
        //SessionFactory sessionFactory=ac.getBean(SessionFactory.class);
        Installer installer = (Installer) ac.getBean("installer");
        System.out.println(installer);
        installer.install();
    }

    /**
     * 执行安装
     */
    @Transactional
    public void install() {
        Session session = sessionFactory.getCurrentSession();

        //ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        // ==============================================================
        // 保存超级管理员用户
        Manage user = new Manage();
        user.setNumber("admin");
        user.setName("超级管理员");
        user.setPassword(DigestUtils.md5Hex("0000"));
        session.save(user); // 保存

        config = (Configuration) ac.getBean("systemConfiguration");
        config.clear();
        config.addProperty("loginAble", false);
        config.addProperty("replaceMachineAble", false);
        config.addProperty("jobArea", 12);
        config.addProperty("startTerm", (new SimpleDateFormat("yyyy-MM-dd").format(new Date())));

        //	  config.clear();
        //Operation operation;
//		operation=new Operation("Student_show", "获得学生详细信息","学生模块");
//		session.save(operation);
//		operation=new Operation("CaseAction_list", "列出案例","案例模块");
//		session.save(operation);
//		operation=new Operation("CaseAction_update", "修改案例","案例模块");
//		session.save(operation);
//		operation=new Operation("CaseAction_delete", "删除案例","案例模块");
//		session.save(operation);
//		operation=new Operation("CaseAction_add", "增加案例","案例模块");
//		session.save(operation);
//		operation=new Operation("UserAction_login", "用户登录","用户模块");
//		session.save(operation);
//		operation=new Operation("UserAction_logout", "用户退出","用户模块");
//		session.save(operation);
//
//		operation=new Operation("PaperAction_list", "列出试卷","考试模块");
//		session.save(operation);
//		operation=new Operation("PaperAction_add", "增加试卷","考试模块");
//		session.save(operation);
//		operation=new Operation("PaperAction_update", "修改试卷","考试模块");
//		session.save(operation);
//		operation=new Operation("PaperAction_delete", "删除试卷","考试模块");
//		session.save(operation);
//

    }
}

package cm.web;

import cm.commons.bean.MenuItem;
import cm.commons.config.SystemConfiguration;
import cm.commons.service.MenuService;
import cm.commons.util.MDCoder;
import cm.entity.Manage;
import cm.service.ManageService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by li hong on 2015/6/24.
 */
@WebServlet(name = "SetupServlet")
public class SetupServlet extends HttpServlet {
	private static WebApplicationContext wc;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//初始化spring的工厂
				wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		String relPath = request.getContextPath();
		System.out.println(relPath);

		MenuService menuService = (MenuService)wc.getBean(MenuService.class);
		ManageService manageService = (ManageService) wc.getBean(ManageService.class);
		SystemConfiguration config = wc.getBean(SystemConfiguration.class);
		MenuItem menuItem1 = new MenuItem(relPath+"/manage/courses",1,"课程列表","manage");
		menuItem1.setId(UUID.randomUUID());

		MenuItem menuItem2 = new MenuItem(relPath+"/manage/teachers",2,"教师管理","manage");
		menuItem2.setId(UUID.randomUUID());

		MenuItem menuItem3 = new MenuItem(relPath+"/manage/classes",3,"班级管理","manage");
		menuItem3.setId(UUID.randomUUID());

		MenuItem menuItem4 = new MenuItem(relPath+"/manage/students",4,"学生管理","manage");
		menuItem4.setId(UUID.randomUUID());

		MenuItem menuItem5 = new MenuItem(relPath+"/manage/menus",5,"菜单管理","manage");
		menuItem5.setId(UUID.randomUUID());

		MenuItem menuItem6 = new MenuItem(relPath+"/manage/system",6,"系统管理","manage");
		menuItem6.setId(UUID.randomUUID());

		MenuItem menuItem7 = new MenuItem(relPath+"/changePassword",11,"更改口令","user");
		menuItem7.setId(UUID.randomUUID());

		MenuItem menuItem8 = new MenuItem(relPath+"/logout",12,"退出","user");
		menuItem8.setId(UUID.randomUUID());

		menuService.save(menuItem1);
		menuService.save(menuItem2);
		menuService.save(menuItem3);
		menuService.save(menuItem4);
		menuService.save(menuItem5);
		menuService.save(menuItem6);
		menuService.save(menuItem7);
		menuService.save(menuItem8);
		 Manage manage = new Manage();
        manage.setId(UUID.randomUUID());
        manage.setName("admin");
        manage.setNumber("admin");
        manage.setPassword(MDCoder.encodeMD5Hex("0000"));
        manageService.save(manage);

		config.addProperty("loginAble", false);
		config.addProperty("replaceMachineAble", false);
		config.addProperty("jobArea", 12);
		config.addProperty("startTerm", (new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
		config.addProperty("fileRoot","");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
	}
	public static WebApplicationContext getWc() {
		return wc;
	}
}

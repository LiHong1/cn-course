package cm.web;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cm.commons.bean.MenuItem;
import cm.commons.service.MenuService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		//初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		String relPath = wc.getServletContext().getContextPath();
		System.out.println(relPath);
		MenuService menuService = (MenuService)wc.getBean(MenuService.class);
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

		/*menuService.save(menuItem1);
		menuService.save(menuItem2);
		menuService.save(menuItem3);
		menuService.save(menuItem4);
		menuService.save(menuItem5);
		menuService.save(menuItem6);
		menuService.save(menuItem7);
		menuService.save(menuItem8);*/


	}

	public static WebApplicationContext getWc() {
		return wc;
	}

}

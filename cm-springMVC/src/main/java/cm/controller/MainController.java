package cm.controller;
import cm.commons.bean.MenuItem;
import cm.commons.service.MenuService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by li hong on 2015/5/19.
 */
@Controller
@RequestMapping("/main")
public class MainController {
		@Autowired
		private MenuService menuService;
		protected static Logger logger = Logger.getLogger("controller");

		/**
		 * 跳转到adminpage页面
		 *
		 * @return
		 */
		@RequestMapping(value = "/menu", method = RequestMethod.GET)
		public String getAadminPage(Model model) {
			CacheManager manager = CacheManager.newInstance();
			Cache menuCache = manager.getCache("menuCache");
			Object object = menuCache.get("menuItems");
			if(object == null){
				List<MenuItem> menuItems = menuService.getAll();
				menuCache.put(new Element("menuItems",menuItems));
			}
			model.addAttribute("menuItems",menuCache.get("menuItems").getObjectValue());
			return "public/menu";
		}

}

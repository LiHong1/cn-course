package cm.controller;

import cm.bean.AjaxObj;
import cm.commons.PageBean;
import cm.commons.bean.MenuItem;
import cm.commons.config.SystemConfiguration;
import cm.commons.service.MenuService;
import cm.commons.util.MDCoder;
import cm.dto.SystemDto;
import cm.entity.Course;
import cm.entity.State;
import cm.entity.Student;
import cm.entity.Teacher;
import cm.service.*;
import cm.util.CacheUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manage/")
@PreAuthorize("hasRole('ROLE_MANAGE')")
public class MenuController {
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "menu/add",method = RequestMethod.GET)
	public @ResponseBody AjaxObj addMenu(MenuItem menuItem,HttpSession session){
		menuItem.setId(UUID.randomUUID());
		menuService.save(menuItem);
		CacheUtil.updateMenuCatche(menuService.getAll());
		return new AjaxObj(1,"SUCCESS",menuItem);
	}

	@RequestMapping(value = "menu/update",method = RequestMethod.GET)
	public @ResponseBody AjaxObj updateMenu(MenuItem menuItem,HttpSession session){
		MenuItem menuItem1 = menuService.get(menuItem.getId());
		menuItem1.setAuthority(menuItem.getAuthority());
		menuItem1.setUrl(menuItem.getUrl());
		menuItem1.setName(menuItem.getName());
		menuService.update(menuItem1);
		CacheUtil.updateMenuCatche(menuService.getAll());

		return new AjaxObj(1,"SUCCESS",menuItem);
	}

	@RequestMapping(value = "menu/delete/{id}",method = RequestMethod.GET)
	public @ResponseBody AjaxObj   deleteMenu(@PathVariable UUID id,HttpSession session){
		menuService.delete(id);
		CacheUtil.updateMenuCatche(menuService.getAll());
		return new AjaxObj(1,"SUCCESS");
	}


	@RequestMapping(value = "menu/updateSort",method = RequestMethod.GET)
	public @ResponseBody AjaxObj updateSort(String [] ids,HttpSession session){
		System.out.println("dg");
		UUID uuids[];
		if(ids != null){
			uuids = new UUID[ids.length];
			for(int i=0; i < ids.length; i++){
				uuids[i]=UUID.fromString(ids[i]);
				System.out.println(uuids[i]);
			}
			menuService.updateSort(uuids);
		}
        CacheUtil.updateMenuCatche(menuService.getAll());
		return new AjaxObj(1,"SUCCESS");
	}

	@RequestMapping(value = "menu/apply",method = RequestMethod.GET)
	public String   refreshMenu(HttpSession session){
		CacheUtil.updateMenuCatche(menuService.getAll());
		return "redirect:/manage/menus";
	}

	@RequestMapping(value = "menus",method = RequestMethod.GET)
	public String menus(Model model,HttpSession session){
		model.addAttribute("menuItems", CacheUtil.getMenuCatche(menuService));
		return "manage/menus";
	}
}

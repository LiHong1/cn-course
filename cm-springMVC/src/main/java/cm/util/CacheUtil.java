package cm.util;

import cm.commons.bean.MenuItem;
import cm.commons.config.SystemConfiguration;
import cm.commons.service.MenuService;
import cm.dto.SystemDto;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

/**
 * Created by li hong on 2015/6/25.
 */
public class CacheUtil {

	public static SystemDto getSystemConfiguration(SystemConfiguration configuration) {
		CacheManager manager = CacheManager.newInstance();
		Cache systemConfigCache = manager.getCache("systemConfigCache");
		Object object = systemConfigCache.get("systemConfig");

		if(object == null){
			SystemDto systemDto;
			systemDto = new SystemDto();
			String loginAble = (String) configuration.getProperty("loginAble");
			String replaceMachineAble = (String) configuration.getProperty("replaceMachineAble");
			String size = (String) configuration.getProperty("jobArea");
			String startTermTime = (String) configuration.getProperty("startTerm");
			String fileRoot = (String) configuration.getProperty("fileRoot");
			systemDto.setLoginAble(Boolean.parseBoolean(loginAble));
			systemDto.setReplaceMachineAble(Boolean.parseBoolean(replaceMachineAble));
			systemDto.setJobArea(Integer.parseInt(size));
			systemDto.setStartTermTime(startTermTime);
			systemDto.setFileRoot(fileRoot);
			systemConfigCache.put(new Element("systemConfig", systemDto));
		}
		return (SystemDto)systemConfigCache.get("systemConfig").getObjectValue();
	}
	public static Object getMenuCatche(MenuService menuService){
		CacheManager manager = CacheManager.newInstance();
		Cache menuCache = manager.getCache("menuCache");
		Object object = menuCache.get("menuItems");
		if(object == null){
			List<MenuItem> menuItems = menuService.getAll();
			menuCache.put(new Element("menuItems",menuItems));
		}
		return menuCache.get("menuItems").getObjectValue();
	}
	public static void updateMenuCatche(List<MenuItem> menuItems){
		CacheManager manager = CacheManager.newInstance();
		Cache menuCache = manager.getCache("menuCache");
		menuCache.put(new Element("menuItems",menuItems));
		Object object = menuCache.get("menuItems");
	}
}

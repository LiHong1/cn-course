package cm.util;

import cm.commons.config.SystemConfiguration;
import cm.dto.SystemDto;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by li hong on 2015/6/11.
 */
public class SystemConfigUtil {
	
	public static String getRoot(SystemConfiguration configuration){
		SystemDto systemDto;
		CacheManager manager = CacheManager.newInstance();
		Cache systemConfigCache = manager.getCache("systemConfigCache");
		Object object = systemConfigCache.get("systemConfig");
		if(object ==null){
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
			systemConfigCache.put(new Element("systemConfig",systemDto));
		}
		systemDto = (SystemDto)systemConfigCache.get("systemConfig").getObjectValue();
		return systemDto.getFileRoot();
	}
}

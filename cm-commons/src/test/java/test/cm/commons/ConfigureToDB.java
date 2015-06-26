package test.cm.commons;

import cm.commons.dao.ConfigurationDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;

//import cn.commons.config.Config;
//import cn.commons.config.SystemConfiguration;

public class ConfigureToDB {
    private static final Log logger = LogFactory.getLog(ConfigureToDB.class);
    private static ApplicationContext ctx;
    //private static SystemConfiguration config;
    private static ConfigurationDao configDao;

    public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException,
            IllegalAccessException, NoSuchFieldException, SecurityException {
        // ctx = new ClassPathXmlApplicationContext(new String[] {
        // "memberApplicationContextTest.xml" });
        // config = (SystemConfiguration) ctx.getBean("config");
        // configDao = (ConfigurationDao) ctx.getBean("configDao");

        // commons模块
        logger.info("同步Commons模块错误码......");

        Class<?> clazz = Class.forName("com.ciis.commons.CommonsConfigKey");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // System.out.println(field);
            String fieldName = field.getName();
            if (fieldName.endsWith("_DEF")) {
                //System.out.println(field.getInt(clazz));
                continue;
            }

            System.out.println(field.get(clazz));

            Field defaultValueField = clazz.getField(fieldName + "_DEF");
            System.out.println(defaultValueField.get(clazz));

            //Config.setProperty(field.get(clazz).toString(), defaultValueField.get(clazz));

        }

        logger.info("同步Commons模块错误码结束。");

    }

}

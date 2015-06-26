package cm.commons.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * load properties
 *
 * @author zhuyx
 */
public class EditConfiguration {

    private static final String CONFIG_FILE = "/xnews-editor.properties";
    private static final Log LOG = LogFactory.getLog(EditConfiguration.class);
    private static EditConfiguration config;

    static {
        String mypath = EditConfiguration.class.getResource(CONFIG_FILE).getFile();

        config = new EditConfiguration();
        config.loadProperties(mypath);
    }

    private Properties props;

    /**
     * 获取配置静态实例
     *
     * @return
     */
    public static EditConfiguration getInstance() {
        return config;
    }

    /**
     * 载入键值
     *
     * @param filePath
     */
    public void loadProperties(String filePath) {
        props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            in.close();
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    /**
     * 根据指定的键值获取该键对应的值
     *
     * @param key 键
     * @return 返回键对应的值
     */
    public String getValue(String key) {
        return this.props.getProperty(key);
    }

    /**
     * 获取所有键及键值
     *
     * @return 返回键值的map
     */
    public Map<String, String> getValues() {
        Enumeration<?> en = props.propertyNames();
        Map<String, String> map = new HashMap<String, String>();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String property = props.getProperty(key);
            map.put(key, property);
        }
        return map;
    }

    /**
     * 设置键值
     *
     * @param key   键
     * @param value 值
     */
    public void setValue(String key, String value) {
        this.props.setProperty(key, value);

        String mypath = EditConfiguration.class.getResource(CONFIG_FILE).getFile();
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(mypath));
            this.props.store(out, "cheleon editor configuration");
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            LOG.debug(ex.getMessage());
        } catch (IOException e) {
            LOG.debug(e.getMessage());
        }
    }

}

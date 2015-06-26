package cm.commons.config;

import cm.commons.bean.ConfigurationItem;
import cm.commons.dao.ConfigurationDao;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.PropertyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 系统配置服务
 *
 * @author leizhenchun
 */
@Service
public class SystemConfiguration extends AbstractConfiguration {
    @Autowired
    private ConfigurationDao configDao;


    public boolean isEmpty() {
        return configDao.getKeyCount() == 0;
    }


    public boolean containsKey(String key) {
        ConfigurationItem item = configDao.get(key);
        return item != null;
    }


    public Object getProperty(String key) {
        ConfigurationItem item = configDao.get(key);
        if (item == null) {
            return null;
        }
        String value = item.getValue();

        Object result = null;
        List<Object> results = new ArrayList<Object>();

        if (isDelimiterParsingDisabled()) {
            results.add(value);
        } else {
            Iterator<?> it = PropertyConverter.toIterator(value, getListDelimiter());
            while (it.hasNext()) {
                results.add(it.next());
            }
        }

        if (!results.isEmpty()) {
            result = (results.size() > 1) ? results : results.get(0);
        }

        return result;
    }


    public Iterator<String> getKeys() {
        List<ConfigurationItem> itemlist = configDao.getAll();
        List<String> result = new ArrayList<String>();
        for (ConfigurationItem item : itemlist) {
            result.add(item.getKey());
        }
        return result.iterator();
    }

    @Override
    protected void addPropertyDirect(String key, Object obj) {
        configDao.save(key, String.valueOf(obj));
    }


    public void addProperty(String key, Object value) {
        boolean parsingFlag = isDelimiterParsingDisabled();
        try {
            if (value instanceof String) {
                setDelimiterParsingDisabled(true);
            }
            super.addProperty(key, value);
        } finally {
            setDelimiterParsingDisabled(parsingFlag);
        }
    }


    protected void clearPropertyDirect(String key) {
        ConfigurationItem item = configDao.get(key);
        if (item != null) {
            configDao.delete(item);
        }
    }


}

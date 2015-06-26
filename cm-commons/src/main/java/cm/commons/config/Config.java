package cm.commons.config;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class Config {
    private static Configuration configuration;

    public static Configuration getInstance() {
        return configuration;
    }

    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        if (configuration != null) {
            return configuration.getBigDecimal(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static byte getByte(String key, byte defaultValue) {
        if (configuration != null) {
            return configuration.getByte(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static int getInteger(String key, int defaultValue) {
        if (configuration != null) {
            return configuration.getInteger(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Integer getInteger(String key, Integer defaultValue) {
        if (configuration != null) {
            return configuration.getInteger(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static int getInt(String key, int defaultValue) {
        if (configuration != null) {
            return configuration.getInt(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Integer getInt(String key, Integer defaultValue) {
        if (configuration != null) {
            return configuration.getInt(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
        if (configuration != null) {
            return configuration.getBigInteger(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (configuration != null) {
            return configuration.getBoolean(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        if (configuration != null) {
            return configuration.getBoolean(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static double getDouble(String key, double defaultValue) {
        if (configuration != null) {
            return configuration.getDouble(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Double getDouble(String key, Double defaultValue) {
        if (configuration != null) {
            return configuration.getDouble(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static float getFloat(String key, float defaultValue) {
        if (configuration != null) {
            return configuration.getFloat(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Float getFloat(String key, Float defaultValue) {
        if (configuration != null) {
            return configuration.getFloat(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static List<Object> getList(String key, List<Object> defaultValue) {
        if (configuration != null) {
            return configuration.getList(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static long getLong(String key, long defaultValue) {
        if (configuration != null) {
            return configuration.getLong(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Long getLong(String key, Long defaultValue) {
        if (configuration != null) {
            return configuration.getLong(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static short getShort(String key, short defaultValue) {
        if (configuration != null) {
            return configuration.getShort(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static Short getShort(String key, Short defaultValue) {
        if (configuration != null) {
            return configuration.getShort(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static String getString(String key, String defaultValue) {
        if (configuration != null) {
            return configuration.getString(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static String[] getStringArray(String key) {
        if (configuration != null) {
            return configuration.getStringArray(key);
        } else {
            return new String[0];
        }
    }

    public static void addProperty(String key, Object value) {
        if (configuration != null) {
            configuration.addProperty(key, value);
        }
    }

    public static void setProperty(String key, Object value) {
        if (configuration != null) {
            configuration.setProperty(key, value);
        }
    }

    public static boolean containsKey(String key) {
        if (configuration != null) {
            return configuration.containsKey(key);
        } else {
            return false;
        }
    }

    public static boolean isEmpty() {
        if (configuration != null) {
            return configuration.isEmpty();
        } else {
            return true;
        }
    }

    public static void clearProperty(String key) {
        configuration.clearProperty(key);
    }

    @Autowired
    public void setConfig(Configuration config) {
        if (configuration == null)
            configuration = config;
    }
    
/*    public static boolean containsKey(String key){
        configuration.containsKey(key);
    }*/
}

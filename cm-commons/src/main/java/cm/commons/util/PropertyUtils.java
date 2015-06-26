//package cm.commons.util;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.Properties;
//
//
//public class PropertyUtils {
//    private static Properties property;
//
//    public static void update() {
//        property = new Properties();
//        String path = PropertyUtils.class.getClassLoader().getResource("time.properties").getPath();
//        FileInputStream in;
//        System.out.println(path);
//        try {
//            in = new FileInputStream(path);
//            property.load(in);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public static void save() {
//        String path = PropertyUtils.class.getClassLoader().getResource("time.properties").getPath();
//        FileOutputStream ou;
//        System.out.println(path);
//        try {
//            ou = new FileOutputStream(path);
//            property.save(ou, null);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    public static String getValue(String name) {
//        return property.getProperty(name);
//    }
//
//    public static void setValue(String name, String value) {
//        property.setProperty(name, value);
//    }
//
//    public Properties getProperty() {
//        return property;
//    }
//
//}

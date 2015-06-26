//package cm.commons.util;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class XmlUtils {
//    private static String filepath;
//
//    static {
//        filepath = XmlUtils.class.getClassLoader().getResource("User.xml").getPath();
//    }
//
//    public static Document getDocument() throws DocumentException {
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(new File(filepath));
//        return document;
//    }
//
//    public static void write2Xml(Document document) throws IOException {
//        OutputFormat format = OutputFormat.createPrettyPrint();
//        format.setEncoding("UTF-8");
//        XMLWriter writer = new XMLWriter(new FileOutputStream(filepath), format);
//        writer.write(document);
//
//        // Compact format to System.out
//        format = OutputFormat.createCompactFormat();
//        writer = new XMLWriter(System.out, format);
//        writer.write(document);
//
//        writer.close();
//    }
//
//    public static void write2Xml(String json) throws IOException {
//        OutputFormat format = OutputFormat.createPrettyPrint();
//        format.setEncoding("UTF-8");
//        XMLWriter writer = new XMLWriter(new FileOutputStream(filepath), format);
//        writer.write(json);
//        writer.close();
//    }
//
//    public static void clear() throws IOException {
//        write2Xml("");
//    }
//
//    public static String getFilePath() {
//        return filepath;
//    }
//}

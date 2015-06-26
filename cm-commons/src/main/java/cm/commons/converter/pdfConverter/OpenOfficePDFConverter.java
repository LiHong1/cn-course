package cm.commons.converter.pdfConverter;

import cm.commons.util.FileUtils;
import org.apache.log4j.Logger;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;


public class OpenOfficePDFConverter implements PDFConverter {

    private static OfficeManager officeManager;
    private static String OFFICE_HOME = "D:\\OpenOffice.org 3.4.1";
    private static int port[] = {8100};

    public static void startService() {
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        try {
            System.out.println("准备启动服务....");
            configuration.setOfficeHome(OFFICE_HOME);//设置OpenOffice.org安装目录
            configuration.setPortNumbers(port); //设置转换端口，默认为8100
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);//设置任务执行超时为5分钟
            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);//设置任务队列超时为24小时

            officeManager = configuration.buildOfficeManager();
            officeManager.start();    //启动服务
            System.out.println("office转换服务启动成功!");
        } catch (Exception ce) {
            Logger logger = Logger.getLogger(FileUtils.class.getName());
            logger.error("office转换服务启动失败!详细信息:" + ce.getMessage());
        }
    }

    public static void stopService() {
        System.out.println("关闭office转换服务....");
        if (officeManager != null) {
            officeManager.stop();
        }
        System.out.println("关闭office转换成功!");
    }

    public void convert2PDF(String inputFile, String pdfFile) {

        if (inputFile.endsWith(".txt")) {
            String odtFile = FileUtils.getFilePrefix(inputFile) + ".odt";
            if (new File(odtFile).exists()) {
                System.out.println("odt文件已存在！");
                inputFile = odtFile;
            } else {
                FileUtils.copyFile(inputFile, odtFile);
                inputFile = odtFile;
            }
        }
        if(!new File(pdfFile).exists()){
            startService();
            System.out.println(pdfFile);
            System.out.println("进行文档转换转换:" + inputFile + " --> " + pdfFile);
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            converter.convert(new File(inputFile), new File(pdfFile));
            stopService();
        }
    }

    public void convert2PDF(String inputFile) {
        String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
        if(!new File(pdfFile).exists())
        convert2PDF(inputFile, pdfFile);

    }
}

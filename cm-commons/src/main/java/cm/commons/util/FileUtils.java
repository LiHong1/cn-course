package cm.commons.util;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * 文件处理工具
 *
 * @author li hong
 */
public class FileUtils {
    /**
     * 删除某个文件夹下的所有文件夹和文件
     *
     * @param delpath String
     * @return boolean
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void deletefile(String delpath) throws Exception {
        try {

            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    deletefile(delfile.getPath());
//            if (!delfile.isDirectory()) {  
//                delfile.delete();   
//            } else  {  
//                deletefile(delpath + "\\" + filelist[i]);  
//            }  
                }
                file.delete();
            }

        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(FileUtils.class.getName());
            logger.error("deletefile() Exception:" + e.getMessage());
        }

    }

    /**
     * 获取文件前缀
     *
     * @param fileName
     * @return
     */
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }

    /**
     * 文件复制
     *
     * @param inputFile
     * @param outputFile
     * @throws FileNotFoundException
     */
    public static void copyFile(String inputFile, String outputFile) {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int temp = 0;
        try {
            fis = new FileInputStream(sFile);
            fos = new FileOutputStream(tFile);
            while ((temp = fis.read()) != -1) {
                fos.write(temp);
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(FileUtils.class.getName());
            logger.error(e.getMessage());
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(FileUtils.class.getName());
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean exist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static Boolean createDir(String destDirName){
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }

        File dir = new File(destDirName);
        if (dir.exists()) {
            return true;
        }else{
            //创建目录
            if (dir.mkdirs()) {
                return true;
            } else {
                return false;
            }
        }
    }
}

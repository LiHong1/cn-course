//package test.cn.commons.service;
//
//import static org.junit.Assert.assertEquals;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//
//import cn.commons.util.DESCoder;
//import cn.commons.util.TicketUtils;
//
//public class DESCoderTest {
//    private static final Log logger = LogFactory.getLog(DESCoderTest.class);
//    
//    @Test
//    public void test() throws Exception{
//        logger.debug("===========test===============");
//        String inputStr = RandomStringUtils.randomAscii(10);
//        byte[] inputData = inputStr.getBytes();
//        logger.debug("原文：" + inputStr);
//        byte[] key = DESCoder.initKey();
//        logger.debug("key长度：" + key.length);
//        logger.debug("key：" + key);
//        logger.debug("密钥：" + Base64.encodeBase64String(key));
//        inputData = DESCoder.encrypt(inputData, key);
//        logger.debug("加密后：" + Base64.encodeBase64String(inputData));
//        byte[] outputData = DESCoder.decrypt(inputData, key);
//        String outputStr = new String(outputData);
//        logger.debug("解密后：" + outputStr);
//        
//        assertEquals(inputStr, outputStr);
//    }
//    
//    @Test
//    public void test2() throws Exception{
//        logger.debug("===========test2===============");
//        String inputStr = RandomStringUtils.randomAscii(10);
//        byte[] inputData = inputStr.getBytes();
//        logger.debug("原文：" + inputStr);
//        
//        byte[] key = RandomStringUtils.randomAscii(10).getBytes();
//        logger.debug("密钥：" + Base64.encodeBase64String(key));
//        
//        inputData = DESCoder.encrypt(inputData, key);
//        logger.debug("加密后：" + Base64.encodeBase64String(inputData));
//        
//        byte[] outputData = DESCoder.decrypt(inputData, key);
//        String outputStr = new String(outputData);
//        logger.debug("解密后：" + outputStr);
//        
//        assertEquals(inputStr, outputStr);
//    }
//    
//    @Test
//    public void testTicketUtils(){
//        for(int i = 1; i <= 1000; i ++){
//        logger.debug("===========testTicketUtils===============" + i);
//        String inputStr = RandomStringUtils.randomAscii(10) + "#" + RandomStringUtils.randomAscii(10); 
//        logger.debug("原文：" + inputStr);
//        
//        String encryptStr = TicketUtils.encrypt(inputStr);
//        logger.debug("加密后：" + encryptStr);
//        
//        String decryptStr = TicketUtils.decrypt(encryptStr);
//        logger.debug("解密后：：" + decryptStr);
//        
//        assertEquals(inputStr, decryptStr);
//        }
//    }
//}

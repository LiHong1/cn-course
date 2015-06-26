//package test.cn.commons.service;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Map;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Before;
//import org.junit.Test;
//
//import test.cn.commons.service.DESCoderTest;
//import cn.commons.util.RSACoder;
//
//public class RSACoderTest {
//    private byte[] publicKey;
//    private byte[] privateKey;
//    private static final Log logger = LogFactory.getLog(DESCoderTest.class);
//
//    @Before
//    public void initKey() throws Exception {
//
//        // 初始化密钥
//        Map<String, Object> keyMap = RSACoder.initKey();
//
//        publicKey = RSACoder.getPublicKey(keyMap);
//        privateKey = RSACoder.getPrivateKey(keyMap);
//
//        logger.debug("公钥: \n" + Base64.encodeBase64String(publicKey));
//        logger.debug("私钥： \n" + Base64.encodeBase64String(privateKey));
//    }
//
//    @Test
//    public void test() throws Exception {
//
//        logger.debug("\n---私钥加密——公钥解密---");
//
//        String inputStr1 = "RSA加密算法";
//        byte[] data1 = inputStr1.getBytes();
//        logger.debug("原文:\n" + inputStr1);
//
//        // 加密
//        byte[] encodedData1 = RSACoder.encryptByPrivateKey(data1, privateKey);
//        logger.debug("加密后:\n" + Base64.encodeBase64String(encodedData1));
//
//        // 解密
//        byte[] decodedData1 = RSACoder.decryptByPublicKey(encodedData1, publicKey);
//        String outputStr1 = new String(decodedData1);
//        logger.debug("解密后:\n" + outputStr1);
//
//        // 校验
//        assertEquals(inputStr1, outputStr1);
//
//        logger.debug("\n---公钥加密——私钥解密---");
//        String inputStr2 = "RSA Encypt Algorithm";
//        byte[] data2 = inputStr2.getBytes();
//        logger.debug("原文:\n" + inputStr2);
//
//        // 加密
//        byte[] encodedData2 = RSACoder.encryptByPublicKey(data2, publicKey);
//        logger.debug("加密后:\n" + Base64.encodeBase64String(encodedData2));
//
//        // 解密
//        byte[] decodedData2 = RSACoder.decryptByPrivateKey(encodedData2, privateKey);
//        String outputStr2 = new String(decodedData2);
//        logger.debug("解密后: " + outputStr2);
//
//        // 校验
//        assertEquals(inputStr2, outputStr2);
//    }
//}

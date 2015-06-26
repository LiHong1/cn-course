//package cm.commons.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.util.encoders.Hex;
//
//import javax.crypto.Cipher;
//import java.cm.security.*;
//import java.cm.security.interfaces.RSAPrivateKey;
//import java.cm.security.interfaces.RSAPublicKey;
//
///**
// * RSA算法加密/解密工具类。
// *
// * @author Administrator
// */
//public class RSA {
//    /**
//     * 算法名称
//     */
//    private static final String ALGORITHOM = "RSA";
//
//    /**
//     * 密钥大小
//     */
//    private static final int KEY_SIZE = 1024;
//    /**
//     * 默认的安全服务提供者
//     */
//    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
//
//    private static KeyPairGenerator keyPairGen = null;
//    private static KeyFactory keyFactory = null;
//    /**
//     * 缓存的密钥对。
//     */
//    private static KeyPair oneKeyPair = null;
//
//    private static String radamKey = "";
//
//    static {
//        try {
//            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM,
//                    DEFAULT_PROVIDER);
//            keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
//        } catch (NoSuchAlgorithmException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 生成并返回RSA密钥对。
//     */
//    private static synchronized KeyPair generateKeyPair() {
//        try {
//            keyPairGen.initialize(KEY_SIZE,
//                    new SecureRandom(radamKey.getBytes()));
//            oneKeyPair = keyPairGen.generateKeyPair();
//            return oneKeyPair;
//        } catch (InvalidParameterException ex) {
//            ex.printStackTrace();
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 返回已初始化的默认的公钥。
//     */
//    public static RSAPublicKey getDefaultPublicKey() {
//        KeyPair keyPair = generateKeyPair();
//        if (keyPair != null) {
//            return (RSAPublicKey) keyPair.getPublic();
//        }
//        return null;
//    }
//
//    /**
//     * 使用指定的私钥解密数据。
//     *
//     * @param privateKey 给定的私钥。
//     * @param data       要解密的数据。
//     * @return 原数据。
//     */
//    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
//        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
//        ci.init(Cipher.DECRYPT_MODE, privateKey);
//        return ci.doFinal(data);
//    }
//
//    /**
//     * 使用默认的私钥解密给定的字符串。
//     * <p>
//     * 若{@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
//     * 私钥不匹配时，返回 {@code null}。
//     *
//     * @param encrypttext 密文。
//     * @return 原文字符串。
//     */
//    public static String decryptString(String encrypttext) {
//        if (StringUtils.isBlank(encrypttext)) {
//            return null;
//        }
//        KeyPair keyPair = generateKeyPair();
//        try {
//            byte[] en_data = Hex.decode(encrypttext);
//            byte[] data = decrypt((RSAPrivateKey) keyPair.getPrivate(), en_data);
//            return new String(data);
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串。
//     *
//     * @param encrypttext 密文。
//     * @return {@code encrypttext} 的原文字符串。
//     */
//    public static String decryptStringByJs(String encrypttext) {
//        String text = decryptString(encrypttext);
//        if (text == null) {
//            return null;
//        }
//        return StringUtils.reverse(text);
//    }
//
//    public static void main(String[] args) {
//
//        //密码种子，一个密码种子生产一组RSA密码
//        RSA.radamKey = "1111";
//
//        //获取公钥，分发公钥（e1,n）
//        RSAPublicKey publicKey = RSA.getDefaultPublicKey();
//
//        //公钥-系数(n)
//        System.out.println("public key modulus:"
//                + new String(Hex.encode(publicKey.getModulus().toByteArray())));
//
//        //公钥-指数(e1)
//        System.out.println("public key exponent:"
//                + new String(Hex.encode(publicKey.getPublicExponent()
//                .toByteArray())));
//
//        //JS加密后的字符串
//        String pppp = "6e269bdf7f7670ffaff669d86d93e63fd91f2d6dd4f7e57ca58c614de65c0828e608bdd0f3e81d3630f8533f5286e0f55caa005ebe6d3faf3b9cc130d9ecff51d1f77eeed48156431cbf5de62cf6899084a4e92890827edbebbf506ecbb022de4a0e3131f2ffefe3368c7b7f929362edb4af419b837456c275f643a5c760a4ff";
//
//        //解密后的字符串
//        String kkkk = RSA.decryptStringByJs(pppp);
//
//        System.out.println("解密后文字：" + kkkk);
//    }
//}
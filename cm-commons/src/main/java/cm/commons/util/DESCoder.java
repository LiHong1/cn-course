package cm.commons.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES编码器
 *
 * @author lzc
 */
public final class DESCoder {
    /**
     * 密钥算法
     * Java 6 只支持56位密钥
     */
    public static final String KEY_ALGORITHM = "DES";

    /**
     * 加密、解密算法，工作模式，填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    /**
     * 构造函数
     */
    private DESCoder() {
    }

    /**
     * 转换秘钥
     *
     * @param key 二进制秘钥
     * @return 密钥
     * @throws GeneralSecurityException
     * @throws GeneralSecurityException
     * @throws
     * @throws
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws GeneralSecurityException {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密数据
     * @throws GeneralSecurityException
     * @throws
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws GeneralSecurityException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密数据
     * @throws GeneralSecurityException
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws GeneralSecurityException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 生成密钥
     * Java 6 只支持56位密钥
     *
     * @return 二进制秘钥
     * @throws Exception
     */
    public static byte[] initKey() throws GeneralSecurityException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(new SecureRandom());
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

}

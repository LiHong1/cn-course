package cm.commons.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 随机数生成工具
 *
 * @author li hong
 */
public class RandomUtils {
    public static String getRandom() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = null;
        random.nextBytes(bytes);
        //随机数R1
        StringBuffer RandomNumber = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            RandomNumber.append((char) ((bytes[i] + 128) % 64 + 63));
        }
        return RandomNumber.toString();
    }
}

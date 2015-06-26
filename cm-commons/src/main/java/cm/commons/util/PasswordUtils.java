package cm.commons.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;


/**
 * 口令工具类
 *
 * @author lzc
 */
public final class PasswordUtils {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 构造函数
     */
    private PasswordUtils() {
    }

    private static String mergePdAndSalt(String password, Object salt, boolean strict) {
        String p;
        if (password == null) {
            p = "";
        } else p = password;

        if (strict && (salt != null)) {
            String saltString = salt.toString();
            if ((saltString.lastIndexOf('{') != -1) || (saltString.lastIndexOf('}') != -1)) {
                throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
            }
        }

        if ((salt == null) || "".equals(salt)) {
            return p;
        } else {
            return p + "{" + salt.toString() + "}";
        }
    }

    /**
     * 生成加密口令
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encode(String password, String salt) {
        return DigestUtils.sha1Hex(mergePdAndSalt(password, salt, false));
    }

    /**
     * 检查口令是否正确
     *
     * @param encPass
     * @param rawPass
     * @param salt
     * @return
     */
    public static boolean check(String encPass, String rawPass, Object salt) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass, salt.toString());

        return equal(pass1, pass2);
    }

    /**
     * 比较两个字符串是否相同
     *
     * @param expected
     * @param actual
     * @return
     */
    public static boolean equal(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }

    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }

        return encodeUtf8(s);
    }

    /**
     * 对字符串进行utf8编码
     *
     * @param string
     * @return
     */
    public static byte[] encodeUtf8(CharSequence string) {
        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());
            return bytesCopy;
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Encoding failed", e);
        }
    }

    /**
     * 计算口令强度
     *
     * @param string
     * @return
     */
    public static int computeStrength(String password) {
        if (password == null) {
            return 0;
        }
        return 0;
    }


}

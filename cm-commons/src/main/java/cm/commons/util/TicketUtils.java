package cm.commons.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/**
 * Ticket工具类
 *
 * @author lzc
 */
public final class TicketUtils {
    // UTF-8
    public static final String UTF_8_NAME = "UTF-8";
    // Ticket 编码
    public static final Charset UTF_8 = Charset.forName(UTF_8_NAME);
    // Ticket分隔符
    public static final String TICKET_DELIMITER = "#";
    // Ticket Key String
    private static final String TICKET_KEY = "#m_8glww2ju&298er";
    private static final Log LOG = LogFactory.getLog(TicketUtils.class);

    /**
     * 私有构造函数
     */
    private TicketUtils() {
    }

    /**
     * ticket解密
     *
     * @param ticket
     * @return
     * @throws ModelException
     */
    public static String decrypt(String ticket) {
        String result = null;
        try {
            result = new String(DESCoder.decrypt(Base64.decodeBase64(ticket), TICKET_KEY.getBytes(UTF_8)), UTF_8);
        } catch (GeneralSecurityException e) {
            LOG.debug(e.getMessage());
        }
        return result;
    }

    /**
     * ticket加密
     *
     * @param tickets
     * @return
     * @throws ModelException
     */
    public static String encrypt(String[] tickets) {
        if (tickets == null) {
            return null;
        }
        if (tickets.length == 1) {
            return encrypt(tickets[0]);
        }

        StringBuilder sb = new StringBuilder(tickets[0]);
        for (int i = 1; i < tickets.length; i++) {
            sb.append(TICKET_DELIMITER);
            sb.append(tickets[i]);
        }
        return encrypt(sb.toString());
    }

    /**
     * ticket加密
     *
     * @param ticket
     * @return
     * @throws ModelException
     */
    public static String encrypt(String ticket) {
        String result = null;
        try {
            result = Base64.encodeBase64URLSafeString(DESCoder.encrypt(ticket.getBytes(UTF_8),
                    TICKET_KEY.getBytes(UTF_8)));
        } catch (GeneralSecurityException e) {
            LOG.debug(e.getMessage());
        }
        return result;
    }

    /**
     * ticket解密，并分割
     *
     * @param ticket
     * @return
     * @throws ModelException
     */
    public static String[] decryptAndSplit(String ticket) {
        return decrypt(ticket).split(TICKET_DELIMITER);
    }

}

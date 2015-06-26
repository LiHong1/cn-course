package cm.commons.service;

import java.util.Calendar;

/**
 * 流水号服务
 *
 * @author leizhenchun
 */
public interface SerialService {
    /**
     * 生成流水号
     *
     * @param prefix
     * @param tradeType
     * @param date
     * @return
     */
    String get(String prefix, String tradeType, Calendar date);

    /**
     * 生成流水号
     *
     * @param prefix
     * @param tradeType
     * @return
     */
    String get(String prefix, String tradeType);

}

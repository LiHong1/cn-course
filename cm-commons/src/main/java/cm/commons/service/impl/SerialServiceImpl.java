package cm.commons.service.impl;

import cm.commons.service.SerialService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * 流水号具体实现
 *
 * @author leizhenchun
 */
@Service
public class SerialServiceImpl implements SerialService {
    private long nextNum = 1L;
    private long maxNum = 10000L;

    public String get(String prefix, String tradeType, Calendar date) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(tradeType);
        // new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date.getTime())
        sb.append(DateFormatUtils.format(date, "yyyyMMddHHmmssSSS"));
        sb.append(String.format("%04d", getNextNum()));
        return sb.toString();
    }


    public String get(String prefix, String tradeType) {
        return get(prefix, tradeType, Calendar.getInstance());
    }

    private synchronized Long getNextNum() {
        if (nextNum >= maxNum) {
            nextNum = 1L;
        }
        return Long.valueOf(nextNum++);
    }

}

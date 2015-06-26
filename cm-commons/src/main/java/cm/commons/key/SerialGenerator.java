package cm.commons.key;


import java.util.Calendar;

/*
 * 交易流水号生成器
 */
public interface SerialGenerator {
    /**
     * 获取序列号
     *
     * @return
     */
    String get();

    /**
     * 根据时间获取流水号
     *
     * @param now
     * @return
     */
    String get(Calendar now);

    /**
     * 下一个主键值
     *
     * @return
     */
    Long nextId();

    /**
     * 下一个主键值
     *
     * @return
     */
    String nextKey();

}

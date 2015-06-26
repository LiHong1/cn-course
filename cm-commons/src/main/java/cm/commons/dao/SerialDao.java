package cm.commons.dao;

import cm.commons.bean.SerialNumber;

import java.util.Calendar;

/**
 * 序号生成器Dao接口
 *
 * @author lzc
 */
public interface SerialDao {
    /**
     * 获取序列号
     *
     * @param serialKey
     * @param currentDate
     * @return
     */
    SerialNumber get(String serialKey, Calendar currentDate);

}

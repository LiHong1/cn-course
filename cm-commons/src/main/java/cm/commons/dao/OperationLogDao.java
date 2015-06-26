package cm.commons.dao;

import cm.commons.CalendarRange;
import cm.commons.bean.OperationLogItem;

import java.util.List;

/**
 * 操作日志Dao接口
 *
 * @author lzc
 */
public interface OperationLogDao extends BaseDao<OperationLogItem> {

    /**
     * 新增操作日志
     *
     * @param items
     */
    void save(List<OperationLogItem> items);

    /**
     * 清除日志
     */
    void clearLog();

    /**
     * 获取所有日志
     *
     * @param operatorId
     * @param cr
     * @param firstResult
     * @param maxResults
     * @return
     */
    List<OperationLogItem> getAll(Long operatorId, CalendarRange cr, int firstResult, int maxResults);

    /**
     * 获取日志数目
     *
     * @param operatorId
     * @param cr
     * @return
     */
    long getAllCount(Long operatorId, CalendarRange cr);

}

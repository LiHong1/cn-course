package cm.commons.service;

import cm.commons.Page;
import cm.commons.bean.OperationLogItem;
import org.aspectj.lang.JoinPoint;

import java.util.Calendar;

/**
 * 日志服务
 *
 * @author leizhenchun
 */
public interface LogService extends BaseService<OperationLogItem> {
    /**
     * 新增操作日志
     *
     * @param operatorId
     * @param operatorLoginName
     * @param operationModule
     * @param operationName
     * @param operationContent
     * @param operationArgs
     * @param operationResult
     * @param remoteHost
     * @param hostAddress
     * @return 日志Id
     */
    Long log(Long operatorId, String operatorName, String operationModule, String operationName, String operationContent,
             String operationArgs, String operationResult, String remoteHost, String remoteAddr, String userAgent,
             Calendar start, Calendar end);

    /**
     * 新增操作日志
     *
     * @param logItem
     * @return
     */
    Long log(OperationLogItem logItem);

    /**
     * 用户操作日志（分页）
     *
     * @param userId
     * @param firstResult
     * @param maxResults
     * @return
     */
    Page<OperationLogItem> getLogPageList(Long userId, Calendar start, Calendar end, int firstResult, int maxResults);

    /**
     * 清除所有操作日志(仅供开发测试用)
     */
    void clearAll();

    //  //无参的日志方法
//    public void log();
    //有参的日志方法
    public void logArg(JoinPoint point);
//    //有参有返回值的方法
//    public void logArgAndReturn(JoinPoint point,Object returnObj);

    void log(JoinPoint point);
}

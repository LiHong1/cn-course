package cm.commons.service.impl;

import cm.commons.CalendarRange;
import cm.commons.Page;
import cm.commons.bean.OperationLogItem;
import cm.commons.dao.OperationLogDao;
import cm.commons.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * 日志Service实现类
 *
 * @author lzc
 */
@Service
public class LogServiceImplement extends BaseServiceImpl<OperationLogItem> implements LogService {
    @Autowired
    private OperationLogDao logDao;

    public OperationLogDao getLogDao() {
        return logDao;
    }

//    @Autowired
//    public void setLogDao(OperationLogDao logDao) {
//        this.logDao = logDao;
//        setBaseDao(logDao);
//    }


    @Transactional
    public Long log(Long operatorId, String operatorName, String operationModule, String operationName, String operationContent,
                    String operationArgs, String operationResult, String remoteHost, String remoteAddr, String userAgent,
                    Calendar start, Calendar end) {
        OperationLogItem item = new OperationLogItem();
        item.setOperatorId(operatorId);
        item.setOperatorName(operatorName);
        item.setOperationModule(operationModule);
        item.setOperationName(operationName);
        item.setOperationContent(operationContent);
        item.setOperationArgs(operationArgs);
        item.setOperationResult(operationResult);
        item.setRemoteHost(remoteHost);
        item.setRemoteAddr(remoteAddr);
        item.setOperationDateStart(start);
        item.setOperationDateEnd(end);
        item.setUserAgent(userAgent);
        return log(item);
    }


    @Transactional
    public Long log(OperationLogItem logItem) {
        return (Long) logDao.save(logItem);
    }


    @Transactional(readOnly = true)
    public Page<OperationLogItem> getLogPageList(Long operatorId, Calendar start, Calendar end, int firstResult,
                                                 int maxResults) {
        CalendarRange cr = new CalendarRange(start, end);
        long totalCount = logDao.getAllCount(operatorId, cr);
        int newFirstResult = Page.getFirstResult(firstResult, totalCount, maxResults);
        return new Page<OperationLogItem>(logDao.getAll(operatorId, cr, newFirstResult, maxResults), totalCount,
                newFirstResult, maxResults);
    }


    @Transactional
    public void clearAll() {
        logDao.clearLog();
    }

    public void log(JoinPoint point) {
        System.out.println("*************Log*******************");
        System.out.println(point.getTarget().toString());
        System.out.println(point.getSignature().getName());
    }

    //有参无返回值的方法

    public void logArg(JoinPoint point) {
        System.out.println("*************logArg*******************");
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        System.out.println("目标参数列表：");
        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj + ",");
            }
            System.out.println();
            System.out.println(point.getTarget().toString());
            System.out.println(point.getSignature().getName());
        }
    }

//    //有参并有返回值的方法
//    
//    public void logArgAndReturn(JoinPoint point, Object returnObj) {
//        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
//        Object[] args = point.getArgs();
//        System.out.println("目标参数列表：");
//        if (args != null) {
//            for (Object obj : args) {
//                System.out.println(obj + ",");
//            }
//            System.out.println();
//            System.out.println("执行结果是：" + returnObj);
//        }
//        System.out.println(point.getTarget().toString());
//        System.out.println(point.getSignature().getName());
//    }

    //有参并有返回值的方法

    public void failure(JoinPoint point) {
        System.out.println("*************failure*******************");
        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
        Object[] args = point.getArgs();
        System.out.println("目标参数列表：");
        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj + ",");
            }
            System.out.println();
        }
        System.out.println(point.getTarget().toString());
        System.out.println(point.getSignature().getName());
    }


}

//package cn.commons.service.impl;
//
//import java.util.Calendar;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.RejectedExecutionException;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import cn.commons.service.LogService;
//
//import cn.commons.CommonsPrimaryKey;
//import cn.commons.bean.OperationLogItem;
//import cn.commons.key.KeyFactory;
//
///**
// * 日志Service多线程实现类
// * 
// * @author lzc
// * 
// */
//@Service
//public class LogServiceCachedThreadImplement extends LogServiceImplement{
//    private ExecutorService exec;
//    private int poolSize = Runtime.getRuntime().availableProcessors() * 2;
//
//    private static final long AWAIT_TERMINATION = 100;
//    private final Log LOG = LogFactory.getLog(LogServiceCachedThreadImplement.class);
//
//    public void setPoolSize(int poolSize) {
//        this.poolSize = poolSize;
//    }
//
//    @Override
//    @Transactional
//    public Long log(Long operatorId, String operationModule, String operationName, String operationContent,
//            String operationArgs, String operationResult, String remoteHost, String remoteAddr, String userAgent,
//            Calendar start, Calendar end) {
//        OperationLogItem item = new OperationLogItem();
//
//        item.setOperatorId(operatorId);
//        item.setOperationModule(operationModule);
//        item.setOperationName(operationName);
//        item.setOperationContent(operationContent);
//        item.setOperationArgs(operationArgs);
//        item.setOperationResult(operationResult);
//        item.setRemoteHost(remoteHost);
//        item.setRemoteAddr(remoteAddr);
//        item.setOperationDateStart(start);
//        item.setOperationDateEnd(end);
//        item.setUserAgent(userAgent);
//
//        return log(item);
//    }
//
//    @Override
//    @Transactional
//    public Long log(OperationLogItem logItem) {
//        Long id = KeyFactory.nextId(CommonsPrimaryKey.LOG_OPERATION);
//        logItem.setId(id);
//
//        LoggerThread task = new LoggerThread(logItem);
//
//        LOG.debug("准备执行日志任务");
//        try {
//            exec.execute(task);
//        } catch (RejectedExecutionException ignored) {
//        }
//        LOG.debug("执行日志任务结束");
//
//        return id;
//    }
//
//    public void start() {
//        exec = Executors.newFixedThreadPool(poolSize);
//        LOG.debug("LogServiceCachedThreadImplement.start ");
//    }
//
//    public void stop() {
//        LOG.debug("LogServiceCachedThreadImplement.stoop ");
//        try {
//            exec.shutdown();
//            exec.awaitTermination(AWAIT_TERMINATION, TimeUnit.SECONDS);
//        } catch (Exception e) {
//
//        }
//    }
//
//    class LoggerThread implements Runnable {
//        private OperationLogItem log;
//
//        public LoggerThread(OperationLogItem log) {
//            this.log = log;
//        }
//
//        @Override
//        public void run() {
//            LOG.debug("执行日志任务。。。。。。");
//            if (log != null) {
//                getLogDao().save(log);
//            }
//        }
//    }
//
//}

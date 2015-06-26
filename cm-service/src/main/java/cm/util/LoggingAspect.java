//package cm.util;
//
//import cm.commons.service.LogService;
//import cm.dao.OperationDao;
//import cm.entity.Operation;
//import cm.entity.Person;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Calendar;
//
//@Aspect
//@Component
//public class LoggingAspect {
//    String action = null;
//    String method = null;
//    String uri = null;
//    String operationArgs = null;
//    Calendar end = null;
//    String operationContent;
//    String operationResult;
//    String operatorName = null;
//    String oteratorId = null;
//    Object[] args = null;
//    String remoteHost = null;
//    String remoteAddr = null;
//    String userAgent = null;
//    Calendar start = null;
//    Operation option = null;
//    Person user = null;
//    @Resource
//    private LogService logService;
//    @Resource
//    private OperationDao operationDao;
//
//    @Before("execution (* cm.action.*.*(..))")
//    public void before(JoinPoint jp) {
//        start = Calendar.getInstance();
//        System.out.println("before=================");
//    }
//
//    @AfterReturning(value = "execution (* cm.action.*.*(..))", returning = "returnObj")
//    public void after(JoinPoint jp, Object returnObj) {
//        System.out.println("after=====================");
//        action = jp.getTarget().getClass().getSimpleName();
//        System.out.println(action);
//        method = jp.getSignature().getName();
//        System.out.println(method);
//        uri = action + "_" + method;
//        if (returnObj != null)
//            operationResult = returnObj.toString();
//        remoteHost = ServletActionContext.getRequest().getRemoteHost();
//        remoteAddr = ServletActionContext.getRequest().getRemoteAddr();
//        userAgent = ServletActionContext.getRequest().getHeader("user-agent");
//
//        option = operationDao.get(uri);
//        user = (Person) ActionContext.getContext().getSession().get("user");
//        end = Calendar.getInstance();
//        if (user != null) {
//            oteratorId = user.getId();
//            operatorName = user.getName();
//        }
//        //if(option!=null)
//        //logService.log(oteratorId,operatorName, option.getOptionModelName(), option.getOptionName(),null, operationArgs, operationResult, remoteHost, remoteAddr, userAgent, start, end);
//        // logService.log(oteratorId, "operationModule", operatorName, operationContent, operationArgs, operationResult, remoteHost, remoteAddr, userAgent, start, end);
//
//    }
//
//    //有参并有返回值的方法
//    @AfterReturning(value = "execution (* cm.serviceImpl.*.*(..))", returning = "returnObj")
//    public void logArgAndReturn(JoinPoint point, Object returnObj) {
//        //此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
//        System.out.println("service======================");
//        Object[] args = point.getArgs();
//        System.out.println("目标参数列表：");
//        if (args != null) {
//            for (Object obj : args) {
//                System.out.println(obj + ",");
//            }
//            System.out.println();
//            System.out.println("执行结果是：" + returnObj);
//        }
//    }
//}

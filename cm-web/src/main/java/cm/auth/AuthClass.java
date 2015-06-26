package cm.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 只要在Controller上增加了这个方法的类，都需要进行权限的控制
 *
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
    /**
     * 如果value为manage就表示这个类只能超级管理员访问
     * 如果value为student表示这个类只能学生访问
     * 如果value为teacher表示这个类只能老师访问
     * 如果value为base表示这个类所有人能访问
     *
     * @return
     */
    public String value() default "base";
}

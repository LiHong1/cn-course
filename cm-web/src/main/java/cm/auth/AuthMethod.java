package cm.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用来确定哪些方法由哪些角色访问
 * 属性有一个role：如果role的值为base表示这个方法可以被所有的登录用户访问
 * 如果为student表示只能为学生访问
 * 如果为teacher表示只能为老师访问
 * 如果为manage表示只能为管理员访问
 * 如果为base表示只能为所有人访问
 * 如果某个方法中没有加入AuthMethod就表示该方法能被所有人访问
 *
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {
    public String role() default "base";
}

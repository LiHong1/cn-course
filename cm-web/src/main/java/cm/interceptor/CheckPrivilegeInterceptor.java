package cm.interceptor;

import cm.entity.Person;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;
import java.util.Set;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation invocation) throws Exception {

        Person user = (Person) ActionContext.getContext().getSession().get("user"); // 当前登录用户
        String namespace = invocation.getProxy().getNamespace();
        String actionName = invocation.getProxy().getActionName();
        String name = invocation.getAction().getClass().getName() + "." + invocation.getProxy().getMethod();
        String privUrl = namespace + actionName; // 对应的权限URL
        String role = (String) ActionContext.getContext().getSession().get("role");
        if (user == null) {
            if (ActionContext.getContext().getSession().get("user") == null) {
                if (privUrl.startsWith("/user_login") || privUrl.startsWith("/user_loginUI") ||
                        privUrl.startsWith("/application_apply") || privUrl.startsWith("/application_applyUI")
                        || privUrl.startsWith("/student_addUI") || privUrl.startsWith("/student_add") || privUrl.startsWith("/application_applicationGetClass")) { // "/user_loginUI", "/user_login"
                    // 如果是去登录，就放行
                    return invocation.invoke();
                } else {
                    // 如果不是去登录，就转到登录页面
                    return "loginUI";
                }
            }
        } else {
            Map<String, Set<String>> allAuths = (Map<String, Set<String>>) ActionContext.getContext().getApplication().get("allAuths");
            System.out.println(allAuths);
            //不是超级管理人员，就需要判断是否有权限访问某些功能
            Set<String> actions = allAuths.get(role);
            Set<String> allActions = allAuths.get("allActions");
            System.out.println(name);
            if (allActions != null && allActions.contains(name)) {

                if (actions == null || !actions.contains(name))
                    return "noPrivilegeError";
            }


        }


        return invocation.invoke();
    }

}

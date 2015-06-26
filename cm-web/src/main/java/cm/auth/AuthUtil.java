package cm.auth;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuthUtil {
    /**
     * 初始化系统的角色所访问的功能信息
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, Set<String>> initAuth(String pname) {
        try {
            Map<String, Set<String>> auths = new HashMap<String, Set<String>>();
            String[] ps = getClassByPackage(pname);
            for (String p : ps) {
                String pc = pname + "." + p.substring(0, p.lastIndexOf(".class"));
                //得到了类的class对象
                Class clz = Class.forName(pc);
                Method[] ms = clz.getDeclaredMethods();
                /*if(!clz.isAnnotationPresent(AuthClass.class)){//如果没加AuthClass注解  即所有人都能访问
                    //continue;
				    Set<String> actions = auths.get("base");
	                   if(actions==null) {
	                       actions = new HashSet<String>();
	                       auths.put("base", actions);
	                   }
	                   for(Method m:ms) {
	                       actions.add(pc+"."+m.getName());
	                   }
				}else{*/
                if (!clz.isAnnotationPresent(AuthClass.class)) continue;
                AuthClass authClass = (AuthClass) clz.getAnnotation(AuthClass.class);
                if (!authClass.value().equals("base")) {  //若果这个类的方法只能被 相应的role访问的
                    Set<String> actions = auths.get(authClass.value());
                    Set<String> allActions = auths.get("allActions");
                    if (actions == null) {
                        actions = new HashSet<String>();
                        auths.put(authClass.value(), actions);
                    }
                    if (allActions == null) {
                        allActions = new HashSet<String>();
                        auths.put("allActions", allActions);
                    }
                    for (Method m : ms) {
                        actions.add(pc + "." + m.getName());
                        allActions.add(pc + "." + m.getName());
                    }
                    System.out.println("11111111111111");
                } else {
                    for (Method m : ms) {
                        if (!m.isAnnotationPresent(AuthMethod.class)) { //方法没有注解    即所有人都能访问
		                        /*Set<String> actions = auths.get("base");
		                        if(actions==null) {
		                            actions = new HashSet<String>();
		                            auths.put("base", actions);
		                        }
		                            actions.add(pc+"."+m.getName());*/
                        } else {
                            //如果存在就要获取这个Annotation
                            AuthMethod am = m.getAnnotation(AuthMethod.class);
                            String roles = am.role();
                            //可能一个action可以被多个角色所访问，使用，进行分割
                            String[] aRoles = roles.split(",");
                            for (String role : aRoles) {
                                Set<String> actions = auths.get(role);
                                if (actions == null) {
                                    actions = new HashSet<String>();
                                    auths.put(role, actions);
                                }
                                actions.add(pc + "." + m.getName());
                            }
                            Set<String> allActions = auths.get("allActions");
                            if (allActions == null) {
                                allActions = new HashSet<String>();
                                auths.put("allActions", allActions);
                            }
                            allActions.add(pc + "." + m.getName());
                        }

                    }
                }
                //}
            }
            return auths;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据包获取所有的类
     *
     * @param pname
     * @return
     */
    private static String[] getClassByPackage(String pname) {
        String pr = pname.replace(".", "/");
        String pp = AuthUtil.class.getClassLoader().getResource(pr).getPath().replace("%20", " ");
        File file = new File(pp);
        String[] fs = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(".class")) return true;
                return false;
            }
        });
        System.out.println(fs[1]);
        return fs;
    }

    public static void main(String[] args) {
        System.out.println(initAuth("org.konghao.cms.controller"));
    }
}

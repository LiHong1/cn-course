package cm.action;

import cm.commons.config.SystemConfiguration;
import cm.commons.util.MDCoder;
import cm.entity.*;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<Person> {
    @Autowired
    private SystemConfiguration configuration;
    private String newPassword1;
    private String newPassword2;
    private String classId;
    private String courseId;

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String login() {
        State state;

        String loginAble = getSystemConfiguration("loginAble");
        String replaceMachineAble = getSystemConfiguration("replaceMachineAble");
        if (loginAble.equals("true")) {
            Student student = studentService.login(model.getNumber(), this.getModel().getPassword());
            if (student != null) {

                state = stateService.find(courseService.get(getCourseId()), classService.get(getClassId()));
                if (state == null) {
                    addFieldError("login", "请选择课程与班级");
                    return "login";
                }
                if (!state.getLoginAble()) {
                    addFieldError("login", "该班级没有开放，请联系任课教师！");
                    return "login";
                }
                Application application = applicationService.find(state, student);
                if (!loginAble(replaceMachineAble, application, student)) {
                    addFieldError("login", "你已经在另一台机子上登录，可三十分钟后重新登录");
                    return "login";
                }
                student.setCuState(state);
                student.setRemoteAddr(ServletActionContext.getRequest().getRemoteAddr());
                timeLogService.save(application);
                studentService.update(student);

                ActionContext.getContext().getSession().put("user", student);
                ActionContext.getContext().getSession().put("role", "student");
                return "success";
            }
            Teacher teacher = teacherService.login(model.getNumber(), this.getModel().getPassword());
            if (teacher != null) {
                State cuState = stateService.find(courseService.get(getCourseId()), classService.get(classId));
                if (cuState == null) {
                    addFieldError("login", "请选择课程与班级");
                    return "login";
                }
                teacher.setCuState(cuState);
                ActionContext.getContext().getSession().put("user", teacher);
                teacherService.update(teacher);
                ActionContext.getContext().getSession().put("role", "teacher");
                return "success";
            }

        }
        Manage manage = manageService.login(model.getNumber(), model.getPassword());
        if (manage != null) {
            ActionContext.getContext().getSession().put("user", manage);
            ActionContext.getContext().getSession().put("role", "manage");
            System.out.println("success");
            return "success";
        }
        System.out.println("failure");
        if (!loginAble.equals("true")) {
            addFieldError("login", "该系统没有开放，请联系管理员！");

        } else {
            addFieldError("login", "用户名或密码不正确！");
        }
        return "login";

    }

    public String logout() {

        Person person = getCurrentUser();
        if (person instanceof Student) {
            Application application = applicationService.find(((Student) person).getCuState(), (Student) person);
            application.setLatestLogoutTime(Calendar.getInstance());
            applicationService.update(application);
        }
        ActionContext.getContext().getSession().remove("user");
        return "login";

    }

    public String getSystemConfiguration(String property) {
        String value = null;
        value = (String) ActionContext.getContext().getApplication().get(property);
        if (value == null) {
            value = (String) configuration.getProperty(property);
            ActionContext.getContext().getApplication().put(property, value);
        }
        return value;
    }

    public boolean loginAble(String replaceMachineAble, Application application, Student student) {
        if (application.getLatestLoginTime() == null)
            return true;
        long time = Calendar.getInstance().getTimeInMillis() - application.getLatestLoginTime().getTimeInMillis();
        if (time > 30 * 60 * 1000 || replaceMachineAble.equals("true"))
            return true;
        else {
            String remoteAddr = ServletActionContext.getRequest().getRemoteAddr();
            if (remoteAddr.equals(student.getRemoteAddr()))
                return true;
            else return false;
        }
    }

    public void loginGetCourse() {
        System.out.println(model.getNumber());
        Student student = studentService.findByNumber(model.getNumber());
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = null;
        JSONArray json = new JSONArray();
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student != null) {
            System.out.println(student.getName());
            for (Application application : student.getApplications()) {
                if (application.getType() == 1l) {
                    Course a = application.getState().getCourse();
                    JSONObject jo = new JSONObject();
                    jo.put("id", a.getId().toString());
                    jo.put("courseName", a.getName());
                    json.add(jo);
                }

            }
            out.print(json.toString());

        } else {
            Teacher teacher = teacherService.findByNumber(model.getNumber());
            System.out.println(teacher.getName());
            if (teacher != null) {
                for (Course a : teacher.getCourses()) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", a.getId().toString());
                    jo.put("courseName", a.getName());
                    json.add(jo);
                }

                out.print(json.toString());

            }
        }

    }

    public void loginGetClass() {
        Teacher teacher = teacherService.findByNumber(model.getNumber());
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = null;
        JSONArray json = new JSONArray();
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (teacher != null) {
            List<State> stateList = stateService.find(null, courseService.get(getCourseId()), teacher);
            for (State a : stateList) {
                JSONObject jo = new JSONObject();
                jo.put("id", a.getClas().getId().toString());
                jo.put("className", a.getClas().getName());

                json.add(jo);
            }
            out.print(json.toString());
        } else {
            Student student = studentService.findByNumber(model.getNumber());
            if (student != null) {
                Course course = courseService.get(getCourseId());
                for (Application application : student.getApplications()) {
                    if (application.getType() == 1l) {
                        Clazz a = application.getState().getClas();
                        if (application.getState().getCourse().getId() == course.getId()) {
                            JSONObject jo = new JSONObject();
                            jo.put("id", a.getId().toString());
                            jo.put("className", a.getName());
                            json.add(jo);
                        }


                    }


                }
                out.print(json.toString());
            }
        }

    }

    public String changePassword() {
        Person person = (Person) getCurrentUser();
        if (person != null)

            if (person.getPassword().equals(MDCoder.encodeMD5Hex(model.getPassword()))) {
                if (newPassword1.equals(newPassword2)) {
                    person.setPassword(MDCoder.encodeMD5Hex(newPassword1));
                    if (person.getClass().getSimpleName().equals("Manage"))
                        manageService.update((Manage) person);
                    else if (person.getClass().getSimpleName().equals("Teacher"))
                        teacherService.update((Teacher) person);
                    else if (person.getClass().getSimpleName().equals("Student"))
                        studentService.update((Student) person);
                } else {
                    addActionMessage("你的口令输入不一致!");
                }
            } else {
                addActionMessage("你的旧口令输入错误!");
            }
        addActionMessage("更改成功");
        return "changePassword";
    }

    public String changePasswordUI() {

        return "changePassword";
    }

    public String loginUI() {

        if (getCurrentUser() != null)
            return "success";
        return "login";
    }
}

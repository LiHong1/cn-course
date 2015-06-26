package cm.action;

import cm.auth.AuthClass;
import cm.auth.AuthMethod;
import cm.commons.util.MDCoder;
import cm.entity.Application;
import cm.entity.Course;
import cm.entity.State;
import cm.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * 申请Action
 *
 * @author li hong
 */

@Controller
@Scope("prototype")
@AuthClass("base")
public class ApplicationAction extends BaseAction<Application> {
    HttpServletResponse response;
    PrintWriter out;
    JSONArray json;
    private String courseId;
    private String classId;
    private String number;
    private String password;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void application() {
        response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        out = null;
        json = new JSONArray();
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 申请加入班级
     *
     * @return
     */
    public String applyUI() {
        List<Course> courseList = courseService.getAll();
        ActionContext.getContext().put("courseList", courseList);
        return "applyUI";
    }

    public String apply() {
        List<Course> courseList = courseService.getAll();
        ActionContext.getContext().put("courseList", courseList);
        Student student = studentService.findByNumber(getNumber());
        if (student == null) {
            ActionContext.getContext().put("message", "你还没有注册，请先去注册！");
            return "applyUI";
        } else {
            if (!student.getPassword().equals(MDCoder.encodeMD5Hex(getPassword()))) {
                ActionContext.getContext().put("message", "你的密码输入错误！");
                return "applyUI";
            }
        }
        State state = stateService.find(courseService.get(getCourseId()), classService.get(getClassId()));
        if (state != null) {
            Set<Application> applications = student.getApplications();
            for (Application application : applications) {
                if (application.getState().equals(state)) {
                    ActionContext.getContext().put("message", "你已经申请了该课程！");
                    return "applyUI";
                }
            }
            if (!state.getJoinAble()) {
                ActionContext.getContext().put("message", "系统已经关闭申请,请联系任课老师！");
                return "applyUI";
            }
            Application application = new Application();
            application.setState(state);
            application.setStudent(studentService.findByNumber(getNumber()));
            application.setType(0l);
            applicationService.save(application);
            ActionContext.getContext().put("message", "你的申请已经完成，请等待任课老师批准！");
        } else
            ActionContext.getContext().put("message", "你的申请失败，请与管理员联系！");
        return "applyUI";
    }

    public void applicationGetClass() {
        application();
        Student student = studentService.findByNumber(getNumber());
        if (student != null) {
            Course course = courseService.get(getCourseId());
            List<State> stateList = stateService.find(null, course, null);
            for (State a : stateList) {
                JSONObject jo = new JSONObject();
                jo.put("id", a.getClas().getId());
                jo.put("className", a.getClas().getName());
                json.add(jo);
            }
            out.print(json.toString());
        }

    }

    @AuthMethod(role = "teacher")
    public String getThrough() {
        Application application = applicationService.get(model.getId());
        application.setType(1l);
        application.setJoinClassTime(Calendar.getInstance());
        applicationService.update(application);
        return "toClassManageUI";
    }

    @AuthMethod(role = "teacher")
    public String refuse() {
        Application application = applicationService.get(model.getId());
        application.setType(2l);
        applicationService.update(application);
        return "toClassManageUI";
    }
}

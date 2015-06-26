package cm.action;


import cm.entity.Application;
import cm.entity.Clazz;
import cm.entity.State;
import cm.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller
@Scope("prototype")
public class StateAction extends BaseAction<State> {
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String delete() {
        stateService.delete(model.getId());
        return "delete";
    }

    public void find() {
        if (courseName != null) {
            List<State> stateList = stateService.find(null, courseService.find("name", courseName), null);

            List<Clazz> classList = new ArrayList<Clazz>();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = null;
            JSONArray json = new JSONArray();
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (State state : stateList) {
                JSONObject jo = new JSONObject();
                jo.put("id", state.getId());
                jo.put("className", state.getClas().getName());
                json.add(jo);
            }
            out.print(json.toString());
        }

    }

    public String examSetUI() {
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("user");
        State state = null;
        if (teacher != null) {
            state = teacher.getCuState();
            if (state != null)
                state = stateService.get(state.getId());
        }
        ActionContext.getContext().put("state", state);
        return "examSet";
    }

    public String examSet() {
        State state = stateService.get(model.getId());
        state.setExamAble(model.getExamAble());
        state.setReExamAble(model.getReExamAble());
        state.setExamAttention(model.getExamAttention());
        stateService.update(state);
        return "examSetUI";
    }

    /**
     * 所有重新考试
     *
     * @return
     */
    public String reExam() {
        Teacher teacher = (Teacher) getCurrentUser();
        State state = teacher.getCuState();
        List<Application> applications = applicationService.findByState(state);
        for (Application application : applications) {
            application.setPaper(null);
            application.setExamState(false);
            applicationService.update(application);
        }
        return "examSetUI";
    }
}

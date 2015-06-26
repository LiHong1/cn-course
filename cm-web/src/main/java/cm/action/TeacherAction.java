package cm.action;


import cm.auth.AuthClass;
import cm.auth.AuthMethod;
import cm.commons.PageBean;
import cm.commons.util.MDCoder;
import cm.entity.Application;
import cm.entity.State;
import cm.entity.Student;
import cm.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Scope("prototype")
@AuthClass("base")
public class TeacherAction extends BaseAction<Teacher> {
    private Boolean joinAble;
    private Boolean loginAble;
    private Long type;
    private String option;

    public Boolean getJoinAble() {
        return joinAble;
    }

    public void setJoinAble(Boolean joinAble) {
        this.joinAble = joinAble;
    }

    public Boolean getLoginAble() {
        return loginAble;
    }

    public void setLoginAble(Boolean loginAble) {
        this.loginAble = loginAble;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @AuthMethod(role = "manage")
    public String add() {
        if (teacherService.findByNumber(model.getNumber()) == null) {
            model.setPassword("0000");
            model.setPassword(MDCoder.encodeMD5Hex(model.getPassword()));
            teacherService.save(model);
        }

        return "toList";
    }

    @AuthMethod(role = "manage")
    public String list() {
        List list = teacherService.getAll();
        ActionContext.getContext().put("teacherList", list);
        return "list";
    }

    @AuthMethod(role = "teacher")
    public String courseSetUI() {
        Teacher teacher = (Teacher) getCurrentUser();
        teacher = teacherService.get(teacher.getId());
        ActionContext.getContext().put("teacher", teacher);

        return "courseSetUI";
    }

    @AuthMethod(role = "teacher")
    public String classManageUI() {
        Teacher teacher = (Teacher) getCurrentUser();
        teacher = teacherService.get(teacher.getId());
        System.out.println(teacher.getCuState().getId());
        List<Application> applicationList = new ArrayList<Application>();
        PageBean<Application> page;
        Application application = new Application();
        application.setCreatedDate(null);
        application.setExamState(null);
        application.setTimeCount(null);
        application.setApplicationTime(null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", teacher.getCuState());
        if (getType() == null || getType() == -1 || getType().equals("")) {
            application.setType(null);
            page = applicationService.getPageByExample(application, getPageNum(), getPageSize(), map);
        } else {
            application.setType(getType());
            page = applicationService.getPageByExample(application, getPageNum(), getPageSize(), map);
        }
        ActionContext.getContext().getValueStack().push(page);
        ActionContext.getContext().put("type", getType());
        ActionContext.getContext().put("teacher", teacher);
        ActionContext.getContext().put("option", getOption());
        return "classManage";
    }

    @AuthMethod(role = "teacher")
    public String classSet() {
        State state = stateService.get(((Teacher) getCurrentUser()).getCuState().getId());
        state.setJoinAble(joinAble);
        state.setLoginAble(loginAble);
        stateService.update(state);
        return "toClassManage";
    }

    @AuthMethod(role = "teacher")
    public String studentList() {
        Application application = new Application();
        application.setCreatedDate(null);
        application.setExamState(null);
        application.setTimeCount(null);
        application.setApplicationTime(null);
        Teacher teacher = (Teacher) getCurrentUser();
        State state = teacher.getCuState();
        application.setState(state);
        application.setType(1L);
        PageBean<Application> page = applicationService.getPageByExample(application, this.getPageNum(), this.getPageSize());
        ActionContext.getContext().getValueStack().push(page);
        return "studentList";
    }

    @AuthMethod(role = "teacher")
    public String reExam() {
        Student student = studentService.get(model.getId());
        Application application = applicationService.find(student.getCuState(), student);
        application.setPaper(null);
        application.setExamState(false);
        applicationService.update(application);

        return "toStudentList";
    }

    @AuthMethod(role = "teacher")
    public String deleStudent() {
        Teacher teacher = (Teacher) getCurrentUser();
        State state = teacher.getCuState();
        Student student = studentService.get(model.getId());
        Application application = applicationService.find(state, student);
        applicationService.delete(application.getId());
        return "toStudentList";
    }

    @AuthMethod(role = "teacher")
    public String showStudentPaper() {
        Student student = studentService.get(model.getId());
        Application application = applicationService.find(student.getCuState(), student);
        if (application.getExamState()) {
            ActionContext.getContext().put("answer", application.getAnswer());
            ActionContext.getContext().put("paper", application.getPaper());
        } else {
            ActionContext.getContext().put("examState", application.getExamState());
        }
        return "studentPaper";
    }


}

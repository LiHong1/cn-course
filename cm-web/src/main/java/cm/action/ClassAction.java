package cm.action;


import cm.entity.Course;
import cm.entity.State;
import cm.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@Scope("prototype")
public class ClassAction extends BaseAction<cm.entity.Clazz> {
    private String className;
    private String teacherName;
    private String courseName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String add() {

        State state = null;

        Teacher teacher = teacherService.find("name", teacherName);
        Course course = courseService.find("name", courseName);
        cm.entity.Clazz clas = classService.find("name", className);

        //判断该班级是否存在
        if (clas == null && className != null && !className.equals("")) {
            model.setName(className);
            classService.save(model);
            clas = model;
        }
        if (stateService.find(course, clas) != null)
            return "toList";

        state = new State();
        state.setJoinAble(false);
        state.setLoginAble(false);
        state.setExamAble(false);
        state.setReExamAble(false);

        teacher.getCourses().add(course);
        teacher.getClasses().add(clas);
        teacherService.update(teacher);
        state.setClas(clas);
        state.setTeacher(teacher);
        state.setCourse(course);
        stateService.save(state);
        return "toList";
    }

    public String list() {

        List<State> stateList = stateService.getAll();
        List<Teacher> teacherList = teacherService.getAll();
        List<Course> courseList = courseService.getAll();
        ActionContext.getContext().put("stateList", stateList);
        ActionContext.getContext().put("courseList", courseList);
        ActionContext.getContext().put("teacherList", teacherList);
        return "list";
    }

    public String get() {
        List<State> list = stateService.find(classService.find("name", className), courseService.find("name", courseName), teacherService.find("name", teacherName));
        List<Course> courseList = courseService.getAll();
        List<Teacher> teacherList = teacherService.getAll();
        ActionContext.getContext().put("courseList", courseList);
        ActionContext.getContext().put("teacherList", teacherList);
        ActionContext.getContext().put("stateList", list);
        return "list";
    }

}

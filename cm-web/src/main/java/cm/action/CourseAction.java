package cm.action;


import cm.entity.Course;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@Scope("prototype")
public class CourseAction extends BaseAction<Course> {

    public String add() {
        if (courseService.find("name", model.getName()) == null && model.getName() != null && !model.getName().equals("")) {
            System.out.println(model.getId());
            courseService.save(model);
        }
        return "toList";
    }

    public String list() {
        List list = courseService.getAll();
        ActionContext.getContext().put("courseList", list);
        return "list";
    }

    public String save() {
        Course course = courseService.get(model.getId());
        course.setSchedule(model.getSchedule());
        course.setInformation(model.getInformation());
        courseService.update(course);
        return "toCourseSetUI";
    }

}

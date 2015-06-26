package cm.action;

import cm.dao.ReplyDao;
import cm.dao.TopicDao;
import cm.entity.*;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
    @Resource
    private TopicDao topicDao;
    @Resource
    private ReplyDao replyDao;

    public String list() {
        State state = null;
        Person user = getCurrentUser();
        if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            state = stateService.get(teacher.getCuState().getId());
            ActionContext.getContext().put("teacher", true);
        } else {
            Student student = (Student) user;
            state = stateService.get(student.getCuState().getId());
        }

        List<Topic> topics = topicDao.findByState(state);
        ActionContext.getContext().put("topics", topics);
        return "list";
    }

    public String addUI() throws Exception {
        if (getCurrentUser() instanceof Teacher) {

            return "addUI";
        } else {
            throw new RuntimeException("只有老师有权限创建讨论题!!");
        }
    }

    public String add() {
        Person person = getCurrentUser();
        if (person instanceof Teacher) {
            Teacher teacher = (Teacher) person;
            if (teacher != null) {
                model.setState(teacher.getCuState());
                model.setCreatedDate(Calendar.getInstance());
                topicDao.save(model);
            }
        }

        return "toList";
    }

    public String show() {
        Topic topic = topicDao.get(model.getId());
        ActionContext.getContext().put("topic", topic);
//        new QueryHelper(Reply.class, "r")//
//                .addCondition("r.topic=?", topic)//
//                .addOrderProperty("r.postTime", true)//
//                .preparePageBean(replyDao, pageNum, pageSize);
        return "show";
    }

    public String delete() {
        topicService.delete(model.getId());
        return "toList";
    }


}

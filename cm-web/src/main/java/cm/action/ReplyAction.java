package cm.action;


import cm.entity.Person;
import cm.entity.Reply;
import cm.entity.Student;
import cm.entity.Topic;
import cm.service.ReplyService;
import cm.service.TopicService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Calendar;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {
    private String topicId;
    @Resource
    private ReplyService replyService;
    @Resource
    private TopicService topicService;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String add() {
        Topic topic = topicService.get(topicId);
        model.setCreatedDate(Calendar.getInstance());
        model.setTopic(topic);
        Person user = getCurrentUser();
        if (user instanceof Student)
            model.setStudent((Student) user);
        replyService.save(model);
        return "topicShow";
    }
}

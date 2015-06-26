package cm.controller;

import cm.commons.PageBean;
import cm.commons.converter.docConverter.DocConverter;
import cm.commons.converter.pdfConverter.OpenOfficePDFConverter;
import cm.commons.converter.pdfConverter.PDFConverter;
import cm.commons.converter.swfConverter.SWFConverter;
import cm.commons.converter.swfConverter.SWFToolsSWFConverter;
import cm.entity.*;
import cm.service.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
public class TopicController {
	public static final int PageSize = 3;
	@Autowired
	private TopicService topicService;
	@Autowired
	private StateService stateService;
	@Autowired
	private ReplyService replyService;
	@RequestMapping(value = "teacher/topics",method = RequestMethod.GET)
	public String teacherTopicList(Model model,HttpSession session) {
		State state = null;
		Teacher teacher = (Teacher)session.getAttribute("user");
		state = stateService.get(teacher.getCuState().getId());
		model.addAttribute("teacher", true);
		List<Topic> topics = topicService.findByState(state);
		model.addAttribute("topics", topics);
		return "topic/list";
	}

	@RequestMapping(value = "student/topics",method = RequestMethod.GET)
	public String studentTopicList(Model model,HttpSession session) {
		State state = null;
		Student student = (Student)session.getAttribute("user");
	    state = stateService.get(student.getCuState().getId());
		List<Topic> topics = topicService.findByState(state);
		model.addAttribute("topics", topics);
		return "topic/list";
	}

	@RequestMapping(value = "teacher/topic/add",method = RequestMethod.GET)
	public String add(Model model,HttpSession session) throws Exception {
		Person person = (Person)session.getAttribute("user");
		if (person instanceof Teacher) {
			model.addAttribute(new Topic());
			return "topic/add";
		} else {
			throw new RuntimeException("只有老师有权限创建讨论题!!");
		}
	}

	@RequestMapping(value = "teacher/topic/add",method = RequestMethod.POST)
	public String add(@Valid Topic topic,BindingResult br,HttpSession session) {
		if (br.hasErrors())
			return "topic/add";
		Person person = (Person)session.getAttribute("user");
		if (person instanceof Teacher) {
			Teacher teacher = (Teacher) person;
			if (teacher != null) {
				topic.setState(teacher.getCuState());
				topic.setCreatedDate(Calendar.getInstance());
				topic.setId(UUID.randomUUID());
				topicService.save(topic);
			}
		}
		return "redirect:/teacher/topics";
	}

	@RequestMapping(value = "teacher/topic/show/{id}",method = RequestMethod.GET)
	public String teacherTopicShow(@PathVariable UUID id,Integer pageNum,Model model) {
		this.setDate(id,pageNum,model);
		return "topic/show";
	}

	@RequestMapping(value = "student/topic/show/{id}",method = RequestMethod.GET)
	public String studentTopicShow(@PathVariable UUID id,Integer pageNum,Model model) {
		this.setDate(id,pageNum,model);
		return "topic/show";
	}
	private void setDate(UUID id,Integer pageNum,Model model){
		Topic topic = topicService.get(id);
		if(pageNum == null){
			pageNum = 1;
		}
		model.addAttribute("topic", topic);
//        new QueryHelper(Reply.class, "r")//
//                .addCondition("r.topic=?", topic)//
//                .addOrderProperty("r.postTime", true)//
//                .preparePageBean(replyDao, pageNum, pageSize);
		Reply reply = new Reply();
		reply.setCreatedDate(null);
		Map map = new HashMap<String, Object>();
		map.put("topic", topic);
		PageBean page = replyService.getPageByExample(reply, pageNum, PageSize,map);
		System.out.println(page.getRecordList().size());
		model.addAttribute("page", page);
	}

	@RequestMapping(value = "teacher/topic/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable UUID id) {
		topicService.delete(id);
		return "redirect:/teacher/topics";
	}

	@RequestMapping(value = "teacher/topic/reply/add/{id}",method = RequestMethod.POST)
	public String teacherReplyAdd(@Valid Reply reply,BindingResult br,@PathVariable UUID id,HttpSession session) {
		if (br.hasErrors())
			return "repply/add";
		Topic topic = topicService.get(id);
		reply.setCreatedDate(Calendar.getInstance());
		reply.setTopic(topic);
		reply.setId(UUID.randomUUID());
		if (topic != null)
		replyService.save(reply);
		return "redirect:/teacher/topic/show/"+topic.getId();
	}
	@RequestMapping(value = "student/topic/reply/add/{id}",method = RequestMethod.POST)
	public String studentReplyAdd(@Valid Reply reply,BindingResult br,@PathVariable UUID id,HttpSession session) {
		if (br.hasErrors())
			return "repply/add";
		Topic topic = topicService.get(id);
		reply.setCreatedDate(Calendar.getInstance());
		reply.setTopic(topic);
		reply.setId(UUID.randomUUID());
		Person user = (Person)session.getAttribute("user");
		reply.setStudent((Student) user);
		if(topic != null)
		replyService.save(reply);
		return "redirect:/student/topic/show/"+topic.getId();
	}
}

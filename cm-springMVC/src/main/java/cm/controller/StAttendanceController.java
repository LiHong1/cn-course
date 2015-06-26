package cm.controller;

import cm.bean.AjaxObj;
import cm.commons.PageBean;
import cm.commons.config.SystemConfiguration;
import cm.commons.util.MDCoder;
import cm.entity.*;
import cm.enu.AttendanceType;
import cm.service.*;
import cm.util.GetCourseAndClassHttpPostUtil;
import cm.util.GetStudentHttpGetUtil;
import cm.util.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
public class StAttendanceController {
	public static final int PageSize = 3;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StAttendanceService stAttendanceService;
	@Autowired
	private StAttendanceItemService stAttendanceItemService;
	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(value = "/teacher/addStAttendance",method = RequestMethod.GET)
	public String addStAttendance(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		teacher = teacherService.get(teacher.getId());
		State state = teacher.getCuState();
		StAttendance stAttendance = new StAttendance();
		stAttendance.setState(state);
		stAttendance.setId(UUID.randomUUID());
		stAttendance.setLateCount(0);
		stAttendance.setLeaveCount(0);
		stAttendance.setMissCount(0);
		stAttendance.setUnKnowCount(0);
		stAttendanceService.save(stAttendance);

		List<Application> applicationList = new ArrayList<Application>();;
		List<StAttendanceItem> stAttendanceItems = new ArrayList<StAttendanceItem>();

		applicationList = applicationService.find(teacher.getCuState(),1L);
		if(applicationList != null){
			for(Application a : applicationList){
				StAttendanceItem stAttendanceItem = new StAttendanceItem();
				stAttendanceItem.setStudent(a.getStudent());
				stAttendanceItem.setId(UUID.randomUUID());
				stAttendanceItem.setAttendance(stAttendance);
				stAttendanceItemService.save(stAttendanceItem);
				stAttendanceItems.add(stAttendanceItem);
			}
		}
		orderStAttendanceItem(stAttendanceItems);
		model.addAttribute("stAttendanceItems",stAttendanceItems);
		return "teacher/addStAttendance";
	}



	@RequestMapping(value = "/teacher/stAttendances",method = RequestMethod.GET)
	public String stAttendances(Model model,HttpSession session) {
		Teacher teacher = (Teacher) session.getAttribute("user");
		teacher = teacherService.get(teacher.getId());
		State state = teacher.getCuState();
		List<StAttendance> stAttendances = stAttendanceService.getAllByState(state);
		model.addAttribute("stAttendances", stAttendances);
		return "teacher/stAttendances";
	}

	@RequestMapping(value = "/teacher/stAttendance/delete/{id}",method = RequestMethod.GET)
	public String deleteStAttendances(@PathVariable UUID id) {
		stAttendanceService.delete(id);
		return "redirect:/teacher/stAttendances";
	}

	@RequestMapping(value = "/teacher/stAttendance/update/{id}",method = RequestMethod.GET)
	public String showStAttendances(@PathVariable UUID id,Model model) {
		StAttendance stAttendance = stAttendanceService.get(id);
		List<StAttendanceItem> stAttendanceItems = stAttendanceItemService.getAll(stAttendance);
		orderStAttendanceItem(stAttendanceItems);
		model.addAttribute("stAttendanceItems", stAttendanceItems);
		return "teacher/addStAttendance";
	}

    public void orderStAttendanceItem(List<StAttendanceItem> stAttendanceItems){
		Collections.sort(stAttendanceItems, new Comparator<StAttendanceItem>() {
			public int compare(StAttendanceItem arg0, StAttendanceItem arg1) {
				return arg0.getStudent().getNumber().compareTo(arg1.getStudent().getNumber());
			}
		});
	}

}

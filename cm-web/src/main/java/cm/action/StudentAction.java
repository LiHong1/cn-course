package cm.action;


import cm.commons.PageBean;
import cm.commons.util.MDCoder;
import cm.dao.StudentDao;
import cm.dao.TimeLogDao;
import cm.entity.*;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TimeLogDao timeLogDao;
    private int option;
    private boolean nextStudent;
    private String password1;
    private String answer;

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public boolean getNextStudent() {
        return nextStudent;
    }

    public void setNextStudent(boolean nextStudent) {
        this.nextStudent = nextStudent;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String list() {
        PageBean<Student> page = studentService.getPage(pageNum, pageSize);
        ActionContext.getContext().getValueStack().push(page);
        return "list";
    }

    public String delete() {
        studentService.delete(model.getId());
        return "toList";
    }

    public String likeDelete() {
        studentService.likeDelete(model.getNumber());
        return "toList";
    }

    /**
     * 我的作业
     *
     * @return
     */
    public String jobList() {
        Student student = (Student) getCurrentUser();
        List<Job> jobs = jobService.findJobs(student, student.getCuState());
        ActionContext.getContext().put("jobs", jobs);
        return "jobList";
    }

    /**
     * 删除作业
     *
     * @return
     */
    public String jobDelete() {
        jobService.delete(model.getId());
        return "toJobList";
    }

    /**
     * 课程信息
     *
     * @return
     */
    public String showCourse() {
        Student student = (Student) ActionContext.getContext().getSession().get("user");
        student = studentService.get(student.getId());
        ActionContext.getContext().put("course", student.getCuState().getCourse());
        return "showCourse";
    }

    /**
     * 工程案例
     *
     * @return
     */
    public String showCase() {
        ActionContext.getContext().put("Case", caseService.get(model.getId()));
        return "showCase";
    }

    public String caseList() {
        Student student = (Student) getCurrentUser();
        ActionContext.getContext().put("caseList", caseService.find(student.getCuState()));
        return "caseList";
    }

    /**
     * 我的考试
     *
     * @return
     */
    public String myExam() {
        Student student = (Student) getCurrentUser();
        State state = stateService.get(student.getCuState().getId());
        Application application = applicationService.find(state, student);
        Boolean examState = application.getExamState();
        if (!state.getReExamAble() && !examState) {
            Paper paper = application.getPaper();
            if (paper != null) {
                ActionContext.getContext().put("problem", paper.getProblem());
                ActionContext.getContext().put("id", paper.getId());
                return "paper_unfinish";
            }

        }
        ActionContext.getContext().put("examState", examState);
        ActionContext.getContext().put("examAttention", state.getExamAttention());
        ActionContext.getContext().put("reExamAble", state.getReExamAble());
        ActionContext.getContext().put("examAble", state.getExamAble());
        return "myExam";
    }

    public String getPaper() {
        Student student = (Student) getCurrentUser();
        State state = stateService.get(student.getCuState().getId());
        Application application = applicationService.find(state, student);
        List<Paper> papers = new ArrayList<Paper>();
        for (Paper paper : state.getPapers()) {
            if (paper.getType())
                papers.add(paper);
        }
        Paper paper = null;
        if (papers.size() > 0) {
            int index = (int) (Math.random() * papers.size());
            paper = papers.get(index);
        }
        application.setPaper(paper);
        application.setAnswer(null);
        applicationService.update(application);
        ActionContext.getContext().put("problem", paper.getProblem());
        ActionContext.getContext().put("id", paper.getId());
        return "paper_unfinish";
    }

    /**
     * 提交试卷
     *
     * @return
     */
    public String submitPaper() {
        Student student = (Student) getCurrentUser();
        State state = student.getCuState();
        Application application = applicationService.find(state, student);
        if (state.getExamAble()) {
            application.setAnswer(getAnswer());
            application.setExamState(true);
            applicationService.update(application);
        } else {
            return "toMyExam";
        }
        model.setId(application.getId());
        return "submitSuccess";
    }

    public String paperShow() {
        Application application = applicationService.get(model.getId());
        ActionContext.getContext().put("application", application);
        return "paper_finished";
    }

    public String addUI() {
        return "addUI";
    }

    public String add() {
        if (!model.getPassword().equals(password1)) {
            addActionMessage("该口令输入不一致，注册失败！");
            return "addUI";
        }

        if (model.getNumber() != null) {
            Student student = studentService.findByNumber(model.getNumber());
            if (student != null) {
                addActionMessage("该学号已注册，注册失败！");
                return "addUI";
            } else {
                model.setPassword(MDCoder.encodeMD5Hex(model.getPassword()));
                studentService.save(model);
            }
        }

        ActionContext.getContext().put("success", "恭喜你，注册成功！");
        return "toLogin";
    }


    public String resetPassword() {
        Student student = studentService.get(model.getId());
        student.setPassword(MDCoder.encodeMD5Hex("0000"));
        studentService.update(student);
        return "toTeacherStudentList";
    }

    public String show() {
        Teacher teacher = (Teacher) getCurrentUser();
        State state = teacher.getCuState();
        Student student = null;
        Application application;
        List<Student> studentList = new ArrayList<Student>();
        List<Application> applications = applicationService.findByState(state);

        for (Application a : applications) {
            if (a.getType() == 1l) {
                student = a.getStudent();
                studentList.add(student);
            }
        }
        orderStudentByNumber(studentList);
        if (getNextStudent() == true) {
            student = studentService.get(model.getId());
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i) == student) {
                    i = i + 1;
                    if (i < studentList.size())
                        student = studentList.get(i);
                    System.out.println(student.getName());
                }
            }

        } else if (model.getId() != null)
            student = studentService.get(model.getId());
        studentList.remove(student);
        application = applicationService.find(state, student);
        TimeLogging timeLogging = new TimeLogging();
        timeLogging.setCreatedDate(null);
        timeLogging.setUpdatedDate(null);
        timeLogging.setLoginTime(null);
        timeLogging.setApplication(application);
        PageBean<TimeLogging> page = timeLogService.getPageByExample(timeLogging, getPageNum(), getPageSize());
        ActionContext.getContext().getValueStack().push(page);
        ActionContext.getContext().put("studentList", studentList);
        ActionContext.getContext().put("student", student);
        ActionContext.getContext().put("option", option);
        ActionContext.getContext().put("jobs", jobService.findJobs(student, state));
        ActionContext.getContext().put("application", application);
        return "show";

    }

    public void orderStudentByNumber(List<Student> students) {
        int k = students.size();
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 1; j < k; j++)
                if (Long.parseLong(students.get(j - 1).getNumber()) > Long.parseLong(students.get(j).getNumber())) {
                    Student student = students.get(j - 1);
                    students.set(j - 1, students.get(j));
                    students.set(j, student);
                    flag = true;
                }
            k--;
        }
    }

}

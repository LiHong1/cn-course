package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 申请实体
 */
@Entity
@Table(name = "T_APPLICATION")
public class Application extends DomainObject {
    //最近登录时间
    private Calendar latestLoginTime;
    //最近退出时间
    private Calendar latestLogoutTime;
    //考试状态
    private Boolean examState = false;//false表示还没有进行考试
    //登录次数计数
    private Long timeCount = 0L;
    //登陆历史
    private Set<TimeLogging> times;
    //加入班级时间
    private Calendar joinClassTime;
    //申请状态
    private Long type = 0L;//0 等待审核  1 已通过  2 已拒绝
    //学生
    private Student student;
    //申请时间
    private Calendar applicationTime = Calendar.getInstance();
    //对应的课程班级教室
    private State state;
    //答案
    private String answer;
    //试卷
    private Paper paper;
    //Date类型的最近登录时间
    private Date latestLoginDate;
    //Date类型的最近退出时间
    private Date latestLogoutDate;
    //Date类型的申请时间
    private Date applicationDate;
    //Date类型的加入班级时间
    private Date joinClassDate;


    @Column(name = "TYPE", nullable = false)
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @ManyToOne()
    @JoinColumn(name = "STUDENT_ID")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPLICATION_TIME", nullable = false, length = 10)
    public Calendar getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Calendar time) {
        this.applicationTime = time;
        if (time != null)
            this.setApplicationDate(time.getTime());
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "JOIN_CLASS_TIME", length = 10)
    public Calendar getJoinClassTime() {
        return joinClassTime;
    }

    public void setJoinClassTime(Calendar joinClassTime) {
        this.joinClassTime = joinClassTime;
        if(joinClassTime != null){
            this.setJoinClassDate(joinClassTime.getTime());
        }
    }

    @OneToMany(mappedBy = "application", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<TimeLogging> getTimes() {
        return times;
    }

    public void setTimes(Set<TimeLogging> times) {
        this.times = times;
    }

    @Column(name = "TIME_COUNT")
    public Long getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Long timeCount) {
        this.timeCount = timeCount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LATEST_LOGIN_TIME", length = 10)
    public Calendar getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Calendar latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
        if (latestLoginTime != null)
            this.setLatestLoginDate(latestLoginTime.getTime());
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LATEST_LOGOUT_TIME", length = 10)
    public Calendar getLatestLogoutTime() {
        return latestLogoutTime;
    }

    public void setLatestLogoutTime(Calendar latestLogoutTime) {
        this.latestLogoutTime = latestLogoutTime;
        if (latestLogoutTime != null)
            this.setLatestLogoutDate(latestLogoutTime.getTime());
    }

    public Boolean getExamState() {
        return examState;
    }

    public void setExamState(Boolean examState) {
        this.examState = examState;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(columnDefinition = "text", nullable = true)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAPER_ID")
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @Transient
    public Date getLatestLoginDate() {
        return latestLoginDate;
    }

    public void setLatestLoginDate(Date date) {
        this.latestLoginDate = date;
    }

    @Transient
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date date) {
        this.applicationDate = date;
    }

    @Transient
    public Date getJoinClassDate() {
        return joinClassDate;
    }

    public void setJoinClassDate(Date date) {
        this.joinClassDate = date;
    }

    @Transient
    public Date getLatestLogoutDate() {
        return latestLogoutDate;
    }

    public void setLatestLogoutDate(Date date) {
        this.latestLogoutDate = date;
    }
}

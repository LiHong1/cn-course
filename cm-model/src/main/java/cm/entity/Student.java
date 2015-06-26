package cm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生实体
 */

@Entity
@Table(name = "T_STUDENT")
public class Student extends Person implements Serializable{

    //当前登录的课程班级
    private State cuState;
    //作业
    private List<Job> jobs = new ArrayList<Job>();
    //申请
    private List<Application> applications = new ArrayList<Application>();
    //回复
    private List<Reply> replies = new ArrayList<Reply>();
    //登录IP RemoteAddr
    private String remoteAddr;
    //出勤情况
    private List<StAttendanceItem> stAttendanceItems;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CU_STATE_ID")
    public State getCuState() {
        return cuState;
    }

    public void setCuState(State cuState) {
        this.cuState = cuState;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy(value = "id desc")
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Column(name = "REMOTE_ADDR")
    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @OneToMany(mappedBy = "student")
    public List <StAttendanceItem> getStAttendanceItems() {
        return this.stAttendanceItems;
    }

    public void setStAttendanceItems(List<StAttendanceItem> stAttendanceItems) {
        this.stAttendanceItems = stAttendanceItems;
    }
}

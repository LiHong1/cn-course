package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;

/**
 * 作业实体
 */
@Entity
@Table(name = "T_JOB")
public class Job extends DomainObject {
    //作业名称
    private String jobName;
    //学生
    private Student student;
    //课程
    private State state;
    //路径
    private String path;

    @Column(name = "JOB_NAME")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "JOB_PATH")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

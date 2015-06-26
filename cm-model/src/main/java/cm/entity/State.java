package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 班级老师课程等对应关系实体
 */
@Entity
@Table(name = "T_STATE")
public class State extends DomainObject {

    private static final long serialVersionUID = 1L;
    //能否加入
    private Boolean joinAble = false;// 0 不能加入 1能加入
    //能否登录
    private Boolean loginAble = false;// 0 关闭 1正常
    //考试状态
    private Boolean examAble = false;
    //能否重新考试
    private Boolean reExamAble = false;
    //考试注意事情
    private String examAttention;

    private Set<Teacher> cuTeacher;
    //当前登录的学生
    private Set<Student> cuStudent;
    //课程
    private Course course;
    //老师
    private Teacher teacher;
    //班级
    private Clazz clas;
    //申请班级
    private Set<Application> applications;
    //学习材料
    private Set<Material> materials = new HashSet<Material>();// 学习资料
    //案例
    private Set<Case> cases = new HashSet<Case>();
    //试卷
    private Set<Paper> papers = new HashSet<Paper>();
    //作业
    private Set<Job> jobs;
    //讨论主题
    private Set<Topic> topics = new HashSet<Topic>();
    //出勤情况
    private Set<StAttendance> attendances = new HashSet<StAttendance>();

    @OneToMany(mappedBy = "state",fetch = FetchType.LAZY)
    public Set<StAttendance> getAttendances() {
        return this.attendances;
    }

    public void setAttendances(Set<StAttendance> attendances) {
        this.attendances = attendances;
    }

    @Column(name = "EXAM_ABLE")
    public Boolean getExamAble() {
        return examAble;
    }

    public void setExamAble(Boolean examAble) {
        this.examAble = examAble;
    }

    @Column(name = "RE_EXAM_ABLE")
    public Boolean getReExamAble() {
        return reExamAble;
    }

    public void setReExamAble(Boolean reExamAble) {
        this.reExamAble = reExamAble;
    }

    @Column(name = "EXAM_ATTENTION")
    public String getExamAttention() {
        return examAttention;
    }

    public void setExamAttention(String examAttention) {
        this.examAttention = examAttention;
    }

    @OneToMany(mappedBy = "cuState", fetch = FetchType.LAZY)
    public Set<Teacher> getCuTeacher() {
        return cuTeacher;
    }

    public void setCuTeacher(Set<Teacher> cuTeacher) {
        this.cuTeacher = cuTeacher;
    }

    @OneToMany(mappedBy = "cuState", fetch = FetchType.LAZY)
    public Set<Student> getCuStudent() {
        return cuStudent;
    }

    public void setCuStudent(Set<Student> cuStudent) {
        this.cuStudent = cuStudent;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    @Column(name = "JOIN_ABLE")
    public Boolean getJoinAble() {
        return joinAble;
    }

    public void setJoinAble(Boolean joinAble) {
        this.joinAble = joinAble;
    }

    @Column(name = "LOGIN_ABLE")
    public Boolean getLoginAble() {
        return loginAble;
    }

    public void setLoginAble(Boolean loginAble) {
        this.loginAble = loginAble;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_ID")
    public Clazz getClas() {
        return clas;
    }

    public void setClas(Clazz clas) {
        this.clas = clas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Case> getCases() {
        return cases;
    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @OneToMany(mappedBy = "state", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }
}

package cm.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 老师实体
 */
@Entity
@Table(name = "T_TEACHER")
public class Teacher extends Person implements Serializable {
    //所有的课程班级对应关系
    private Set<State> states;
    //所有的课程
    private Set<Course> courses = new HashSet<Course>();
    //所有班级
    private Set<Clazz> classes = new HashSet<Clazz>();
    //登录时的请求的课程 班级
    private State cuState;
    //明文密码
    private String unencry_password;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_COURSES_TEACHERS",
            joinColumns = @JoinColumn(name = "TEACHER_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "T_CLASSES_TEACHERS",
            joinColumns = @JoinColumn(name = "TEACHER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLASS_ID")
    )
    public Set<Clazz> getClasses() {
        return classes;
    }

    public void setClasses(Set<Clazz> classes) {
        this.classes = classes;
    }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CU_STATE_ID")
    public State getCuState() {
        return cuState;
    }

    public void setCuState(State cuState) {
        this.cuState = cuState;
    }

    @Column(name="UNENCRY_PASSWORD")
    public String getUnencry_password() {
        return this.unencry_password;
    }

    public void setUnencry_password(String unencry_password) {
        this.unencry_password = unencry_password;
    }
}

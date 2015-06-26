package cm.entity;


import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 课程
 */
@Entity
@Table(name = "T_COURSE")
public class Course extends DomainObject {

    //对应得班级课程
    private Set<State> states;
    //课程名
    private String name;
    //课程信息
    private String information;
    //进度安排
    private String schedule;
    //任课老师
    private Set<Teacher> teachers = new HashSet<Teacher>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    @Column(name = "NAEM")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "INFORMATIION")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Column(name = "SCHEDULE")
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }


}

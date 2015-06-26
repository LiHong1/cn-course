package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 班级
 */
@Entity
@Table(name = "T_CLASS")
public class Clazz extends DomainObject {

    //班级名称
    private String name;
    //教师
    private Set<Teacher> teachers = new HashSet<Teacher>();
    //对应的课程教师
    private Set<State> states = new HashSet<State>();

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "classes", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @OneToMany(mappedBy = "clas", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

}

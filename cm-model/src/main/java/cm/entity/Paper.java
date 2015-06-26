package cm.entity;

import cm.commons.DomainObject;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

/**
 * 试卷实体
 */
@Entity
@Table(name = "T_PAPER")
public class Paper extends DomainObject {
    //问题
    private String problem;
    //标准答案
    private String answer;
    //试卷名称
    private String name;
    //状态
    private Boolean type = false;//是否加入考试
    //课程对应关系
    private State state;

    //所有学生的申请(有学生答案)
    private Set<Application> applications = new HashSet<Application>();

    @Column(name = "PROBLEM", columnDefinition = "text", nullable = true)
    @NotEmpty(message = "问题不能为空")
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Column(name = "ANSWER", columnDefinition = "text", nullable = true)
    @NotEmpty(message = "标准答案不能为空")
    @Length(max = 5000,message = "答案不能超过5000个字符")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @OneToMany(mappedBy = "paper", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> answers) {
        applications = answers;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stateId")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "NAME")
    @NotEmpty(message = "试题标题不能为空")
    @Length(max = 100,message = "标题不能超过100个字符")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TYPE")
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}

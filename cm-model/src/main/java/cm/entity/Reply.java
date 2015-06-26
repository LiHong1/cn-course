package cm.entity;

import cm.commons.DomainObject;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Calendar;
import java.util.Date;

/**
 * 讨论区的回复
 */
@Entity
@Table(name = "T_REPLY")
public class Reply extends DomainObject {

    //学生
    private Student student;
    //内容
    private String content;
    //主题
    private Topic topic;
    //Date类型的创建时间
    private Date createTime;

    /**
     * 获得回复的学生
     *
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    public Student getStudent() {
        return student;
    }

    /**
     * 设置回复的学生
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * 获取内容
     *
     * @return
     */
    @Column(name = "CONTENT", length = 10000)
    @NotEmpty(message = "内容不能为空")
    @Length(max = 5000,message = "内容不能超过5000个字符")
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取主题
     *
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOPIC_ID")
    public Topic getTopic() {
        return topic;
    }

    /**
     * 设置主题
     *
     * @param topic
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * 获取Date类型的创建时间
     *
     * @return
     */
    @Transient
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置Date类型的创建时间
     *
     * @param date
     */
    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    /**
     * 设置创建时间
     *
     * @param createdDate
     */
    public void setCreatedDate(Calendar createdDate) {
        super.setCreatedDate(createdDate);
        if (createdDate != null)
            this.setCreateTime(createdDate.getTime());
    }
}

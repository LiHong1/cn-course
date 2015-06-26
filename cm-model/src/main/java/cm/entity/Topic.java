package cm.entity;

import cm.commons.DomainObject;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 讨论主题
 */
@Entity
@Table(name = "T_TOPIC")
public class Topic extends DomainObject {

    //标题
    private String title;
    //回复
    private Set<Reply> replies = new HashSet<Reply>();
    // 回复数量
    private Long replyCount = 0L;
    //对应的课程班级对应关系
    private State state;
    //内容
    private String content;
    //Date类型的创建时间
    private Date createTime;

    /**
     * 获取标题
     *
     * @return
     */
    @Column(name = "TITLE")
    @NotEmpty(message = "标题不能为空")
    @Max(value = 100,message = "标题不能超过100个字符")
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取回复
     *
     * @return
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOPIC_ID")
    public Set<Reply> getReplies() {
        return replies;
    }

    /**
     * 设置回复
     *
     * @param replies
     */
    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    /**
     * 获得回复数量
     *
     * @return
     */
    @Column(name = "REPLY_COUNT")
    public Long getReplyCount() {
        return replyCount;
    }

    /**
     * 设置回复数量
     *
     * @param replyCount
     */
    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 获得对应关系
     *
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    public State getState() {
        return state;
    }

    /**
     * 设置对应关系
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 获取内容
     *
     * @return
     */
    @Column(name = "CONTENT", length = 10000)
    @NotEmpty(message = "内容不能为空")
    @Max(value = 5000,message = "内容不能超过5000个字符")
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

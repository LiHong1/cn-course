package cm.commons.bean;

import cm.commons.NonDeletableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * 新闻类
 *
 * @author leizhenchun
 */
@Entity
@Table(name = "T_C_NEWS")
public class News extends NonDeletableDomainObject {
    /**
     *
     */
    private static final long serialVersionUID = 6467332915230356873L;
    // 标题
    private String title;
    // 内容
    private String content;
    // 分类
    private Integer category;
    // 显示日期
    private Calendar showDate;
    // 发布日期
    private Calendar publishDate;
    // 过期日期
    private Calendar expiredDate;
    // 顺序
    private Integer order;
    // 是否审核
    private Boolean approved;
    // 审核者id
    private Long approverId;
    // 审核备注
    private String approvedComment;
    // 审核日期
    private Calendar approvedDate;

    /**
     * 标题
     *
     * @return
     */
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 内容
     *
     * @return
     */
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    /**
     * 内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 显示日期
     *
     * @return
     */
    @Column(name = "SHOW_DATE")
    public Calendar getShowDate() {
        return showDate;
    }

    /**
     * 显示日期
     *
     * @param showDate
     */
    public void setShowDate(Calendar showDate) {
        this.showDate = showDate;
    }

    /**
     * 发布日期
     *
     * @return
     */
    @Column(name = "PUBLISH_DATE")
    public Calendar getPublishDate() {
        return publishDate;
    }

    /**
     * 发布日期
     *
     * @param publishDate
     */
    public void setPublishDate(Calendar publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 是否审核
     *
     * @return
     */
    @Column(name = "IS_APPROVED")
    public Boolean getApproved() {
        return approved;
    }

    /**
     * 是否审核
     *
     * @param approved
     */
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * 分类
     *
     * @return
     */
    @Column(name = "CATEGORY")
    public Integer getCategory() {
        return category;
    }

    /**
     * 分类
     *
     * @param category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 过期日期
     *
     * @return
     */
    @Column(name = "EXPIRED_DATE")
    public Calendar getExpiredDate() {
        return expiredDate;
    }

    /**
     * 过期日期
     *
     * @param expiredDate
     */
    public void setExpiredDate(Calendar expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * 顺序
     *
     * @return
     */
    @Column(name = "SHOW_ORDER")
    public Integer getOrder() {
        return order;
    }

    /**
     * 顺序
     *
     * @param order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 审核备注
     *
     * @return
     */
    @Column(name = "COMMENT")
    public String getApprovedComment() {
        return approvedComment;
    }

    /**
     * 审核备注
     *
     * @param approvedComment
     */
    public void setApprovedComment(String approvedComment) {
        this.approvedComment = approvedComment;
    }

    /**
     * 审核者id
     *
     * @return
     */
    @Column(name = "APPROVED_BY")
    public Long getApproverId() {
        return approverId;
    }

    /**
     * 审核者id
     *
     * @param approverId
     */
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    /**
     * 审核日期
     *
     * @return
     */
    @Column(name = "APPROVED_DATE")
    public Calendar getApprovedDate() {
        return approvedDate;
    }

    /**
     * 审核日期
     *
     * @param approvedDate
     */
    public void setApprovedDate(Calendar approvedDate) {
        this.approvedDate = approvedDate;
    }
}
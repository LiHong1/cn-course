package cm.commons;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 * 领域对象
 *
 * @author leizhenchun
 */
@MappedSuperclass
public class DomainObject implements Serializable {


    private UUID id;
    // 创建者
    private UUID createdBy;
    // 创建时间
    private Calendar createdDate = Calendar.getInstance();
    // 最后更新者
    private UUID updatedBy;
    // 最后更新时间
    private Calendar updatedDate;
    //操作    1表示增加  2表示修改  3表示删除
    private Integer operate;
    //用于页面显示的创建时间
    private Date createTime;
    //用于页面显示更新时间
    private Date updateTime;

    @Column(name = "OPERATE")
    public Integer getOperate() {
        return operate;
    }

    /**
     * 设置操作
     *
     * @param operate
     */
    public void setOperate(Integer operate) {
        this.operate = operate;
    }


    /**
     * 获取创建人Id
     *
     * @return
     */
    @Column(name = "CREATED_BY")
    public UUID getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人Id
     *
     * @param createdBy
     */
    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }


/*    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")*/
    @Column(name = "ID",columnDefinition = "BINARY(16)")
    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * 获取创建日期
     *
     * @return
     */
    @Column(name = "CREATED_DATE")
    public Calendar getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建日期，<p><b>直接保存对象时不需要调用此方法</b>
     * <p>
     * 此方法无效，对象创建日期在保存的时候由系统自动设置，不需要重复设置
     * <p>
     *
     * @param createdDate
     */
    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
        if(createdDate!=null)
            this.setCreateTime(createdDate.getTime());
    }

    /**
     * 获取最后更新人Id
     *
     * @return
     */
    @Column(name = "UPDATED_BY")
    public UUID getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置最后更新人Id
     *
     * @param updatedBy
     */
    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取最后更新日期
     *
     * @return
     */
    @Column(name = "UPDATED_DATE")
    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    /**
     * 设置最后更新日期，<p><b>直接更新对象时不需要调用此方法</b>
     * <p>
     * 此方法无效，对象更新日期在保存的时候由系统自动设置，不需要重复设置
     * <p>
     *
     * @param updatedDate
     */
    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
        if(updatedDate !=null)
            this.setUpdateTime(updatedDate.getTime());
    }


    @Transient
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    @Transient
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }
}

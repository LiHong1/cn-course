package cm.commons;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 逻辑删除的领域对象
 *
 * @author leizhenchun
 */
@MappedSuperclass
public class NonDeletableDomainObject extends DomainObject {
    /**
     *
     */
    private static final long serialVersionUID = -3490685804677163040L;

    //是否删除
    private Boolean deleted = Boolean.FALSE;

    /**
     * 获取是否删除
     *
     * @return
     */
    @Column(name = "IS_DELETED")
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除
     *
     * @param deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}

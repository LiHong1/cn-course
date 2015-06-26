package cm.commons.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 主键项
 *
 * @author leizhenchun
 */
@Entity
@Table(name = "T_C_PRIMARY_KEY")
public class PrimaryKeyItem {
    //主键名
    private String keyName;
    //下一个id
    private Long nextId;
    //id增长值
    private Integer incrementBy;

    @Id
    @Column(name = "KEY_NAME")
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Column(name = "NEXT_ID")
    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    @Column(name = "INCREMENT_BY")
    public Integer getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(Integer incrementBy) {
        this.incrementBy = incrementBy;
    }

}

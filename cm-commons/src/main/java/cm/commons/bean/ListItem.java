package cm.commons.bean;

import cm.commons.DomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 列表项
 *
 * @author lzc
 */
@Entity
@Table(name = "T_C_LIST_ITEM")
public class ListItem extends DomainObject {
    /**
     *
     */
    private static final long serialVersionUID = 8982904628502118634L;
    // 键
    private String key;
    // 值
    private String value;

    @Column(name = "LIST_KEY")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "LIST_VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

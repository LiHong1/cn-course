package cm.commons.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 配置项
 *
 * @author leizhenchun
 */

@Entity
@Table(name = "T_C_CONFIGURATION")//, catalog = "users"
public class ConfigurationItem {
    //配置键名
    private String key;
    //配置值
    private String value;
    //值类型（未使用）
    private String valueType;

    @Id
    @Column(name = "CONF_KEY")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "CONF_VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "CONF_TYPE")
    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

}

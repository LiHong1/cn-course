package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;

/**
 * 人实体类
 */
@MappedSuperclass
public class Person extends DomainObject {
    //姓名
    private String name;
    // 密码
    private String password;
    //学号或教工号
    private String number;

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "NUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}

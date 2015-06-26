package cm.commons.bean;

import cm.commons.DomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 错误信息类
 *
 * @author lzc
 */
@Entity
@Table(name = "T_C_ERROR_CODE")
public class ErrorCodeInfo extends DomainObject {
    //错误码
    private int errorCode;
    //显示消息
    private String showMessage;
    //错误描述
    private String description;
    //错误所在模块
    private String module;

    @Id
    @Column(name = "ERROR_CODE")
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Column(name = "SHOW_MSG")
    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    @Column(name = "ERROR_MODULE")
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Column(name = "ERROR_DESC")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

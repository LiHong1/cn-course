package cm.commons.bean;

import cm.commons.DomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * 操作日志项
 *
 * @author leizhenchun
 */
@Entity
@Table(name = "T_C_OPER_LOG")
public class OperationLogItem extends DomainObject {
    /**
     *
     */
    private static final long serialVersionUID = -4915477841290338304L;
    private Long operatorId;
    private Long memberId;
    private String operationModule;
    private String operationName;
    private String operationContent;
    private String operationArgs;
    private String operationResult;
    private Calendar operationDateStart;
    private Calendar operationDateEnd;
    //操作者的名字
    private String operatorName;
    //登录机器名RemoteHost
    private String remoteHost;
    //登录IP RemoteAddr
    private String remoteAddr;
    //
    private String userAgent;

    @Column
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "OPERATOR_ID")
    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    @Column(name = "OPERATION_RESULT")
    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    @Column(name = "OPERATION_NAME")
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Column(name = "OPERATION_MODEULE")
    public String getOperationModule() {
        return operationModule;
    }

    public void setOperationModule(String operationModule) {
        this.operationModule = operationModule;
    }

    @Column(name = "OPERATION_ARGS")
    public String getOperationArgs() {
        return operationArgs;
    }

    public void setOperationArgs(String operationArgs) {
        this.operationArgs = operationArgs;
    }

    @Column(name = "OPERATION_CONTENT")
    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    @Column(name = "OPERATION_DATE_START")
    public Calendar getOperationDateStart() {
        return operationDateStart;
    }

    public void setOperationDateStart(Calendar operationDateStart) {
        this.operationDateStart = operationDateStart;
    }

    @Column(name = "OPERATION_DATE_END")
    public Calendar getOperationDateEnd() {
        return operationDateEnd;
    }

    public void setOperationDateEnd(Calendar operationDateEnd) {
        this.operationDateEnd = operationDateEnd;
    }

    @Column(name = "USER_AGENT")
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Column(name = "REMOTE_HOST")
    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    @Column(name = "REMOTE_ADDR")
    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

}

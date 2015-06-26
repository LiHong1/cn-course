package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/*
  登录日志
 */
@Entity
@Table(name = "T_TIME_LOGGING")
public class TimeLogging extends DomainObject {
    //登录时间
    private Calendar loginTime = Calendar.getInstance();
    //退出时间
    private Calendar logoutTime;
    //申请
    private Application application;

    private Date loginDate;
    private Date logoutDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGIN_TIME", nullable = false, length = 10)
    public Calendar getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Calendar loginTime) {
        this.loginTime = loginTime;
        if (loginTime != null)
            this.setLoginDate(loginTime.getTime());
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGOUT_TIME", length = 10)
    public Calendar getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Calendar logoutTime) {
        this.logoutTime = logoutTime;
        if (logoutTime != null)
            this.setLogoutDate(logoutTime.getTime());
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Transient
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date date) {
        this.loginDate = date;
    }

    @Transient
    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date date) {
        logoutDate = date;
    }
}

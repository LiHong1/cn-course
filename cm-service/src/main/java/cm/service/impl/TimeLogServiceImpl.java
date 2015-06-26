package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.TimeLogDao;
import cm.entity.Application;
import cm.entity.TimeLogging;
import cm.service.ApplicationService;
import cm.service.TimeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

@Service
@Transactional
public class TimeLogServiceImpl extends BaseServiceImpl<TimeLogging> implements TimeLogService {
    @Autowired
    private TimeLogDao timeLogDao;
    @Autowired
    private ApplicationService applicationImpl;

    @Override
    public void save(Application application) {
        TimeLogging time = new TimeLogging();
        time.setId(UUID.randomUUID());
        if (application.getLatestLoginTime() != null) {
            time.setLoginTime(application.getLatestLoginTime());
            if (application.getLatestLogoutTime() != null && application.getLatestLogoutTime().after(application.getLatestLoginTime()))
                time.setLogoutTime(application.getLatestLogoutTime());
            else {
                Calendar c = application.getLatestLoginTime();
                c.roll(Calendar.HOUR, 1);
                time.setLogoutTime(c);
            }
            time.setApplication(application);
            this.save(time);
            application.getTimes().add(time);
            application.setTimeCount(application.getTimeCount() + 1);
        }
        application.setLatestLoginTime(Calendar.getInstance());
        applicationImpl.update(application);
    }

} 

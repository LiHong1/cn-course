package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Application;
import cm.entity.TimeLogging;

/**
 * 学生登录历史记录日志Service接口
 *
 * @author li hong
 */

public interface TimeLogService extends BaseService<TimeLogging> {

    //保存登录的时间日志
    public void save(Application application);
}

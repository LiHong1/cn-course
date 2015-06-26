package cm.dao.impl;


import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.TimeLogDao;
import cm.entity.TimeLogging;
import org.springframework.stereotype.Repository;

/**
 * 学生登录历史记录日志Dao实现类
 *
 * @author li hong
 */
@Repository
public class TimeLogDaoImpl extends BaseDaoImpl<TimeLogging> implements TimeLogDao {

}

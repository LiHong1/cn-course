package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.ApplicationDao;
import cm.dao.StAttendanceDao;
import cm.entity.Application;
import cm.entity.StAttendance;
import cm.entity.State;
import cm.entity.Student;
import cm.service.ApplicationService;
import cm.service.StAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生申请Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class StAttendanceServiceImpl extends BaseServiceImpl<StAttendance> implements StAttendanceService {
     @Autowired
	 private StAttendanceDao stAttendanceDao;
	/**
	 * 根据对应关系获取出勤情况
	 * @param state
	 * @return
	 */
	public List<StAttendance> getAllByState(State state){
		return stAttendanceDao.getAllByState(state);
	}
} 

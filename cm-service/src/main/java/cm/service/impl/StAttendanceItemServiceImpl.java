package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.StAttendanceDao;
import cm.dao.StAttendanceItemDao;
import cm.entity.StAttendance;
import cm.entity.StAttendanceItem;
import cm.entity.State;
import cm.entity.Student;
import cm.service.StAttendanceItemService;
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
public class StAttendanceItemServiceImpl extends BaseServiceImpl<StAttendanceItem> implements StAttendanceItemService {
	 @Autowired
	 private StAttendanceItemDao stAttendanceItemDao;
	/**
	 * 查询所有学生考勤
	 * @param stAttendance
	 * @return
	 */
	public List<StAttendanceItem> getAll(StAttendance stAttendance){
		return  stAttendanceItemDao.getAll(stAttendance);
	}

	/**
	 * 学生查询自己的考勤情况
	 * @return
	 */
	public List<StAttendanceItem> getAll(Student student,State state){
		return stAttendanceItemDao.getAll(student,state);
	}
} 

package cm.service;


import cm.commons.service.BaseService;
import cm.entity.Application;
import cm.entity.StAttendance;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 学生申请Service接口
 *
 * @author li hong
 */
public interface StAttendanceService extends BaseService<StAttendance> {

	/**
	 * 根据对应关系获取出勤情况
	 * @param state
	 * @return
	 */
	public List<StAttendance> getAllByState(State state);
}

package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Application;
import cm.entity.StAttendance;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 学生出勤记录接口
 *
 * @author li hong
 */
public interface StAttendanceDao extends BaseDao<StAttendance> {
	/**
	 * 获取出勤表
	 * @return
	 */
	public List<StAttendance> getAllByState(State state);
}

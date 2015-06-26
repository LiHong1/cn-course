package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.StAttendance;
import cm.entity.StAttendanceItem;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 学生出勤记录接口
 *
 * @author li hong
 */
public interface StAttendanceItemDao extends BaseDao<StAttendanceItem> {
	/**
	 * 查询所有学生考勤
	 * @param stAttendance
	 * @return
	 */
	public List<StAttendanceItem> getAll(StAttendance stAttendance);

	/**
	 * 学生查询自己的考勤情况
	 * @return
	 */
	List<StAttendanceItem> getAll(Student student,State state);
}

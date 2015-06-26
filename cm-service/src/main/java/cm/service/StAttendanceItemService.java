package cm.service;


import cm.commons.service.BaseService;
import cm.entity.StAttendance;
import cm.entity.StAttendanceItem;
import cm.entity.State;
import cm.entity.Student;

import java.util.List;

/**
 * 学生考勤Service接口
 *
 * @author li hong
 */
public interface StAttendanceItemService extends BaseService<StAttendanceItem> {
	/**
	 * 查询相应班级的所有同学考勤
	 * @param stAttendance
	 * @return
	 */
	List<StAttendanceItem> getAll(StAttendance stAttendance);

	/**
	 * 学生查询自己的考勤情况
	 * @return
	 */
	List<StAttendanceItem> getAll(Student student,State state);

}

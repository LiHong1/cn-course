package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.StAttendanceDao;
import cm.dao.StAttendanceItemDao;
import cm.entity.StAttendance;
import cm.entity.StAttendanceItem;
import cm.entity.State;
import cm.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生出勤记录Dao实现类
 *
 * @author li hong
 */
@Repository
public class StAttendanceItemDaoImpl extends BaseDaoImpl<StAttendanceItem> implements StAttendanceItemDao {
	/**
	 * 查询所有学生考勤
	 * @param stAttendance
	 * @return
	 */
	public List<StAttendanceItem> getAll(StAttendance stAttendance){
		return  this.getSession().createCriteria(StAttendanceItem.class).add(Restrictions.eq("attendance",stAttendance))
				.list();
	}

	@Override
	public List<StAttendanceItem> getAll(Student student,State state){
		if(state == null){
			return null;
		}
		Criteria criteria = this.getSession().createCriteria(StAttendanceItem.class).add(Restrictions.eq("student", student))
				.add(Restrictions.in("attendance", state.getAttendances()))
				.addOrder(Order.asc("createdDate"));
		return criteria.list();
	}
}

package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.ApplicationDao;
import cm.dao.StAttendanceDao;
import cm.entity.*;
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
public class StAttendanceDaoImpl extends BaseDaoImpl<StAttendance> implements StAttendanceDao {

	@Override
	public List<StAttendance> getAllByState(State state){
		Criteria criteria = this.getSession().createCriteria(StAttendance.class).add(Restrictions.eq("state", state)).addOrder(Order.asc("createdDate"));
		return criteria.list();
	}
}

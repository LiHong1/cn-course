package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.ApplicationDao;
import cm.entity.Application;
import cm.entity.State;
import cm.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生申请Dao实现类
 *
 * @author li hong
 */
@Repository
public class ApplicationDaoImpl extends BaseDaoImpl<Application> implements ApplicationDao {
    @Override
    public List<Application> findByState(State state) {
        Criteria c = getSession().createCriteria(Application.class);
        c.add(Restrictions.eq("state", state));
        return c.list();
    }

    @Override
    public Application find(State state, Student student) {
        Criteria c = getSession().createCriteria(Application.class);
        c.add(Restrictions.eq("state", state));
        c.add(Restrictions.eq("student", student));
        return (Application) c.uniqueResult();

    }

    @Override
    public List<Application> find(State state, Long type) {
        Criteria c = getSession().createCriteria(Application.class);
        c.add(Restrictions.eq("state", state));
        c.add(Restrictions.eq("type", type));
        return c.list();
    }

}

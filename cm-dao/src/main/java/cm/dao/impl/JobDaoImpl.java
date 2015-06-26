package cm.dao.impl;


import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.JobDao;
import cm.entity.Job;
import cm.entity.State;
import cm.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程Dao的实现类
 *
 * @author li hong
 */
@Repository
public class JobDaoImpl extends BaseDaoImpl<Job> implements JobDao {
    @Override
    public List<Job> findJobs(Student student, State state) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Job.class);
        c.add(Restrictions.eq("student", student));
        c.add(Restrictions.eq("state", state));
        return c.list();
    }


}

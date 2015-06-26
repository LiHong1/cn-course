package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.StateDao;
import cm.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * 班级 课程 老师对应关系Dao实现类
 *
 * @author li hong
 */
@Repository
public class StateDaoImpl extends BaseDaoImpl<State> implements StateDao {
    @Override
    public List<State> find(Clazz clas, Course course, Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(State.class);
        if (clas != null) {
            c.add(Restrictions.eq("clas", clas));
        }
        if (course != null) {
            c.add(Restrictions.eq("course", course));
        }
        if (teacher != null) {
            c.add(Restrictions.eq("teacher", teacher));
        }
        return c.list();
    }

    @Override
    public State find(Course course, Clazz clas) {
        Criteria c = getSession().createCriteria(State.class);
        c.add(Restrictions.eq("course", course));
        c.add(Restrictions.eq("clas", clas));
        return (State) c.uniqueResult();
    }
    @Override
    public State get(UUID courseId,UUID classId){
        Criteria c = getSession().createCriteria(State.class);
        c.add(Restrictions.eq("course.id", courseId));
        c.add(Restrictions.eq("clas.id", classId));
        return (State) c.uniqueResult();
    }


}

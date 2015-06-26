package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.TeacherDao;
import cm.entity.Teacher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 老师Dao实现类
 *
 * @author li hong
 */
@Repository
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao {
    @Override
    public Teacher findByNumber(String number) {
        Criteria c = getSession().createCriteria(Teacher.class);
        c.add(Restrictions.eq("number", number));
        return (Teacher) c.uniqueResult();
    }

    @Override
    public Teacher login(String number, String password) {
        Criteria c = getSession().createCriteria(Teacher.class);
        c.add(Restrictions.eq("number", number));
        c.add(Restrictions.eq("password", password));
        return (Teacher) c.uniqueResult();
    }


}

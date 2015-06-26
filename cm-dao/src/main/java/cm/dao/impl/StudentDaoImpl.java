package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.StudentDao;
import cm.dto.StudentDto;
import cm.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 学生Dao实现类
 *
 * @author li hong
 */
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    @Override
    public Student findByNumber(String number) {
        /*Session session = sessionFactory.getCurrentSession();
        return (Student) session.createQuery(//
                "FROM Student WHERE number=?0 and operate <?1")//
                .setParameter("0", number)
                .setParameter("1", 2)
                .uniqueResult();*/
        Criteria c = getSession().createCriteria(Student.class);
        c.add(Restrictions.eq("number", number));
        //c.add(Restrictions.le("operate", 1));
        return (Student) c.uniqueResult();
    }

    @Override
    public Student get(String number, String password) {
        Criteria c = getSession().createCriteria(Student.class);
        c.add(Restrictions.eq("password", password));
        c.add(Restrictions.eq("number", number));
        //c.add(Restrictions.ne("operate", 2));
        return (Student) c.uniqueResult();
    }

    @Override
    public void likeDelete(String number) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Student.class);
        c.add(Restrictions.like("number", number, MatchMode.ANYWHERE));
        List<Student> studentList = (List<Student>) c.list();
        for (Student student : studentList) {
            this.delete(student);
        }
    }

    @Override
    public void delete(Student s) {
        Session session = sessionFactory.getCurrentSession();
        //s.setOperate(2);
        s.setUpdatedDate(Calendar.getInstance());
        session.delete(s);
    }

    @Override
    public void update(Student s) {
        Session session = sessionFactory.getCurrentSession();
        //s.setOperate(1);
        session.update(s);
    }

    @Override
    public List<StudentDto> getLatestUpdate(Calendar c) {
        Session session = sessionFactory.getCurrentSession();
        List<StudentDto> studentDtos = new ArrayList<StudentDto>();
               /* =session.createSQLQuery("select id,name,createdDate,updatedDate,operate from jxnu_student where updatedDate > ?")
                .setParameter(0, c).setResultTransformer(Transformers.aliasToBean(StudentDto.class)).list();*/

        int studentDtoSize = studentDtos.size();
        for (int i = 0; i < studentDtoSize; i++) {
            StudentDto s = studentDtos.get(i);
            if (s.getCreatedDate() != null && s.getCreatedDate().after(c.getTime()))//如果是在之前创建的，就需要客户端增加
            {
                if (s.getOperate() == 2) {//如果被删除了  就不需要传给客户端
                    studentDtos.remove(i);
                } else
                    s.setOperate(0);
            }


        }
        return studentDtos;
    }

    /**
     * webservice 获取所有实体
     *
     * @return
     */
    @Override
    public List<Student> getAll() {
        return getSession().createCriteria(Student.class).add(Restrictions.le("operate", 2)).list();
    }
}

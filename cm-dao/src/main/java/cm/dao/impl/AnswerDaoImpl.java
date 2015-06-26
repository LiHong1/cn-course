//package cm.dao.impl;
//
//import cm.commons.dao.impl.BaseDaoImpl;
//import cm.dao.AnswerDao;
//import cm.entity.Answer;
//import cm.entity.Paper;
//import cm.entity.Student;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//
///**
// * 试卷答案Dao实现类
// *
// * @author li hong
// */
//@Repository
//public class AnswerDaoImpl extends BaseDaoImpl<Answer> implements AnswerDao {
//    public Answer find(Paper paper, Student student) {
//
//        Criteria c=getSession().createCriteria(Answer.class);
//        c.add(Restrictions.eq("paper",paper));
//        c.add(Restrictions.eq("student", student));
//                return (Answer)c.uniqueResult();
//       /* return (Answer) session.createQuery(""//
//                + "FROM Answer a WHERE a.paper=?0 AND a.student=?1")//
//                .setParameter("0", paper)
//                .setParameter("1", student)
//                .uniqueResult();*/
//    }
//
//    public Answer find(List<Paper> papers, Student student) {
//        UUID[] ids = new UUID[papers.size()];
//        for (int i = 0; i < papers.size(); i++) {
//            ids[i] = papers.get(i).getId();
//        }
//        Criteria c=getSession().createCriteria(Answer.class);
//        c.add(Restrictions.eq("student",student));
//        c.add(Restrictions.in("paper.id",ids));
//        return (Answer)c.uniqueResult();
//        /*return (Answer) session.createQuery(//
//                "FROM Answer a WHERE a.paper.id IN (:ids) AND a.student=?0")//
//                .setParameterList("ids", ids)//
//                .setParameter("0", student)
//                .uniqueResult();*/
//    }
//
//
//}

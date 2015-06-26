//package cm.dao.impl;
//
//import cm.commons.dao.impl.BaseDaoImpl;
//import cm.dao.OperationDao;
//import cm.entity.Operation;
//import org.hibernate.Session;
//import org.springframework.stereotype.Repository;
//
///**
// * 案例Dao的实现类
// *
// * @author li hong
// */
//@Repository
//public class OperationDaoImpl extends BaseDaoImpl<Operation> implements OperationDao {
//
//    public Operation get(String uri) {
//        Session session = sessionFactory.getCurrentSession();
//        return (Operation) session.createQuery("FROM Operation o WHERE o.uri=?0")
//                .setParameter("0", uri)
//                .uniqueResult();
//    }
//
//
//}

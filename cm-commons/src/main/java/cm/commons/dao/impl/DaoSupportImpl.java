//package cm.commons.dao.impl;
//
//import cm.commons.dao.DaoSupport;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//import java.util.UUID;
//
////import cm.commons.util.QueryHelper;
//
//public abstract class DaoSupportImpl<T> implements DaoSupport<T> {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Class<T> clazz;
//
//    public DaoSupportImpl() {
//        //使用反射技术获得T的真实类型
//        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//获取当前new的对象的 泛型的父类 类型
//        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];// 获取第一个类型参数的真实类型
//    }
//
//    public void save(T entity) {
//        getSession().save(entity);
//    }
//
//    public void update(T entity) {
//        getSession().update(entity);
//    }
//
//    public void delete(UUID id) {
//        Object obj = findById(id);
//        if (obj != null) {
//            getSession().delete(obj);
//        }
//    }
//
//    /**
//     * 删除实体
//     *
//     * @param entity
//     */
//    public void delete(T entity){
//        getSession().delete(entity);
//    }
//
//    public List<T> findAll() {
//        return getSession().createQuery(//
//                "FROM " + clazz.getSimpleName() + " t ORDER BY t.createdDate")//
//                .list();
//    }
//
//    public T findById(UUID id) {
//        if (id == null) {
//            return null;
//        } else {
//            return (T) getSession().get(clazz, id);
//        }
//    }
//
//    public Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
////    public T findByName(String name) {
////        if (name == null) {
////            return null;
////        } else {
////            return (T) getSession().createQuery(//
////                    "FROM " + clazz.getSimpleName() + " u WHERE u.name=?0")//
////                    .setParameter("0", name)//
////                    .uniqueResult();
////        }
////    }
//
////    // 公共的查询分页信息的方法（最终版）
////    public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
////        // 参数列表
////        List<Object> parameters = queryHelper.getParameters();
////
////        // 查询本页的数据列表
////        Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
////        if (parameters != null) { // 设置参数
////            for (int i = 0; i < parameters.size(); i++) {
////                listQuery.setParameter(i, parameters.get(i));
////            }
////        }
////        listQuery.setFirstResult((pageNum - 1) * pageSize);
////        listQuery.setMaxResults(pageSize);
////        List<Object> list = listQuery.list(); // 执行查询
////
////        // 查询总记录数量
////        Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
////        if (parameters != null) { // 设置参数
////            for (int i = 0; i < parameters.size(); i++) {
////                countQuery.setParameter(i, parameters.get(i));
////            }
////        }
////        Long count = (Long) countQuery.uniqueResult(); // 执行查询
////
////        return new PageBean(pageNum, pageSize, count.intValue(), list);
////    }
//
//}

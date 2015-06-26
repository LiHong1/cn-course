package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.NonDeletableDomainObject;
import cm.commons.PageBean;
import cm.commons.dao.NonDeletableBaseDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/**
 * 逻辑删除对象基础Dao实现类
 *
 * @author lzc
 */
public class NonDeletableBaseDaoImpl<T extends NonDeletableDomainObject> extends BaseDaoImpl<T> implements
        NonDeletableBaseDao<T> {

    public NonDeletableBaseDaoImpl() {
        super();
    }

    @Override
    public T get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        T result = (T) session.get(entityClass, id);
        if (result == null || Boolean.TRUE.equals(result.getDeleted())) {
            throw new BusinessException(CommonsErrorCode.DOMAIN_OBJECT_NOT_EXIST, entityClass.toString() + "不存在：id="
                    + id);
        }
        return result;
    }


    @Override
    public T getI(Serializable id) {
        return super.get(id);
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        entity.setDeleted(true);
        session.update(entity);
    }

    @Override
    public void deleteFromDb(T entity) {
        super.delete(entity);
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) session.createCriteria(entityClass).add(Restrictions.eqOrIsNull("deleted", false))
                .list();
        return result;
    }

    @Override
    public List<T> getAll(int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) session.createCriteria(entityClass).add(Restrictions.eqOrIsNull("deleted", false))
                .setFirstResult(firstResult).setMaxResults(maxResults).list();
        return result;
    }

    @Override
    public long getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(entityClass).add(Restrictions.eqOrIsNull("deleted", false))
                .setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PageBean<T> getPage(int currentPage, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) session.createCriteria(entityClass).add(Restrictions.eqOrIsNull("deleted", false))
                .setFirstResult(pageSize * (currentPage - 1)).setMaxResults(pageSize).addOrder(Order.desc("id")).list();
        long count = (Long) session.createCriteria(entityClass).add(Restrictions.eqOrIsNull("deleted", false))
                .setProjection(Projections.rowCount()).uniqueResult();
        return new PageBean<T>(currentPage, pageSize, count, list);
    }

    @Override
    public List<T> getAllI() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) session.createCriteria(entityClass).list();
        return result;
    }

//    @Override
//    public List<T> getAllI(int firstResult, int maxResults) {
//        Session session = sessionFactory.getCurrentSession();
//        @SuppressWarnings("unchecked")
//        List<T> result = (List<T>) session.createCriteria(entityClass).setFirstResult(firstResult)
//                .setMaxResults(maxResults).list();
//        return result;
//    }

    @Override
    public long getAllCountI() {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PageBean<T> getPageI(int currentPage, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) session.createCriteria(entityClass).setFirstResult(pageSize * (currentPage - 1))
                .setMaxResults(pageSize).list();
        long count = (Long) session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
        return new PageBean<T>(currentPage, pageSize, count, list);
    }
}

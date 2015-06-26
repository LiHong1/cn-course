package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.DomainObject;
import cm.commons.PageBean;
import cm.commons.dao.BaseDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 基础Dao实现类
 *
 * @param <T>
 * @author lzc
 */
@Transactional
public abstract class BaseDaoImpl<T extends DomainObject> implements BaseDao<T> {
    protected Class<T> entityClass;
    @Autowired
    protected SessionFactory sessionFactory;


    public BaseDaoImpl() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        T result = (T) session.get(entityClass, id);
//                T result = (T) session.createCriteria(entityClass).add(Restrictions.ne("operate", 3)).uniqueResult();
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.DOMAIN_OBJECT_NOT_EXIST, entityClass.toString() + "not exist：id="
                    + id);
        }
        return result;
    }

    @Override
    public Serializable save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        Calendar now = Calendar.getInstance();
        entity.setCreatedDate(now);
        entity.setUpdatedDate(now);
        return session.save(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void delete(UUID uuid) {
        System.out.println("in delete");
        Session session = getSessionFactory().getCurrentSession();
        T t = get(uuid);
        System.out.println(t);
        if (t != null)
            session.delete(t);
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        entity.setUpdatedDate(Calendar.getInstance());
        session.update(entity);
    }

    @Override
    public T merge(T entity) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        T result = (T) session.merge(entity);
        return result;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<T> result = (List<T>) session.createCriteria(entityClass).addOrder(Order.asc("createdDate")).list();
        return result;

    }

    @Override
    public List<T> getAll(int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        List<T> result = (List<T>) session.createCriteria(entityClass).setFirstResult(firstResult)
                .setMaxResults(maxResults).list();
        return result;
    }

    @Override
    public long getAllCount() {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PageBean<T> getPage(int currentPage, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        List<T> list = (List<T>) session.createCriteria(entityClass).setFirstResult((currentPage - 1) * pageSize)
                .setMaxResults(pageSize).list();
        long count = (Long) session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
        return new PageBean<T>(currentPage, pageSize, count, list);
    }

    @Override
    public PageBean<T> getPageByExample(T t, int currentPage, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Example.create(t));
        long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        criteria.setProjection(null);
        List<T> list = (List<T>) criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();

        return new PageBean<T>(currentPage, pageSize, count, list);
    }

    @Override
    public PageBean<T> getPageByExample(T t, int currentPage, int pageSize, Map<String, Object> properties) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Example.create(t));
        if (properties != null) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }
        long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        criteria.setProjection(null);
        List<T> list = (List<T>) criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();

        return new PageBean<T>(currentPage, pageSize, count, list);
    }

    @Override
    public PageBean<T> getPageByExampleOrder(T t, int currentPage, int pageSize, Order order) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Example.create(t));
        long count = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        criteria.setProjection(null);
        if (order != null) {
            criteria.addOrder(order);
        }

        List<T> list = (List<T>) criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();

        return new PageBean<T>(currentPage, pageSize, count, list);
    }

    @Override
    public List<T> getByExample(T t) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Example.create(t));
        List<T> result = (List<T>) criteria.list();
        return result;
    }

    @Override
    public List<T> getByExample(T t,Map<String,Object> properties) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Example.create(t));
        if (properties != null) {
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }
        List<T> result = (List<T>) criteria.list();
        return result;
    }

    @Override
    public void save(List<T> items, int commitCount) {
        int count = 0;

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();
            for (T item : items) {
                session.save(item);
                count++;
                if (count % commitCount == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            throw new BusinessException(e.getMessage(), e.getCause());

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public T get(String propety, Object value) {
        Criteria c = getSession().createCriteria(entityClass);
        c.add(Restrictions.eq(propety, value));
        return (T) c.uniqueResult();
    }

    @Override
    public void sessionFlush() {
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void sessionClear() {
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

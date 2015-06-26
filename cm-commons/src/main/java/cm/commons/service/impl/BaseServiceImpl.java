package cm.commons.service.impl;

import cm.commons.DomainObject;
import cm.commons.PageBean;
import cm.commons.dao.BaseDao;
import cm.commons.service.BaseService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T extends DomainObject> implements BaseService<T> {
    @Autowired
    protected BaseDao<T> baseDao;

    @Override
    @Transactional(readOnly = true)
    public T get(Serializable id) {
        return baseDao.get(id);
    }

    @Override
    @Transactional
    public Serializable save(T entity) {
        return baseDao.save(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Override
    @Transactional
    public void delete(Serializable id) {
        baseDao.delete(baseDao.get(id));
    }

    @Override
    @Transactional
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    @Transactional
    public T merge(T entity) {
        return baseDao.merge(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return baseDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll(int currentPage, int pageSize) {
        return baseDao.getAll(currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public long getAllCount() {
        return baseDao.getAllCount();
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<T> getPage(int currentPage, int pageSize) {
        return baseDao.getPage(currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<T> getPageByExample(T t, int currentPage, int pageSize) {
        return baseDao.getPageByExample(t, currentPage, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<T> getPageByExample(T t, int currentPage, int pageSize, Map<String, Object> properties) {
        return baseDao.getPageByExample(t, currentPage, pageSize, properties);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<T> getPageByExampleOrder(T t, int currentPage, int pageSize, Order order) {
        return baseDao.getPageByExampleOrder(t, currentPage, pageSize, order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getByExample(T t) {
        return baseDao.getByExample(t);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getByExample(T t,Map<String,Object> map) {
        return baseDao.getByExample(t,map);
    }

    @Override
    @Transactional
    public void save(List<T> items, int commitCount) {
        baseDao.save(items, commitCount);
    }

    @Override
    @Transactional
    public T find(String propety, Object value) {
        return baseDao.get(propety, value);
    }
}

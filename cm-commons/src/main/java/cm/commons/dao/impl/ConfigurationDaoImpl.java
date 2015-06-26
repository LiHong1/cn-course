package cm.commons.dao.impl;

import cm.commons.bean.ConfigurationItem;
import cm.commons.dao.ConfigurationDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置Dao实现类
 *
 * @author lzc
 */
@Repository
public class ConfigurationDaoImpl implements ConfigurationDao {
    @Autowired
    private SessionFactory sessionFactory;

    /*
     * public SessionFactory getSessionFactory() { return sessionFactory; }
     */

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public void save(String key, String value) {
        ConfigurationItem item = new ConfigurationItem();
        item.setKey(key);
        item.setValue(value);
        save(item);
    }


    @Transactional
    public void save(ConfigurationItem item) {
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }


    @Transactional(readOnly = true)
    public long getKeyCount() {
        Session session = sessionFactory.getCurrentSession();
        long result = (Long) session.createCriteria(ConfigurationItem.class).setProjection(Projections.rowCount())
                .uniqueResult();
        return result;
    }

    @Transactional(readOnly = true)
    public ConfigurationItem get(String key) {
        Session session = sessionFactory.getCurrentSession();
        return (ConfigurationItem) session.get(ConfigurationItem.class, key);
    }


    @Transactional(readOnly = true)
    public List<ConfigurationItem> getAll() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<ConfigurationItem> result = (List<ConfigurationItem>) session.createCriteria(ConfigurationItem.class)
                .list();
        return result;
    }


    @Transactional
    public void delete(ConfigurationItem item) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }


    @Transactional
    public void update(ConfigurationItem item) {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }


    @Transactional
    public void merge(ConfigurationItem item) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(item);
    }


    @Transactional
    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }


    @Transactional
    public void clear() {
        sessionFactory.getCurrentSession().clear();
    }

}

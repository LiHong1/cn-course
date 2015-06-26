package cm.commons.dao.impl;

import cm.commons.bean.PrimaryKeyItem;
import cm.commons.dao.KeyGeneratorDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 主键Dao实现类
 *
 * @author lzc
 */
@Repository
public class KeyGeneratorDaoImpl implements KeyGeneratorDao {
    private static final long ID_STARTING = 1000001;
    private static final int INCREMENT_BY_DEF = 1;
    @Autowired
    private SessionFactory sessionFactory;

    public long getNextId(String keyName, long incrementBy) {
        long nextId = ID_STARTING;

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            PrimaryKeyItem pkItem = (PrimaryKeyItem) session.get(PrimaryKeyItem.class, keyName);

            if (pkItem == null) {
                pkItem = new PrimaryKeyItem();
                pkItem.setKeyName(keyName);
                pkItem.setNextId(ID_STARTING);
                pkItem.setIncrementBy(INCREMENT_BY_DEF);
                session.save(pkItem);
            }
            if (pkItem.getNextId() == null) {
                pkItem.setNextId(ID_STARTING);
            }
            if (pkItem.getIncrementBy() == null) {
                pkItem.setIncrementBy(INCREMENT_BY_DEF);
            }
            nextId = pkItem.getNextId();

            if (incrementBy <= 0) {
                pkItem.setNextId(nextId + pkItem.getIncrementBy());
            } else {
                pkItem.setNextId(nextId + incrementBy);
            }

            session.update(pkItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return nextId;
    }


    public long getNextId(String keyName) {
        return getNextId(keyName, 0);
    }
}

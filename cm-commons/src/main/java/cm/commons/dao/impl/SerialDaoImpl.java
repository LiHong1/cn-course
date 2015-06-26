package cm.commons.dao.impl;

import cm.commons.bean.SerialNumber;
import cm.commons.dao.SerialDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

/**
 * 序列号Dao实现类
 *
 * @author lzc
 */
@Repository
public class SerialDaoImpl implements SerialDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SerialNumber get(String serialKey, Calendar currentDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SerialNumber s where s.keyName = ? and s.dateStr = ?");
        @SuppressWarnings("unchecked")
        List<SerialNumber> list = (List<SerialNumber>) query.list();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

}

package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.TopicDao;
import cm.entity.State;
import cm.entity.Topic;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 讨论主题Dao实现类
 *
 * @author li hong
 */
@Repository
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {
    @Override
    public List<Topic> findByState(State state) {
        Criteria c = getSession().createCriteria(Topic.class);
        c.add(Restrictions.eq("state", state));
        c.addOrder(Order.asc("createdDate"));
        return c.list();
    }
}

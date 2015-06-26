package cm.dao.impl;

import cm.commons.dao.impl.BaseDaoImpl;
import cm.dao.ReplyDao;
import cm.entity.Reply;
import cm.entity.Topic;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 回复Dao实现类
 *
 * @author li hong
 */
@Repository
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao {
    @Override
    public List<Reply> findByTopic(Topic topic) {
        Criteria c = getSession().createCriteria(Reply.class);
        c.add(Restrictions.eq("topic", topic));
        return c.list();

    }

}

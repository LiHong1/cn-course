package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.Reply;
import cm.entity.Topic;

import java.util.List;

/**
 * 回复Dao接口
 *
 * @author li hong
 */
public interface ReplyDao extends BaseDao<Reply> {
    /**
     * 列出回复
     *
     * @param topic
     * @return
     */
    List<Reply> findByTopic(Topic topic);
}

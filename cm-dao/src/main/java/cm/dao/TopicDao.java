package cm.dao;

import cm.commons.dao.BaseDao;
import cm.entity.State;
import cm.entity.Topic;

import java.util.List;

/**
 * 讨论主题Dao接口
 *
 * @author li hong
 */
public interface TopicDao extends BaseDao<Topic> {
    /**
     * 查找讨论主题
     *
     * @return
     */
    List<Topic> findByState(State state);
}

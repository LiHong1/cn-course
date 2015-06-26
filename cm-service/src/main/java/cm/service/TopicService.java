package cm.service;

import cm.commons.service.BaseService;
import cm.entity.State;
import cm.entity.Topic;

import java.util.List;

/**
 * 讨论主题Service接口
 *
 * @author li hong
 */


public interface TopicService extends BaseService<Topic> {
    /**
     * 查找讨论主题
     *
     * @param state
     * @return
     */
    public List<Topic> findByState(State state);
}

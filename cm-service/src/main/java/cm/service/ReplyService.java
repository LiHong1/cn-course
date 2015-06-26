package cm.service;

import cm.commons.service.BaseService;
import cm.entity.Reply;
import cm.entity.Topic;

import java.util.List;

/**
 * 回复Service接口
 *
 * @author li hong
 */


public interface ReplyService extends BaseService<Reply> {
    /**
     * 列出回复
     *
     * @param topic
     * @return
     */
    List<Reply> findByTopic(Topic topic);


}

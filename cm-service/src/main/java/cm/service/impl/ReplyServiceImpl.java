package cm.service.impl;


import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.ReplyDao;
import cm.entity.Reply;
import cm.entity.Topic;
import cm.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 回复Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {
    @Autowired
    private ReplyDao replyDao;

    @Override
    public List<Reply> findByTopic(Topic topic) {
        return replyDao.findByTopic(topic);
    }


}

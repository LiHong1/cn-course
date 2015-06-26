package cm.service.impl;

import cm.commons.service.impl.BaseServiceImpl;
import cm.dao.TopicDao;
import cm.entity.State;
import cm.entity.Topic;
import cm.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 讨论主题Service实现类
 *
 * @author li hong
 */
@Service
@Transactional
public class TopicServiceImpl extends BaseServiceImpl<Topic> implements TopicService {
    @Autowired
    private TopicDao topicDao;

    @Override
    public List<Topic> findByState(State state) {
        return topicDao.findByState(state);
    }


} 

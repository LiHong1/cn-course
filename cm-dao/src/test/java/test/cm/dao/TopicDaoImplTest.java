package test.cm.dao;

import cm.dao.StateDao;
import cm.dao.TopicDao;
import cm.entity.State;
import cm.entity.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class TopicDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private StateDao stateDao;
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setUp() {
        session = sessionFactory.openSession();
        if (!TransactionSynchronizationManager.hasResource(sessionFactory))
            TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }

    @Test
    public void testAdd() {
        Topic topic = new Topic();
        topic.setId(UUID.randomUUID());
        topic.setTitle("测试");
        topicDao.save(topic);
        Topic actual = topicDao.get(topic.getId());
        assertEquals("测试", actual.getTitle());
        topicDao.delete(actual);
    }

    @Test
    public void testFindByState() {
        Topic topic = new Topic();
        topic.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        topic.setState(state);
        stateDao.save(state);
        topicDao.save(topic);
        List<Topic> topics = topicDao.findByState(state);
        topicDao.delete(topic);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Topic> topics = topicDao.getAll();
        assertNotNull(topics);
    }
}
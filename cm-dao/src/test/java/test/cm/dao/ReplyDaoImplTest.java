package test.cm.dao;

import cm.dao.ReplyDao;
import cm.dao.TopicDao;
import cm.entity.Reply;
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
public class ReplyDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private TopicDao topicDao;
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
        Reply except = new Reply();
        except .setId(UUID.randomUUID());
        replyDao.save(except);
        Reply actual = replyDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        replyDao.delete(except);
    }

    @Test
    public void testFindByTopic() throws Exception {
        Reply except = new Reply();
        except .setId(UUID.randomUUID());
        Topic topic = new Topic();
        topic.setId(UUID.randomUUID());
        topicDao.save(topic);
        except.setTopic(topic);
        replyDao.save(except);
        List<Reply> actuals = replyDao.findByTopic(topic);
        assertEquals(1, actuals.size());
        replyDao.delete(except);
        topicDao.delete(topic);
    }



    @Test
    public void testFindAll() throws Exception {
        Reply except = new Reply();
        except .setId(UUID.randomUUID());
        replyDao.save(except);
        List<Reply> replies = replyDao.getAll();
        assertNotNull(replies);
        replyDao.delete(except);
    }
}
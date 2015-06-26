package test.cm.dao;

import cm.dao.PaperDao;
import cm.dao.StateDao;
import cm.entity.Paper;
import cm.entity.State;
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

import static org.junit.Assert.*;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class PaperDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private PaperDao paperDao;
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
        Paper except = new Paper();
        except.setId(UUID.randomUUID());
        paperDao.save(except);
        Paper actual = paperDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
    }

    @Test
    public void testFindByState() throws Exception {
        Paper paper = new Paper();
        paper.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        paper.setState(state);
        stateDao.save(state);
        paperDao.save(paper);
        List<Paper> actuals = paperDao.findByState(state);
        assertNotNull(actuals);
        paperDao.delete(paper);
        stateDao.delete(state);
    }

    @Test
    public void testChangeType() {
        Paper paper = new Paper();
        paper.setId(UUID.randomUUID());
        paper.setType(false);
        paperDao.save(paper);
        paperDao.changePaperType(paper.getId());
        paper = paperDao.get(paper.getId());
        assertTrue(paper.getType());
        paperDao.delete(paper);
    }

    @Test
    public void testFindByIds() throws Exception {
        Paper paper1 = new Paper();
        paper1.setId(UUID.randomUUID());
        Paper paper2 = new Paper();
        paper2.setId(UUID.randomUUID());
        paperDao.save(paper1);
        paperDao.save(paper2);
        UUID[] ids = new UUID[2];
        ids[0] = paper1.getId();
        ids[1] = paper2.getId();
        paperDao.findByIds(ids);
        paperDao.delete(paper1);
        paperDao.delete(paper2);
    }

    @Test
    public void testFindAll() throws Exception {
        Paper paper = new Paper();
        paper.setId(UUID.randomUUID());
        paperDao.save(paper);
        List<Paper> papers = paperDao.getAll();
        assertNotNull(papers);
        paperDao.delete(paper);
    }
}
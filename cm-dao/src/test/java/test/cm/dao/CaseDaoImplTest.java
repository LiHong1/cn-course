package test.cm.dao;

import cm.dao.CaseDao;
import cm.dao.StateDao;
import cm.entity.Case;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class CaseDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private CaseDao caseDao;
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
        Case except = new Case();
        except.setId(UUID.randomUUID());
        caseDao.save(except);
        Case actual = caseDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        caseDao.delete(except);
    }

    @Test
    public void testFindByState() throws Exception {
        Case except = new Case();
        except.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        except.setState(state);
        stateDao.save(state);
        caseDao.save(except);
        List<Case> actual = caseDao.find(state);
        assertEquals(1, actual.size());
        caseDao.delete(except);
        stateDao.delete(state);
    }


    @Test
    public void testFindAll() throws Exception {
        Case c = new Case();
        c.setId(UUID.randomUUID());
        caseDao.save(c);
        List<Case> cases = caseDao.getAll();
        assertNotNull(cases);
        caseDao.delete(c);
    }
}
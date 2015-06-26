package test.cm.dao;

import cm.dao.ClazzDao;
import cm.entity.Clazz;
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
public class ClazzDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ClazzDao clazzDao;
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
        Clazz except = new Clazz();
        except.setId(UUID.randomUUID());
        clazzDao.save(except);
        Clazz actual = clazzDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        clazzDao.delete(except.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        Clazz except = new Clazz();
        except.setId(UUID.randomUUID());
        clazzDao.save(except);
        List<Clazz> clas = clazzDao.getAll();
        assertNotNull(clas);
        clazzDao.delete(except);
    }
}
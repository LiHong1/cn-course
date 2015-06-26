package test.cm.dao;

import cm.commons.util.MDCoder;
import cm.dao.ManageDao;
import cm.entity.Manage;
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

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class ManageDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ManageDao manageDao;
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
        /*Manage manage = new Manage();
        manage.setId(UUID.randomUUID());
        manage.setName("admin");
        manage.setNumber("admin");
        manage.setPassword(MDCoder.encodeMD5Hex("0000"));
        manageDao.save(manage);*/
      /*  Manage actual = manageDao.get(manage.getId());
        assertEquals(manage.getId(), actual.getId());
        manageDao.delete(manage);*/
    }


    @Test
    public void testLogin() throws Exception {
       /*  Manage except = new Manage();
        except.setId(UUID.randomUUID());
        except.setNumber("admin");
        except.setPassword("admin");
        manageDao.save(except);
        Manage actual = manageDao.login(except.getNumber(), except.getPassword());
        assertEquals("admin", actual.getNumber());
        manageDao.delete(except);*/
    }
}
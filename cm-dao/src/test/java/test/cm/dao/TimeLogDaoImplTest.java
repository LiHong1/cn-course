package test.cm.dao;

import cm.commons.PageBean;
import cm.dao.ApplicationDao;
import cm.dao.TimeLogDao;
import cm.entity.Application;
import cm.entity.TimeLogging;
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

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class TimeLogDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private TimeLogDao timeLogDao;
    @Autowired
    private ApplicationDao applicationDao;
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
        TimeLogging timeLogging = new TimeLogging();
        timeLogging.setId(UUID.randomUUID());
        timeLogDao.save(timeLogging);
        TimeLogging actual = timeLogDao.get(timeLogging.getId());
        assertEquals(timeLogging.getId(), actual.getId());
        timeLogDao.delete(timeLogging);
    }

    @Test
    public void testFindAll() throws Exception {
        TimeLogging timeLogging = new TimeLogging();
        timeLogging.setId(UUID.randomUUID());
        timeLogDao.save(timeLogging);
        List<TimeLogging> timeLoggings = timeLogDao.getAll();
        assertNotNull(timeLoggings);
        timeLogDao.delete(timeLogging.getId());
    }

   /* @Test
    public void testgetPage() throws Exception {
        Application application = applicationDao.get("402881814d5729a9014d5737dc8b0000");
        TimeLogging timeLogging = new TimeLogging();
        timeLogging.setApplication(application);
        timeLogging.setCreatedDate(null);
        timeLogging.setUpdatedDate(null);
        timeLogging.setLoginTime(null);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("application",application);
        PageBean<TimeLogging> pageBean = timeLogDao.getPageByExample(timeLogging,1,3,map);
        System.out.println(pageBean.getRecordList().size());
        System.out.println(pageBean.getRecordList().get(0).getApplication().getId());
    }*/
}
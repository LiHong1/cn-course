package test.cm.dao;

import cm.commons.PageBean;
import cm.commons.bean.MenuItem;
import cm.commons.dao.MenuItemDao;
import cm.dao.ApplicationDao;
import cm.dao.StateDao;
import cm.dao.StudentDao;
import cm.entity.Application;
import cm.entity.State;
import cm.entity.Student;
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
public class ApplicationDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ApplicationDao applicationDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StateDao stateDao;
    @Autowired
    private MenuItemDao menuDao;
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
        Application application = new Application();
        application.setId(UUID.randomUUID());
        application.setAnswer("答案");
        applicationDao.save(application);
        Application a = applicationDao.get(application.getId());
        System.out.println(a);
        System.out.println(application.getId());
        assertEquals("答案", a.getAnswer());
        applicationDao.delete(a.getId());
    }

    @Test
    public void test() {
        Application application = new Application();
        application.setId(UUID.randomUUID());
        application.setCreatedDate(null);
        application.setExamState(null);
        application.setTimeCount(null);
        application.setApplicationDate(null);
        application.setUpdatedDate(null);
        application.setApplicationTime(null);
        List<State> states = stateDao.getAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", states.get(0));
        PageBean<Application> pages = applicationDao.getPageByExample(application, 1, 2, map);
    }

    @Test
    public void testFindByState() throws Exception {
        Application application = new Application();
        application.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        application.setState(state);
        stateDao.save(state);
        applicationDao.save(application);
        List<Application> actual = applicationDao.findByState(state);
        assertEquals(1, actual.size());
        applicationDao.delete(application);
        stateDao.delete(state);
    }

    @Test
    public void testFindByStateAndType() throws Exception {
        Application application = new Application();
        application.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        application.setState(state);
        application.setType(0L);
        stateDao.save(state);
        applicationDao.save(application);
        List<Application> actual = applicationDao.find(state, 0L);
        assertEquals(1, actual.size());
        applicationDao.delete(application);
        stateDao.delete(state);


    }

    @Test
    public void testFindByStateAndStudent() throws Exception {
        Application application = new Application();
        application.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        application.setState(state);
        Student student = new Student();
        student.setId(UUID.randomUUID());
        application.setStudent(student);
        stateDao.save(state);
        studentDao.save(student);
        applicationDao.save(application);
        Application actual = applicationDao.find(state, student);
        assertEquals(application.getId(), actual.getId());
        applicationDao.delete(application);
        studentDao.delete(student);
        stateDao.delete(state);
    }

    @Test
    public void testFindAll() throws Exception {
        Application application = new Application();
        application.setId(UUID.randomUUID());
        applicationDao.save(application);
        List<Application> applications = applicationDao.getAll();
        assertNotNull(applications);
        applicationDao.delete(application.getId());
    }

    @Test
    public void testArrayList() throws Exception {
       List<MenuItem> menuItems = menuDao.getAll();
        for(MenuItem m : menuItems){
            System.out.println(m.getName()+m.getUrl()+m.getAuthority());
        }
    }

}
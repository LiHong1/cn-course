package test.cm.dao;

import cm.dao.ClazzDao;
import cm.dao.CourseDao;
import cm.dao.StateDao;
import cm.entity.Clazz;
import cm.entity.Course;
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

import static org.junit.Assert.assertNotNull;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class StateDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private StateDao stateDao;
    @Autowired
    private CourseDao courseDao;
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
        State except = new State();
        except.setId(UUID.randomUUID());
        stateDao.save(except);
        State actual = stateDao.get(except.getId());
        stateDao.delete(except.getId());
    }

    @Test
    public void testFind1() throws Exception {
        State except = new State();
        Clazz clazz = new Clazz();
        Course course = new Course();
        except.setId(UUID.randomUUID());
        clazz.setId(UUID.randomUUID());
        course.setId(UUID.randomUUID());
        clazzDao.save(clazz);
        courseDao.save(course);
        except.setClas(clazz);
        except.setCourse(course);
        stateDao.save(except);
        State actual = stateDao.find(course, clazz);
        stateDao.delete(except);
        courseDao.delete(course.getId());
        clazzDao.delete(clazz.getId());

    }

    @Test
    public void testFindAll1() throws Exception {
        List<State> states = stateDao.getAll();
        State state = stateDao.get(states.get(0).getId());
    }

    @Test
    public void testFindAll() throws Exception {
        State except = new State();
        except.setId(UUID.randomUUID());
        //stateDao.save(except);
        List<State> states = stateDao.getAll();
        assertNotNull(states);
        //stateDao.delete(except);
    }

}
package test.cm.dao;

import cm.dao.CourseDao;
import cm.entity.Course;
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
public class CourseDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private CourseDao courseDao;
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
        Course except = new Course();
        except.setId(UUID.randomUUID());
        courseDao.save(except);
        Course actual = courseDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        courseDao.delete(except.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        Course except = new Course();
        except.setId(UUID.randomUUID());
        courseDao.save(except);
        List<Course> Courses = courseDao.getAll();
        assertNotNull(Courses);
        courseDao.delete(except);
    }
}
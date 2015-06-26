package test.cm.dao;

import cm.dao.JobDao;
import cm.dao.StateDao;
import cm.dao.StudentDao;
import cm.entity.Job;
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

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class JobDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private StudentDao studentDao;
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
        Job except = new Job();
        except.setId(UUID.randomUUID());
        jobDao.save(except);
        Job actual = jobDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        jobDao.delete(except.getId());
    }

    @Test
    public void testFindJobs() throws Exception {
        Job except = new Job();
        except.setId(UUID.randomUUID());
        Student student = new Student();
        student.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        except.setState(state);
        except.setStudent(student);
        stateDao.save(state);
        studentDao.save(student);
        jobDao.save(except);
        List<Job> Jobs = jobDao.findJobs(student, state);
        assertNotNull(Jobs);
        jobDao.delete(except);
        studentDao.delete(student);
        stateDao.delete(state);
    }

    @Test
    public void testFindAll() throws Exception {
        Job except = new Job();
        except.setId(UUID.randomUUID());
        jobDao.save(except);
        List<Job> Jobs = jobDao.getAll();
        assertNotNull(Jobs);
        jobDao.delete(except);
    }
}
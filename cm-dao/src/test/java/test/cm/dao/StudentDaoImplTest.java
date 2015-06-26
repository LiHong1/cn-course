package test.cm.dao;

import cm.dao.StudentDao;
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
public class StudentDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private StudentDao studentDao;
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
        Student except = new Student();
        except.setId(UUID.randomUUID());
        except.setNumber("测试");
        studentDao.save(except);
        Student actual = studentDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        studentDao.delete(except);
    }

    @Test
    public void testGet() throws Exception {
        Student except = new Student();
        except.setId(UUID.randomUUID());
        except.setNumber("测试");
        except.setPassword("测试");
        studentDao.save(except);
        Student actual = studentDao.get(except.getNumber(), except.getPassword());
        assertEquals("测试", actual.getNumber());
        studentDao.delete(except);
    }

    @Test
    public void testFindByNumber() throws Exception {
        Student except = new Student();
        except.setId(UUID.randomUUID());
        except.setNumber("测试");
        except.setPassword("测试");
        studentDao.save(except);
        Student actual = studentDao.findByNumber(except.getNumber());
        assertEquals("测试", actual.getNumber());
        studentDao.delete(except);
    }


    @Test
    public void testFindAll() throws Exception {
        Student except = new Student();
        except.setId(UUID.randomUUID());
        studentDao.save(except);
        List<Student> students = studentDao.getAll();
        assertNotNull(students);
        studentDao.delete(except);
    }
}
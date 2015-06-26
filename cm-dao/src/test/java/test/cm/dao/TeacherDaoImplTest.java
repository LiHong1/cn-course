package test.cm.dao;

import cm.dao.TeacherDao;
import cm.entity.State;
import cm.entity.Teacher;
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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lzc on 2015/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/daoApplicationContextTest.xml")
public class TeacherDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private TeacherDao teacherDao;
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
        Teacher except = new Teacher();
        except.setId(UUID.randomUUID());
        except.setNumber("测试");
        teacherDao.save(except);
        Teacher actual = teacherDao.get(except.getId());
        assertEquals(except.getId(), actual.getId());
        teacherDao.delete(except);
    }

    @Test
    public void testLogin() throws Exception {
        Teacher except = new Teacher();
        except.setId(UUID.randomUUID());
        except.setNumber("测试");
        except.setPassword("测试");
        teacherDao.save(except);
        Teacher actual = teacherDao.login(except.getNumber(), except.getPassword());
        assertEquals("测试", actual.getNumber());
        teacherDao.delete(except);
    }

    @Test
    public void testFindByNumber() throws Exception {
        Teacher except = new Teacher();
        except.setId(UUID.randomUUID());
        except.setNumber("测试");
        except.setPassword("测试");
        teacherDao.save(except);
        Teacher actual = teacherDao.findByNumber(except.getNumber());
        assertEquals("测试", actual.getNumber());
        teacherDao.delete(except);
    }
    @Test
    public void testFindBy() throws Exception {

        Teacher actual = teacherDao.findByNumber("004388");
        System.out.println(actual.getStates().size());

    }

    @Test
    public void testFindAll() throws Exception {
        Teacher except=new Teacher();
        except.setId(UUID.randomUUID());
        teacherDao.save(except);
        List<Teacher> teachers = teacherDao.getAll();
        assertNotNull(teachers);
        teacherDao.delete(except);

    }
}
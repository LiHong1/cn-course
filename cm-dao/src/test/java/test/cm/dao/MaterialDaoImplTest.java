package test.cm.dao;

import cm.dao.CaseDao;
import cm.dao.MaterialDao;
import cm.dao.StateDao;
import cm.entity.Case;
import cm.entity.Material;
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
public class MaterialDaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private StateDao stateDao;
    @Autowired
    private CaseDao caseDao;
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
        Material material = new Material();
        material.setId(UUID.randomUUID());
        materialDao.save(material);
        Material actual = materialDao.get(material.getId());
        assertEquals(material.getId(), actual.getId());
        materialDao.delete(actual);
    }

    @Test
    public void testFindByState() {
        Material material = new Material();
        material.setId(UUID.randomUUID());
        State state = new State();
        state.setId(UUID.randomUUID());
        material.setState(state);
        stateDao.save(state);
        materialDao.save(material);
        List<Material> actuals = materialDao.findByState(state);
        assertNotNull(actuals);
        materialDao.delete(material);
        stateDao.delete(state);
    }

    @Test
    public void testFindByCase() {
        Material material = new Material();
        material.setId(UUID.randomUUID());
        Case c = new Case();
        c.setId(UUID.randomUUID());
        material.setCases(c);
        caseDao.save(c);
        materialDao.save(material);
        List<Material> actuals = materialDao.findByCase(c);
        assertNotNull(actuals);
        materialDao.delete(material);
        caseDao.delete(c);
    }

    @Test
    public void testFindAll() throws Exception {
        Material material = new Material();
        material.setId(UUID.randomUUID());
        materialDao.save(material);
        List<Material> materials = materialDao.getAll();
        assertNotNull(materials);
        materialDao.delete(material);

    }
}
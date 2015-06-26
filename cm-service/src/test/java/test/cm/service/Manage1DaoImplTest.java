/*
package test.cm.service;

import cm.commons.config.Config;
import cm.commons.config.SystemConfiguration;
import cm.commons.dao.ConfigurationDao;
import cm.commons.util.MDCoder;
import cm.dao.ManageDao;
import cm.entity.Manage;
import cm.service.ManageService;
import net.sf.ehcache.management.ManagementService;
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

import static org.junit.Assert.assertEquals;
import org.apache.commons.codec.digest.DigestUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
*/
/**
 * Created by lzc on 2015/4/24.
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceApplicationContextTest.xml")
public class Manage1DaoImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ManageDao manageDao;
    @Autowired
    public ManageService manageService;
    @Autowired
    public SystemConfiguration config;
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    @Before
    public void setUp() {
        session= sessionFactory.openSession();
        if(!TransactionSynchronizationManager.hasResource(sessionFactory))
            TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }
    @Test
    public void testAdd(){
        Manage manage=new Manage();
        manage.setNumber("admin");
        manage.setName("超级管理员");
        manage.setPassword(MDCoder.encodeMD5Hex("0000"));
        manageDao.save(manage);
        Manage actual=manageDao.get(manage.getId());
        assertEquals(manage.getId(), actual.getId());
        //manageDao.delete(manage);
    }


    @Test
    public void testLogin(){
        Manage manage=manageService.login("admin","0000");
        System.out.println(manage.getName());
        //manageDao.delete(manage);
    }

    @Test
    public void systemSet(){
        config.clear();
        config.addProperty("loginAble", false);
        config.addProperty("replaceMachineAble", false);
        config.addProperty("jobArea", 12);
        config.addProperty("startTerm", (new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
    }
}*/

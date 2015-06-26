package test.cm.commons.service;

import cm.commons.key.KeyFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/commonsApplicationContextTest.xml")
public class PrimaryKeyTest extends AbstractJUnit4SpringContextTests {
    private static String TEST_KEY = "TEST_KEY";
    private final Log logger = LogFactory.getLog(PrimaryKeyTest.class);

    @Test
    public void testGetKey() {

        logger.debug("C_TEST:newId=" + KeyFactory.nextId("C_TEST"));
        logger.debug("C_TEST:newId=" + KeyFactory.nextId("C_TEST2"));
    }
}

package test.cm.commons.service;

import cm.commons.service.SerialService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/commonsApplicationContextTest.xml")
public class SerialServiceTest extends AbstractJUnit4SpringContextTests {
    private final Log logger = LogFactory.getLog(SerialServiceTest.class);
    @Autowired
    private SerialService serialService;

    @Test
    public void testGetValue() {

        logger.debug("第1次获取流水号 = " + serialService.get("1", "101"));
        logger.debug("第1次获取流水号 = " + serialService.get("1", "101"));


    }
}

package test.cm.commons.service;

import cm.commons.service.LogService;
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
public class LogServiceTest extends AbstractJUnit4SpringContextTests {
    private final Log logger = LogFactory.getLog(LogServiceTest.class);
    @Autowired
    private LogService logService;

    @Test
    public void testClear() {
        logService.clearAll();
    }

    @Test
    public void testCreateLog() throws InterruptedException {
        for (int i = 1; i <= 1; i++) {
//            logService.log(0L, "testModule", "operationName", "operationContent", "operationArgs",
//                    "operationResult", "remoteHost", "remoteAddr", "userAgent", null, null);
        }
        Thread.sleep(1000);
    }
}

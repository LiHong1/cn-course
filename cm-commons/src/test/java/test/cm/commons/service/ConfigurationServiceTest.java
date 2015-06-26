package test.cm.commons.service;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/commonsApplicationContextTest.xml")
public class ConfigurationServiceTest extends AbstractJUnit4SpringContextTests {
    private final String TEST_KEY = "TEST_KEY";
    //private static ConfigurationService configService;
    private final Log logger = LogFactory.getLog(ConfigurationServiceTest.class);
    @Autowired
    private Configuration config;

    @Test
    public void testGetValue() {
        String oldValue;
        String newValue;

        oldValue = RandomStringUtils.randomAlphabetic(20);
        newValue = RandomStringUtils.randomAlphabetic(20);

        if (config.containsKey(TEST_KEY)) {
            config.clearProperty(TEST_KEY);
        }

        config.addProperty(TEST_KEY, oldValue);

        logger.debug("第1次获取TestKey = " + config.getString(TEST_KEY));
        logger.debug("第2次获取TestKey = " + config.getString(TEST_KEY));
        logger.debug("第3次获取TestKey = " + config.getString(TEST_KEY));
        logger.debug("第4次获取TestKey = " + config.getString(TEST_KEY));
        logger.debug("第5次获取TestKey = " + config.getString(TEST_KEY));

        String oldValue2 = config.getString(TEST_KEY);
        boolean containTestKey = config.containsKey(TEST_KEY);

        config.setProperty(TEST_KEY, newValue);

        logger.debug("第6次获取TestKey = " + config.getString(TEST_KEY));
        logger.debug("第7次获取TestKey = " + config.getString(TEST_KEY));
        logger.debug("第8次获取TestKey = " + config.getString(TEST_KEY));

        String newValue2 = config.getString(TEST_KEY);

        config.clearProperty(TEST_KEY);
        String valueAfterClear = config.getString(TEST_KEY);


        assertEquals(oldValue, oldValue2);
        assertTrue(containTestKey);
        assertEquals(newValue, newValue2);
        assertNull(valueAfterClear);
    }
}

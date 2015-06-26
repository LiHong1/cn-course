package test.cm.commons.service;

import cm.commons.EmailAddress;
import cm.commons.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/commonsApplicationContextTest.xml")
public class EmailAddressTest extends AbstractJUnit4SpringContextTests {

    private static final Log logger = LogFactory.getLog(EmailAddressTest.class);

    @Test
    public void testEmailAddress() throws BusinessException {
        EmailAddress email = new EmailAddress("abcabc@aaa.com");
        //EmailUtils.sendSimpleEmail(0L, "abc_abc@aaa.com", "title", "content", "type");
    }
}

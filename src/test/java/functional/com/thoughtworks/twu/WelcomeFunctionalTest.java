package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class WelcomeFunctionalTest {
    private HtmlUnitDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
        CommonSteps.saveProfileInformation(webDriver,"Reader Feeder User");
    }

    @Test
    public void shouldShowUserNameAfterLoginSuccessfully() {
        assertEquals("Reader Feeder User", webDriver.findElement(By.className("username")).getText());
    }

    @Test
    public void shouldStayOnSamePageOnRefresh(){
        webDriver.navigate().refresh();
        assertEquals("ReaderFeeder", webDriver.getTitle());
    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}

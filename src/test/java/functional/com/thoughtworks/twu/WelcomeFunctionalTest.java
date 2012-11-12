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
        performSuccessfulLogin();
        enterUserName();
    }

    @Test
    public void shouldShowUserNameAfterLoginSuccessfully() {
        assertEquals("test", webDriver.findElement(By.className("username")).getText());
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

    private void performSuccessfulLogin() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }

    private void enterUserName() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.name("username")).sendKeys("test");
        webDriver.findElement(By.name("submit")).click();
    }
}

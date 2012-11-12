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
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class CreateProfileFunctionalTest {

    private HtmlUnitDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
    }
    @Test
    public void shouldGoToProfile(){
        webDriver.get("http://localhost:8080/twu/identify-user");
        assertTrue(webDriver.findElement(By.className("profile")).isDisplayed());
    }

    @Test
    public void ProfileShouldHaveDetailsToBeFilled(){
        webDriver.get("http://localhost:8080/twu/identify-user");
        assertTrue(webDriver.findElement(By.className("user-form")).isDisplayed());

    }

    @Test
    public void shouldDisplayLogo(){
        webDriver.get("http://localhost:8080/twu/identify-user");
        assertTrue(webDriver.findElement(By.id("logo")).isDisplayed());
    }

    @Test
    public void shouldDisplayUserNameTextBox(){
        webDriver.get("http://localhost:8080/twu/identify-user");
        assertTrue(webDriver.findElement(By.name("username")).isDisplayed());
    }

    @Test
    public void shouldDisplaySubmitButton(){
        webDriver.get("http://localhost:8080/twu/identify-user");
        assertTrue(webDriver.findElement(By.className("submit-btn")).isDisplayed());
    }

   @Test
    public void shouldStayOnSamePageOnRefresh(){
       webDriver.get("http://localhost:8080/twu/identify-user");
       webDriver.navigate().refresh();
       assertEquals("ReaderFeeder Create Profile", webDriver.getTitle());
    }

    @Test
    public void shouldStayOnSamePageIfUsernameIsBlank() {
        webDriver.get("http://localhost:8080/twu/identify-user");
        webDriver.findElement(By.className("submit-btn")).click();

        assertEquals("ReaderFeeder Create Profile", webDriver.getTitle());
    }

    @Test
    public void shouldRedirectToWelcomePageWhenSubmitUsername(){
        webDriver.get("http://localhost:8080/twu/identify-user");
        enterUserName();
        assertTrue(webDriver.findElement(By.className("username")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("welcome")).isDisplayed());
    }

   @Test
    public void shouldStayOnSamePageOnRefresh1(){
       webDriver.get("http://localhost:8080/twu/identify-user");
        webDriver.navigate().refresh();
        assertEquals("ReaderFeeder Create Profile", webDriver.getTitle());
    }

    @After
    public void tearDown(){
        webDriver.close();
    }
    private void enterUserName() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.name("username")).sendKeys("barbie");
        webDriver.findElement(By.name("submit")).click();
    }

}

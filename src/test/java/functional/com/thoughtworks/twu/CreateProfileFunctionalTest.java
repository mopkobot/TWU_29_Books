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
        assertTrue(webDriver.findElement(By.className("profile")).isDisplayed());
    }

    @Test
    public void shouldDisplayLogo(){
        assertTrue(webDriver.findElement(By.id("logo")).isDisplayed());
    }

    @Test
    public void shouldDisplayUserNameTextBox(){
        assertTrue(webDriver.findElement(By.name("username")).isDisplayed());
    }

    @Test
    public void shouldDisplaySaveButton(){
        assertTrue(webDriver.findElement(By.className("submit-btn")).isDisplayed());
    }

   @Test
    public void shouldStayOnSamePageOnRefresh(){
        webDriver.navigate().refresh();
        assertEquals("ReaderFeeder Create Profile", webDriver.getTitle());
    }

    @Test
    public void shouldNotAllowBlankNameField(){
        CommonSteps.saveProfileInformation(webDriver,"");
        assertEquals("ReaderFeeder Create Profile",webDriver.getTitle());
    }

    @Test
    public void shouldNotAcceptOnlySpacesInNameField() {
        CommonSteps.saveProfileInformation(webDriver,"     ");
        assertEquals("ReaderFeeder Create Profile",webDriver.getTitle());
    }

    @Test
    public void shouldRedirectToWelcomePageWhenSubmitUsername(){
        CommonSteps.saveProfileInformation(webDriver,"Reader Feeder User");
        assertTrue(webDriver.findElement(By.className("username")).isDisplayed());
        assertTrue(webDriver.findElement(By.className("welcome")).isDisplayed());
    }

   @Test
    public void shouldStayOnSamePageOnRefreshAfterCreatingProfile(){
        CommonSteps.saveProfileInformation(webDriver,"Reader Feeder User");
        webDriver.navigate().refresh();
        assertEquals("ReaderFeeder", webDriver.getTitle());
    }
    @After
    public void tearDown(){
        webDriver.close();
    }


}

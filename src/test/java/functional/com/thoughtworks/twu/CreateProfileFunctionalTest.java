package functional.com.thoughtworks.twu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.testng.AssertJUnit.assertTrue;

public class CreateProfileFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        login();
    }

    @Test
    public void shouldDisplayLogo(){
        webDriver.get("http://localhost:8080/twu/redirect");
        assertTrue(webDriver.findElement(By.id("logo")).isDisplayed());
    }

    @Test
    public void shouldDisplayUserNameTextBox(){
        webDriver.get("http://localhost:8080/twu/redirect");
        assertTrue(webDriver.findElement(By.name("username")).isDisplayed());
    }

    @Test
    public void shouldDisplaySubmitButton(){
        webDriver.get("http://localhost:8080/twu/redirect");
        assertTrue(webDriver.findElement(By.name("submit")).isDisplayed());
    }

    @Test
    public void shouldRedirectToWelcomePageWhenSubmitUsername(){
        webDriver.get("http://localhost:8080/twu/redirect");
        enterUserName();
        assertTrue(webDriver.findElement(By.className("welcome")).isDisplayed());
    }

    @After
    public void tearDown(){
        webDriver.close();
    }

    private void login() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }
    private void enterUserName() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.name("username")).sendKeys("barbie");
        webDriver.findElement(By.name("submit")).click();
    }

}

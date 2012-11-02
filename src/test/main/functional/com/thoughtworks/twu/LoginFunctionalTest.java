package main.functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LoginFunctionalTest {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }

    @Test
    public void shouldShowMeCASLoginPage() {
        webDriver.get("http://localhost:8080/twu");
        WebElement header = webDriver.findElement(By.id("cas"));

        assertThat(header, notNullValue());
        assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

    @Test
    public void shouldValidateUserAndPassword() {
        webDriver.get("http://localhost:8080/twu");

        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();

        assertEquals(webDriver.getTitle(), "ReaderFeeder Create Profile");
    }

    @Test
    public void shouldDenyIllegalUserAccess() {
        webDriver.get("http://localhost:8080/twu");

        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("000000@12");
        webDriver.findElement(By.className("btn-submit")).click();

        assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

//    @Test
//    public void shouldShowUserNameAfterLoginSuccessfully() {
//        webDriver.get("http://localhost:8080/twu");
//
//        webDriver.findElement(By.id("username")).sendKeys("test.twu");
//        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
//        webDriver.findElement(By.className("btn-submit")).click();
//
//        assertEquals(webDriver.findElement(By.className("username")).getText(), "test.twu");
//    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}

package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertEquals;

public class CreateUserFunctionalTest {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        performSuccessfulLogin();
    }

    @Test
    public void shouldRedirectToCreateUserPage() {
        assertEquals(webDriver.getTitle(), "ReaderFeeder Create Profile");
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

    public static class ViewBookFunctionalTest{
        private WebDriver webDriver;
        @Before
        public void setUp() {
            webDriver = new FirefoxDriver();
        }
        @Test
        public void shouldValidateAllElementsPresent(){
            login();
            webDriver.get("http://localhost:8080/twu/viewbook?booktitle=The Casual Vacancy");
            Assert.assertEquals(webDriver.findElement(By.className("book-cover")).isDisplayed(), true);
            Assert.assertEquals(webDriver.findElement(By.className("title")).isDisplayed(), true);
            Assert.assertEquals(webDriver.findElement(By.className("author")).isDisplayed(), true);
            Assert.assertEquals(webDriver.findElement(By.className("isbn")).isDisplayed(), true);
            Assert.assertEquals(webDriver.findElement(By.className("description")).isDisplayed(), true);
        }

        private void login() {
            webDriver.get("http://localhost:8080/twu");
            webDriver.findElement(By.id("username")).sendKeys("test.twu");
            webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
            webDriver.findElement(By.className("btn-submit")).click();
        }

        @Test
        public void shouldDisplayBookNotFoundWhenBookIsNotPresent(){
            login();

            webDriver.get("http://localhost:8080/twu/viewbook?booktitle=alkdhaksdh");
            Assert.assertEquals("Could not find book", webDriver.findElement(By.className("title")).getText());
        }

        @Test
        public void shouldShowTheSameBook() {
            login();
            webDriver.get("http://localhost:8080/twu/viewbook?booktitle=alkdhaksdh");
            String title1= webDriver.findElement(By.className("title")).getText();
            webDriver.get("http://localhost:8080/twu/viewbook?booktitle=alkdhaksdh");
            String title2 =webDriver.findElement(By.className("title")).getText();
            Assert.assertEquals(title1, title2);
        }
    }
}

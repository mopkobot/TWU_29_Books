package functional.com.thoughtworks.twu;
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

public class ViewBookFunctionalTest{
    private WebDriver webDriver;
    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }
    @Test
    public void shouldValidateAllElementsPresent(){
        login();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Casual Vacancy");
        assertEquals(webDriver.findElement(By.className("book-cover")).isDisplayed(), true);
        assertEquals(webDriver.findElement(By.className("title")).isDisplayed(), true);
        assertEquals(webDriver.findElement(By.className("author")).isDisplayed(), true);
        assertEquals(webDriver.findElement(By.className("isbn")).isDisplayed(), true);
        assertEquals(webDriver.findElement(By.className("description")).isDisplayed(), true);
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

        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=alkdhaksdh");
        assertEquals("Could not find book",webDriver.findElement(By.className("title")).getText());
    }

    @Test
    public void shouldShowTheSameBook() {
        login();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Casual Vacancy");
        String title1= webDriver.findElement(By.className("title")).getText();
        webDriver.get("127.0.0.1:8080/twu/viewbook?booktitle=The Casual Vacancy");
        String title2 =webDriver.findElement(By.className("title")).getText();
        assertEquals(title1,title2);
    }
}
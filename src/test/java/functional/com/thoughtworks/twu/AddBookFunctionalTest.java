package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

public class AddBookFunctionalTest {
    WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
        enterUserName();
        searchBook();
    }

    @Test
    public void shouldRedirectToViewBookPageByClick() {
        webDriver.findElement(By.className("add-book")).click();

        assertTrue(webDriver.findElement(By.tagName("title")).getText().contains("ReaderFeeder"));
    }

    @After
    public void tearDown(){
        webDriver.close();
    }

    private void login() {
        webDriver.get("http://127.0.0.1:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }

    private void enterUserName() {
        webDriver.get("http://127.0.0.1:8080/twu");
        webDriver.findElement(By.name("username")).sendKeys("barbie");
        webDriver.findElement(By.name("submit")).click();
    }

    private void searchBook() {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        webDriver.findElement(By.name("searchValue")).sendKeys("9780316228534");
        webDriver.findElement(By.id("searchByISBN")).click();
        webDriver.findElement(By.id("search")).submit();
    }
}

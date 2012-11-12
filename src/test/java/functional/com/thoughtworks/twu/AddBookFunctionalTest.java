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
        CommonSteps.saveProfileInformation(webDriver);
        CommonSteps.searchBook(webDriver,"9780316228534","searchByISBN");
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
}

package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
    @Ignore
    public void shouldRedirectToViewBookPageByClick() {
        webDriver.findElement(By.className("view-book-btn")).click();

        assertTrue(webDriver.findElement(By.tagName("title")).getText().contains("ReaderFeeder"));
    }

    @Test
    public void shouldDisplayViewButtonOnSearchResultPage() {
        assertThat(webDriver.findElement(By.className("view-book-btn")).isDisplayed(), is(true));
    }

    @After
    public void tearDown(){
        webDriver.close();
    }

}

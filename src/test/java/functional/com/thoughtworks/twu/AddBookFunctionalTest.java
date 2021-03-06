package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class AddBookFunctionalTest {
    WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
        CommonSteps.saveProfileInformation(webDriver,"Reader Feeder User");
        CommonSteps.searchBook(webDriver,"9780375724404","searchByISBN");
    }

    @Test
    public void shouldAddBookToDBWhenViewButtonIsClicked(){
        webDriver.findElement(By.className("view-book-btn")).click();

        String actualTitle = webDriver.findElement(By.className("title")).getText();
        String actualAuthor = webDriver.findElement(By.className("author")).getText();

        assertThat(actualTitle, is("When We Were Orphans"));
        assertThat(actualAuthor, is("by Kazuo Ishiguro"));
    }

    @Test
    public  void shouldNotAddTheSameBookTwice(){

        webDriver.findElement(By.className("view-book-btn")).click();
        String firstURL =  webDriver.getCurrentUrl();
        CommonSteps.searchBook(webDriver,"9780375724404","searchByISBN");
        webDriver.findElement(By.className("view-book-btn")).click();
        String secondURL = webDriver.getCurrentUrl();
        assertEquals(firstURL,secondURL);

    }
    @Test
    public void shouldRedirectToViewBookPageByClick() {
        webDriver.findElement(By.className("view-book-btn")).click();
        assertTrue(webDriver.getCurrentUrl().contains("viewbook"));
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

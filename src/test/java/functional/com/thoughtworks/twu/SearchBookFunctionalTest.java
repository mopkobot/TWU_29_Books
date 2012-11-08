package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchBookFunctionalTest {
    WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        login();
    }

    @Test
    public void shouldDisplaySearchBox() {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        assertThat(webDriver.findElement(By.name("searchValue")).isDisplayed(), is(true));
    }

    @Test
    public void shouldDisplayDropBox(){
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        assertThat(webDriver.findElement(By.name("searchType")).isDisplayed(), is(true));
    }

    @Test
    public void shouldDisplaySearchButton() {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        assertThat(webDriver.findElement(By.id("search")).isDisplayed(), is(true));
    }

    @Test
    public void shouldDisplayBookDivInfoWhenGoButtonIsClicked() {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        webDriver.findElement(By.name("searchValue")).sendKeys("9780316228534");
        webDriver.findElement(By.id("searchByISBN")).click();
        webDriver.findElement(By.id("search")).submit();
        assertThat(webDriver.findElement(By.className("book-list")).isDisplayed
                (), is(true));
    }

    @Test
    public void shouldNotDisplayByWhenBookDoesntHaveAuthor()
    {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        webDriver.findElement(By.name("searchValue")).sendKeys("Boys' Life");
        webDriver.findElement(By.id("searchByTitle")).click();
        webDriver.findElement(By.id("search")).submit();
        assertThat(webDriver.findElement(By.className("book-author"))
                .getText(), is(""));
    }

    @Test
    public void shouldDisplayBookAuthorAndTitleWhenGoButtonIsClicked() {
        webDriver.get("http://127.0.0.1:8080/twu/search_book");
        webDriver.findElement(By.name("searchValue")).sendKeys("9780316228534");
        webDriver.findElement(By.id("searchByISBN")).click();
        webDriver.findElement(By.id("search")).submit();
        assertThat(webDriver.findElement(By.className("book-picture"))
                .isDisplayed
                (), is(true));
        assertThat(webDriver.findElement(By.className("book-title")).isDisplayed(), is(true));
        assertThat(webDriver.findElement(By.className("book-author")).isDisplayed(), is(true));
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    private void login() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }


}

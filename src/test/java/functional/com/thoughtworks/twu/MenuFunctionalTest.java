package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class MenuFunctionalTest {
    private HtmlUnitDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver, "test.twu", "Th0ughtW0rks@12");
        CommonSteps.saveProfileInformation(webDriver, "Reader Feeder User");
    }

    @Test
    public void shouldAlwaysDisplayLogo() {
        checkLogoFunctionality();

        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook");

        checkLogoFunctionality();
    }

    @Test
    public void shouldAlwaysDisplayUsername() {
        assertEquals("Hello, Reader Feeder User!", webDriver.findElement(By.className("navbar-username")).getText());

        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook");

        assertEquals("Hello, Reader Feeder User!", webDriver.findElement(By.className("navbar-username")).getText());
    }

    @Test
    public void shouldAlwaysDisplaySearchForBook() {
        checkSearchFunctionality();
        WebElement searchTextElement = CommonSteps.locateElementByCss(webDriver, ".navbar-search .search-query");
        WebElement searchDropDownElement = CommonSteps.locateElementByCss(webDriver, ".navbar.dropdown-menu");
        WebElement searchButtonElement = webDriver.findElement(By.id("search"));
        assertThat(searchTextElement.getAttribute("type"), is("text"));
        assertTrue(searchDropDownElement.isDisplayed());
        assertThat(searchButtonElement.getAttribute("value"), is("Search"));
    }


    @Test
    public void menuShouldBeVisibleOnViewBookPage() {
        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook");
        WebElement searchTextElementForNextPage = CommonSteps.locateElementByCss(webDriver, ".navbar-search .search-query");
        WebElement searchDropDownElementForNextPage = CommonSteps.locateElementByCss(webDriver, ".navbar.dropdown-menu");
        WebElement searchButtonElementForNextPage = webDriver.findElement(By.id("search"));
        assertThat(searchTextElementForNextPage.getAttribute("type"), is("text"));
        assertTrue(searchDropDownElementForNextPage.isDisplayed());
        assertThat(searchButtonElementForNextPage.getAttribute("value"), is("Search"));
    }

    @Test
    public void shouldGoToMyBookshelfWhenClickedOnLogo() {
        WebElement imageElement = CommonSteps.locateElementByCss(webDriver, ".logo img");
        assertThat(imageElement.getAttribute("src"), endsWith("/static/images/logo.gif"));
        webDriver.findElement(By.className("logo")).click();
        assertTrue(webDriver.findElement(By.className("bookshelf")).isDisplayed());
    }

    @Test
    public void shouldGotToMyBookshelfWhenClickedOnUsername() {
        webDriver.findElement(By.className("navbar-username")).click();
        assertTrue(webDriver.findElement(By.className("bookshelf")).isDisplayed());
    }



    @After
    public void tearDown() {
        webDriver.close();
    }

    private void checkSearchFunctionality() {
        WebElement searchTextElement = CommonSteps.locateElementByCss(webDriver, ".navbar-search .search-query");
        WebElement searchDropDownElement = CommonSteps.locateElementByCss(webDriver, ".navbar.dropdown-menu");
        WebElement searchButtonElement = webDriver.findElement(By.id("search"));
        assertThat(searchTextElement.getAttribute("type"), is("text"));
        assertTrue(searchDropDownElement.isDisplayed());
        assertThat(searchButtonElement.getAttribute("value"), is("Search"));
    }

    private void checkLogoFunctionality() {
        WebElement imageElement = CommonSteps.locateElementByCss(webDriver, ".logo img");
        assertThat(imageElement.getAttribute("src"), endsWith("/static/images/logo.gif"));
    }
}

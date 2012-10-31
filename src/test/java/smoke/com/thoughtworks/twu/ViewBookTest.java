package smoke.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ViewBookTest {

    WebDriver driver;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void shouldUnderstandBookView() throws Exception {
        String expectedBookViewUrl = "http://localhost:8080/twu/viewbook";
        String expectedBookTitle = "The Casual Vacancy";

        assertOnPageUrl(expectedBookViewUrl);
        assertOnBookPageLink(expectedBookViewUrl, expectedBookTitle);

        goToBookLink();

        assertOnBookTitle(expectedBookTitle);
        assertOnBookCover();
        assertOnBookDescription();

    }

    private void assertOnPageUrl(String expectedBookPageUrl) {
        driver.get(expectedBookPageUrl);
        assertThat(driver.getCurrentUrl(), is(expectedBookPageUrl));
    }

    private void assertOnBookPageLink(String bookViewUrl, String bookTitle) throws Exception {
        String parsedBookTitle = bookTitle.replaceAll(" ","%20");
        String bookPageUrl = bookViewUrl + "?booktitle=" + parsedBookTitle;

        WebElement bookLinkElement = locateElement("a");

        assertEquals(bookTitle, bookLinkElement.getText());
        assertEquals(bookPageUrl, bookLinkElement.getAttribute("href"));
    }

    private void assertOnBookTitle(String bookTitle) {
        WebElement titleElement = locateElement("h1.title");
        assertEquals(bookTitle, titleElement.getText());
    }

    private void assertOnBookCover() {
        WebElement imageElement= locateElement("img.book-img");
        assertThat(imageElement.getAttribute("src").endsWith("jpg"), is(true));
    }

    private void assertOnBookDescription() {
        WebElement descriptionElement = locateElement("div.description");
        assertThat(descriptionElement.getText().isEmpty(), is(false));
    }

    private void goToBookLink() {
        locateElement("a").click();
    }

    private WebElement locateElement(String selector){
        return driver.findElement(By.cssSelector(selector));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

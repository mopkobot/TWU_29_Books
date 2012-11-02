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

        login();
        String bookPageUrl = "http://localhost:8080/twu/viewbook?booktitle=The Casual Vacancy";

        driver.get(bookPageUrl);

        assertOnBookTitle();
        assertOnBookCover();
        assertOnBookDescription();
    }

    private void assertOnBookTitle() {
        WebElement titleElement = locateElement("h1.title");
        assertEquals("The Casual Vacancy", titleElement.getText());
    }

    private void assertOnBookCover() {
        WebElement imageElement= locateElement("img.book-img");
        assertThat(imageElement.getAttribute("src").isEmpty(), is(true));
    }

    private void assertOnBookDescription() {
        WebElement descriptionElement = locateElement("div.description");
        assertThat(descriptionElement.getText().isEmpty(), is(false));
    }

    private WebElement locateElement(String selector){
        return driver.findElement(By.cssSelector(selector));
    }

    private void login() {
        driver.get("http://localhost:8080/twu");
        driver.findElement(By.id("username")).sendKeys("test.twu");
        driver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        driver.findElement(By.className("btn-submit")).click();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

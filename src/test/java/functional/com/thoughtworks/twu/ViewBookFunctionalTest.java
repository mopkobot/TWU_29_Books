package functional.com.thoughtworks.twu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class ViewBookFunctionalTest{
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        login();
    }


    @Test
    public void shouldUnderstandViewBookPageWhenAllInformationIsPresent(){
        goToURL("127.0.0.1:8080/twu/viewbook?booktitle=Lavanya and sanchari QAs");

        assertOnBookTitle("Lavanya and sanchari QAs");
        assertOnBookCover("http://ecx.images-amazon.com/images/I/51HVlrefdkL._SL500_AA300_.jpg");
        assertOnAuthor("J.K. Rowling");
        assertOnIsbn("1234567890");
        assertOnBookDescription("The books chronicle the adventures of a wizard");
    }

    @Test
    public void shouldUnderstandViewBookPageWhenNotAllInformationIsPresent(){
        goToURL("127.0.0.1:8080/twu/viewbook?booktitle=When devs are not coding");

        assertOnBookTitle("When devs are not coding");
        assertOnBookCover("http://127.0.0.1:8080/twu/static/images/default_image.gif");
        assertOnAuthor("Benjamin Parzybok");
        assertOnIsbn("8947321890");
        assertOnBookDescription("No description available");
    }

    @Test
    public void shouldDisplayBookNotFoundWhenBookIsNotPresent(){
        goToURL("127.0.0.1:8080/twu/viewbook?booktitle=alkdhaksdh");
        assertOnBookTitle("Could not find book");
    }

    private void login() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
    }

    private void goToURL(String url) {
        webDriver.get(url);
    }

    private void assertOnBookTitle(String expectedTitle) {
        WebElement titleElement = locateElement("h1.title");
        assertThat(titleElement.getText(), is(expectedTitle));
    }

    private void assertOnBookCover(String expectedCover) {
        WebElement imageElement= locateElement("img.book-img");
        assertThat(imageElement.getAttribute("src"), is(expectedCover));
    }

    private void assertOnBookDescription(String expectedDescription) {
        WebElement descriptionElement = locateElement("section.description");
        assertThat(descriptionElement.getText(), containsString(expectedDescription));
    }

    private void assertOnIsbn(String expectedIsbn) {
        WebElement isbnElement = locateElement("div.isbn");
        assertThat(isbnElement.getText(), containsString(expectedIsbn));
    }

    private void assertOnAuthor(String expectedAuthor) {
        WebElement authorElement = locateElement("h2.author");
        assertThat(authorElement.getText(), is(expectedAuthor));
    }

    private WebElement locateElement(String selector){
        return webDriver.findElement(By.cssSelector(selector));
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
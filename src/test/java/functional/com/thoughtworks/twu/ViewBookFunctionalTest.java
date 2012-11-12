package functional.com.thoughtworks.twu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class ViewBookFunctionalTest{
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
    }


    @Test
    public void shouldUnderstandViewBookPageWhenAllInformationIsPresent(){
        goToURL("http://127.0.0.1:8080/twu/viewbook?booktitle=Lavanya and sanchari QAs");

        assertOnBookTitle("Lavanya and sanchari QAs");
        assertOnBookCover("http://ecx.images-amazon.com/images/I/51HVlrefdkL._SL500_AA300_.jpg");
        assertOnAuthor("J.K. Rowling");
        assertOnIsbn("1234567890");
        assertOnBookDescription("The books chronicle the adventures of a wizard");

        assertOnWantToReadButton();
    }

    @Test
    public void shouldUnderstandViewBookPageWhenNotAllInformationIsPresent(){
        goToURL("http://127.0.0.1:8080/twu/viewbook?booktitle=When devs are not coding");

        assertOnBookTitle("When devs are not coding");
        assertOnBookCover("http://127.0.0.1:8080/twu/static/images/default_image.gif");
        assertOnAuthor("Benjamin Parzybok");
        assertOnIsbn("8947321890");
        assertOnBookDescription("No description available");
    }

    @Test
    public void shouldDisplayBookNotFoundWhenBookIsNotPresent(){
        goToURL("http://127.0.0.1:8080/twu/viewbook?booktitle=alkdhaksdh");
        assertOnBookTitle("Could not find book");
    }

    private void goToURL(String url) {
        webDriver.get(url);
    }

    private void assertOnBookTitle(String expectedTitle) {
        WebElement titleElement = locateElementByCss("h1.title");
        assertThat(titleElement.getText(), is(expectedTitle));
    }

    private void assertOnBookCover(String expectedCover) {
        WebElement imageElement= locateElementByCss("img.book-img");
        assertThat(imageElement.getAttribute("src"), is(expectedCover));
    }

    private void assertOnBookDescription(String expectedDescription) {
        WebElement descriptionElement = locateElementByCss("section.description");
        assertThat(descriptionElement.getText(), containsString(expectedDescription));
    }

    private void assertOnIsbn(String expectedIsbn) {
        WebElement isbnElement = locateElementByCss("div.isbn");
        assertThat(isbnElement.getText(), containsString(expectedIsbn));
    }

    private void assertOnAuthor(String expectedAuthor) {
        WebElement authorElement = locateElementByCss("h2.author");
        assertThat(authorElement.getText(), containsString(expectedAuthor));
    }

    private void assertOnWantToReadButton() {
        WebElement wantToReadButton = locateElementByCss("button.btn");
        assertThat(wantToReadButton.isDisplayed(), is(true));
    }

    private WebElement locateElementById(String id) {
        return locateElement(By.id(id));
    }

    private WebElement locateElementByCss(String selector){
        return locateElement(By.cssSelector(selector));
    }

    private WebElement locateElement(By selector) {
        return webDriver.findElement(selector);
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
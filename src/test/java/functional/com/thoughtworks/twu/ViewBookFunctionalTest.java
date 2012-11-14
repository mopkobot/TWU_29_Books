package functional.com.thoughtworks.twu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class ViewBookFunctionalTest{
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver,"test.twu","Th0ughtW0rks@12");
    }


    @Test
    public void shouldUnderstandViewBookPageWhenAllInformationIsPresent(){
        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook?booktitle=Lavanya and sanchari QAs");

        assertOnBookTitle("Lavanya and sanchari QAs");
        assertOnBookCover("http://ecx.images-amazon.com/images/I/51HVlrefdkL._SL500_AA300_.jpg");
        assertOnAuthor("J.K. Rowling");
        assertOnIsbn("1234567890");
        assertOnBookDescription("The books chronicle the adventures of a wizard");

        assertOnWantToReadButton();
    }

    @Test
    public void shouldUnderstandViewBookPageWhenNotAllInformationIsPresent(){
        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook?booktitle=When devs are not coding");

        assertOnBookTitle("When devs are not coding");
        assertOnBookCover("http://127.0.0.1:8080/twu/static/images/default_image.gif");
        assertOnAuthor("Benjamin Parzybok");
        assertOnIsbn("8947321890");
        assertOnBookDescription("No description available");
    }

    @Test
    public void shouldDisplayTheRecommendButton(){
        goToURL("http://127.0.0.1:8080/twu/viewbook?booktitle=When devs are not coding");
        assertOnRecommendButton();

    }

    @Test
    public void shouldUnderstandViewBookPageWithNotificationWhenUserRecommendsABook(){
        goToURL("http://127.0.0.1:8080/twu/viewbook?booktitle=When devs are not coding");
        assertOnNotification();
    }

    @Test
    public void shouldDisplayBookNotFoundWhenBookIsNotPresent(){
        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook?booktitle=alkdhaksdh");
        assertOnBookTitle("Could not find book");
    }

    @Test
    public void shouldDisplayTheNumberOfRecommendations(){
        goToURL("http://127.0.0.1:8080/twu/viewbook?booktitle=When devs are not coding");
        assertOnRecommendationsText();
    }

    private void assertOnRecommendationsText() {
        WebElement recommendationTextElement = locateElementByCss("div.recommend-text");
        assertThat(recommendationTextElement.isDisplayed(), is(true));
    }

    private void assertOnNotification() {
        WebElement notificationElement = locateElementByCss("h3.notification");
        assertThat(notificationElement.isDisplayed(), is(true));
    }

    private void assertOnRecommendButton() {
        WebElement recommendButtonElement = locateElementByCss("input.recommend-btn");
        assertThat(recommendButtonElement.isDisplayed(), is(true));
    }

    private void goToURL(String url) {
        webDriver.get(url);
    }

    private void assertOnBookTitle(String expectedTitle) {
        WebElement titleElement = CommonSteps.locateElementByCss(webDriver, "h1.title");
        assertThat(titleElement.getText(), is(expectedTitle));
    }

    private void assertOnBookCover(String expectedCover) {
        WebElement imageElement= CommonSteps.locateElementByCss(webDriver, "img.book-img");
        assertThat(imageElement.getAttribute("src"), is(expectedCover));
    }

    private void assertOnBookDescription(String expectedDescription) {
        WebElement descriptionElement = CommonSteps.locateElementByCss(webDriver, "section.description");
        assertThat(descriptionElement.getText(), containsString(expectedDescription));
    }

    private void assertOnIsbn(String expectedIsbn) {
        WebElement isbnElement = CommonSteps.locateElementByCss(webDriver, "div.isbn");
        assertThat(isbnElement.getText(), containsString(expectedIsbn));
    }

    private void assertOnAuthor(String expectedAuthor) {
        WebElement authorElement = CommonSteps.locateElementByCss(webDriver, "h2.author");
        assertThat(authorElement.getText(), containsString(expectedAuthor));
    }

    private void assertOnWantToReadButton() {
        WebElement wantToReadButton = CommonSteps.locateElementByCss(webDriver, "button.add-btn");
        assertThat(wantToReadButton.isDisplayed(), is(true));
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
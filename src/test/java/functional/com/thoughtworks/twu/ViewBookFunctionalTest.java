package functional.com.thoughtworks.twu;


import com.thoughtworks.twu.service.BookService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static functional.com.thoughtworks.twu.CommonSteps.goToURL;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ViewBookFunctionalTest {
    private WebDriver webDriver;

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        CommonSteps.login(webDriver, "test.twu", "Th0ughtW0rks@12");
        CommonSteps.saveProfileInformation(webDriver, "Reader Feeder User");
    }


    @Test
    public void shouldUnderstandViewBookPageWhenAllInformationIsPresent() {
        goToURL("http://127.0.0.1:8080/twu/viewbook?bookId=1377");
        assertOnBookTitle("Lavanya and sanchari QAs");
        assertOnBookCover("http://ecx.images-amazon.com/images/I/51HVlrefdkL._SL500_AA300_.jpg");
        assertOnAuthor("J.K. Rowling");
        assertOnIsbn("1234567890");
        assertOnBookDescription("The books chronicle the adventures of a wizard");

        assertOnWantToReadButton();
    }

    @Test
    public void shouldUnderstandViewBookPageWhenNotAllInformationIsPresent() {
        goToURL("http://127.0.0.1:8080/twu/viewbook?bookId=1333");
        assertOnBookTitle("When devs are not coding");
        assertOnBookCover("http://127.0.0.1:8080/twu/static/images/default_image.gif");
        assertOnAuthor("Benjamin Parzybok");
        assertOnIsbn("8947321890");
        assertOnBookDescription("No description available");
    }

    @Test
    public void shouldDisplayTheRecommendButton() {
        goToURL("http://127.0.0.1:8080/twu/viewbook?bookId=1333");
        assertOnRecommendButton();

    }

    @Test
    public void shouldDisplayNotificationWhenUserRecommendsABook() {
        goToURL("http://127.0.0.1:8080/twu/viewbook?bookId=1333");
        assertOnNotification();
    }

    @Test
    public void shouldDisplayBookNotFoundWhenBookIsNotPresent() {
        CommonSteps.goToURL(webDriver, "http://127.0.0.1:8080/twu/viewbook?bookId=1000");
        assertOnBookTitle("Could not find book");
    }

    @Test
    public void shouldDisplayTheNumberOfRecommendations() {
        goToURL("http://127.0.0.1:8080/twu/viewbook?bookId=1333");
        assertOnRecommendationsText();
    }

    @Test
    public void shouldDisplayIncrementedRecommendCountWhenUserClicksRecommendButton() {
        goToURL("http://127.0.0.1:8080/twu/viewbook?bookId=1333");
        int recommendCountBeforeRecommend = getRecommendationCount();

        webDriver.findElement(By.className("recommend-btn")).click();

        int recommendCountAfterRecommend = getRecommendationCount();

        assertEquals(recommendCountBeforeRecommend + 1, recommendCountAfterRecommend);
    }

    private int getRecommendationCount() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("recommend-text")));

        String recommendCountMessage = webDriver.findElement(By.className("recommend-text")).getText();
        int positionOfR = recommendCountMessage.indexOf("R");
        String recommendCountStringBeforeRecommend = recommendCountMessage.substring(0, positionOfR).trim();
        return Integer.parseInt(recommendCountStringBeforeRecommend);
    }


    @Test
    public void shouldAddBookToMyWantToReadListWhenButtonIsClicked() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        CommonSteps.goToURL(webDriver,
                "http://127.0.0.1:8080/twu/viewbook?booktitle=The" +
                        " Couch");
        webDriver.findElement(By.className("add-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className
                ("add-btn")));
        assertThat(webDriver.findElement(By.className("add-btn")).isEnabled(), is(false));

    }

    private void assertOnRecommendationsText() {
        WebElement recommendationTextElement = locateElementByCss("div.recommend-text");
        assertThat(recommendationTextElement.isDisplayed(), is(true));
    }

    private void assertOnNotification() {
        webDriver.findElement(By.className("recommend-btn")).click();
        WebElement notificationElement = locateElementByCss("h3.alert");
        assertThat(notificationElement.isDisplayed(), is(true));
    }

    private void assertOnRecommendButton() {
        WebElement recommendButtonElement = locateElementByCss("input.recommend-btn");
        assertThat(recommendButtonElement.isDisplayed(), is(true));
    }

    private void assertOnBookTitle(String expectedTitle) {
        WebElement titleElement = CommonSteps.locateElementByCss(webDriver, "h1.title");
        assertThat(titleElement.getText(), is(expectedTitle));
    }

    private void assertOnBookCover(String expectedCover) {
        WebElement imageElement = CommonSteps.locateElementByCss(webDriver, "img.book-img");
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
        WebElement wantToReadButton = webDriver.findElement(By.className
                ("add-btn"));
        assertThat(wantToReadButton.isDisplayed(), is(true));
    }


    private WebElement locateElementByCss(String selector) {
        return locateElement(By.cssSelector(selector));
    }

    private void goToURL(String url){
        webDriver.get(url);
    }

    private WebElement locateElement(By selector) {
        return webDriver.findElement(selector);
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
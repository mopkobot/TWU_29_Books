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

public class HomeFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }

    @Test
    public void shouldShowMeTryMeLink() {
        webDriver.get("http://localhost:8080/twu/home");
        WebElement link = webDriver.findElement(By.tagName("a"));

        assertThat(link.getAttribute("href"), is("http://localhost:8080/twu/home?username=bill"));

    }

    @After
    public void tearDown() {
        webDriver.close();
    }


}

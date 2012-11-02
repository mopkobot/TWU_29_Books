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
    public void shouldShowMeHeaderOfThePage() {
        webDriver.get("http://localhost:8080/twu/home");
        WebElement header = webDriver.findElement(By.tagName("h1"));

        assertThat(header.getText(), is("SSO Internal/VPN access, please use Active Directory credentials."));
    }

    @After
    public void tearDown() {
        webDriver.close();
    }


}

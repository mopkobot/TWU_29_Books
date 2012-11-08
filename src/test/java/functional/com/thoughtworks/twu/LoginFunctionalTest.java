package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginFunctionalTest {
    private HtmlUnitDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
    }

    @Test
    public void shouldShowMeCASLoginPage() throws InterruptedException {
        webDriver.get("http://localhost:8080/twu");
        WebElement header = webDriver.findElement(By.id("cas"));

        assertThat(header, notNullValue());
        assertEquals("CAS \u2013 Central Authentication " + "Service",
                webDriver.getTitle());
    }

    @Test
    public void shouldValidateUserAndPasswordAndRedirectToReaderFeeder() {
        String password = "Th0ughtW0rks@12";
        performLogin("test.twu", password);

        assertTrue(webDriver.getTitle().contains("ReaderFeeder"));
    }

    @Test
    public void shouldDenyIllegalUserAccess() {
        performLogin("test.twu", "000000@12");

        assertEquals("CAS \u2013 Central Authentication " + "Service"
                , webDriver.getTitle());
    }

    @Test
    public void shouldDenyIllegalUserAccess2() {
        performLogin("test.twu", "");

        assertEquals("CAS \u2013 Central Authentication " + "Service",
                webDriver.getTitle());
    }

    @Test
    public void shouldDenyIllegalUserAccess3() {
        performLogin("", "Th0ughtW0rks@12");

        assertEquals("CAS \u2013 Central Authentication " + "Service",
                webDriver.getTitle());
    }

    @Test
    public void shouldDenyIllegalUserAccess4() {
        performLogin("000000123dg.twu", "Th0ughtW0rks@12");

        assertEquals("CAS \u2013 Central Authentication " + "Service",
                webDriver.getTitle());
    }

    @Test
    public void shouldNotRequireLoginIfAlreadyLoggedInCAS(){
        webDriver.get("https://castest.thoughtworks.com/cas/login");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
        webDriver.get("http://localhost:8080/twu");
        assertTrue(webDriver.getTitle().contains("ReaderFeeder"));
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    private void performLogin(String casname, String password) {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys(casname);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.className("btn-submit")).click();
    }


}

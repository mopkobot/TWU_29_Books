package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

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
        Assert.assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

    @Test
    public void shouldValidateUserAndPasswordAndRedirectToCreateProfilePage() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();

        Assert.assertEquals(webDriver.getTitle(), "ReaderFeeder Create Profile");
    }

    @Test
    public void shouldDenyIllegalUserAccess() {
        webDriver.get("http://localhost:8080/twu");

        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("000000@12");
        webDriver.findElement(By.className("btn-submit")).click();

        Assert.assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

    @Test
    public void shouldDenyIllegalUserAccess2() {
        webDriver.get("http://localhost:8080/twu");

        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.className("btn-submit")).click();

        Assert.assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

    @Test
    public void shouldDenyIllegalUserAccess3() {
        webDriver.get("http://localhost:8080/twu");

        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();

        Assert.assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

    @Test
    public void shouldDenyIllegalUserAccess4() {
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("000000123dg.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();

        Assert.assertEquals(webDriver.getTitle(), "CAS \u2013 Central Authentication " + "Service");
    }

    @After
    public void tearDown() {
        webDriver.close();
    }


}

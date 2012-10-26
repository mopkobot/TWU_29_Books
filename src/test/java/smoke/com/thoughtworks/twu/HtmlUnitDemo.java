package smoke.com.thoughtworks.twu;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


/**
 * Created with IntelliJ IDEA.
 * User: lavanya
 * Date: 10/25/12
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlUnitDemo {

    WebDriver driver;

    @Test
    public void openURL() {
        driver.get("https://mail.thoughtworks.com");
        driver.findElement(By.id("username")).sendKeys("lavanyam");
        driver.findElement(By.id("password")).sendKeys("lavanya");
        driver.findElement(By.className("btn-submit")).click();
        Assert.assertEquals(driver.getTitle(), "CAS – Central Authentication Service");

    }

    @Before
    public void setup(){
        driver = new HtmlUnitDriver();
    }
}

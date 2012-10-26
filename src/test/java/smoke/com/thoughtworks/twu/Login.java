package smoke.com.thoughtworks.twu;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 10/24/12
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Login {
    WebDriver driver;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
    }

    @Test
    public void openURL() {
        driver.get("https://mail.thoughtworks.com");
        driver.findElement(By.id("username")).sendKeys("lavanyam");
        driver.findElement(By.id("password")).sendKeys("lavanya");
        driver.findElement(By.className("btn-submit")).click();
        Assert.assertEquals(driver.getTitle(),"CAS – Central Authentication Service");

    }
}
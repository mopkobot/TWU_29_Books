package functional.com.thoughtworks.twu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class CommonSteps {
    public static void login(WebDriver webDriver, String username, String password) {
        webDriver.get("http://127.0.0.1:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.className("btn-submit")).click();
    }
}

package functional.com.thoughtworks.twu;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class WelcomeFunctionalTest{
    private HtmlUnitDriver webDriver;

    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() {
        webDriver = new HtmlUnitDriver();
        User user = new User("test.twu", "test");
        userMapper.insertUser(user);
    }

    @Ignore
    public void shouldShowUserNameAfterLoginSuccessfully() {
        webDriver.get("http://localhost:8080/twu");

        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();

        assertEquals(webDriver.findElement(By.className("username")).getText(), "test.twu");
    }

    @Ignore
    public void shouldShowUserNameIfAlreadyLoggedIntoCAS(){ //Single sign on
        webDriver.get("https://castest.thoughtworks.com/cas/login");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
        webDriver.get("http://localhost:8080/twu");
        assertEquals( "test.twu",webDriver.findElement(By.className("username")).getText());
    }

    @Ignore
    public void shouldStayOnSamePageOnRefresh(){
        webDriver.get("http://localhost:8080/twu");
        webDriver.findElement(By.id("username")).sendKeys("test.twu");
        webDriver.findElement(By.id("password")).sendKeys("Th0ughtW0rks@12");
        webDriver.findElement(By.className("btn-submit")).click();
        webDriver.navigate().refresh();
        assertEquals("ReaderFeeder", webDriver.getTitle());
    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}

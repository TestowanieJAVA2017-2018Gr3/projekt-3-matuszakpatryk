import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests
{
    private static WebDriver driver;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void SignInWithoutEmailTest() throws Exception {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("", "");
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }
}

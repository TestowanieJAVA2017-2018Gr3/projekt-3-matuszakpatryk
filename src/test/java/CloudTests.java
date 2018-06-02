import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CloudTests
{
    static WebDriver driver;
    public static final String USERNAME = "pmatuszak";
    public static final String ACCESS_KEY = "cdec0558-a151-455b-a88f-f06d5084dbf4";
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    @Test
    public void loginWithoutPasswordFirefox() throws Exception{
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        driver.findElement(By.name("Email")).sendKeys("useruser@wp.pl");
        driver.findElement(By.name("Password")).submit();
        WebElement passwordPrompt = driver.findElement(By.id("Password-error"));
        assertThat(passwordPrompt.getText(), containsString("field"));
        driver.quit();
    }

    @Test
    public void loginWithoutPasswordChrome() throws Exception{
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        driver.findElement(By.name("Email")).sendKeys("useruser@wp.pl");
        driver.findElement(By.name("Password")).submit();
        WebElement passwordPrompt = driver.findElement(By.id("Password-error"));
        assertThat(passwordPrompt.getText(), containsString("field"));
        driver.quit();
    }

    @Test
    public void loginWithoutEmailFirefox() throws Exception{
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        driver.findElement(By.name("Email")).sendKeys("useruser@wp.pl");
        driver.findElement(By.name("Password")).submit();
        WebElement emailrompt = driver.findElement(By.id("Email-error"));
        assertThat(emailrompt.getText(), containsString("field"));
        driver.quit();
    }

    @Test
    public void loginWithoutEmailChrome() throws Exception{
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        driver.findElement(By.name("Password")).sendKeys("useruser@wp.pl");
        driver.findElement(By.name("Password")).submit();
        WebElement emailrompt = driver.findElement(By.id("Email-error"));
        assertThat(emailrompt.getText(), containsString("field"));
        driver.quit();
    }
}

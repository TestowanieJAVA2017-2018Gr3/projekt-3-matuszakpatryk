import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sun.jmx.snmp.ThreadContext.contains;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SeleniumExtension.class)
public class HtmlUnitExplicityTests
{
    private WebDriver driver;
    private WebDriverWait wait;

    public HtmlUnitExplicityTests() {
        this.driver = new HtmlUnitDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @BeforeEach
    public void setDefaultPage() {
        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));
        driver.findElement(By.id("Email")).sendKeys("testtest@wp.pl");
        driver.findElement(By.id("Password")).sendKeys("Test123!");
        driver.findElement(By.id("Password")).submit();
    }

    @Test
    public void checkLoggedUser()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("UserMessage")));
        assertThat(driver.findElement(By.id("UserMessage")).getText(), containsString("testtest@wp.pl"));
    }

    @Test
    public void signOutUser()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("SignOutButton")));
        driver.findElement(By.id("SignOutButton")).click();
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("UserMessage")));
    }
}

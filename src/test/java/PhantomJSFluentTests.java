import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class PhantomJSFluentTests
{
    private PhantomJSDriver driver;
    private Wait wait;

    @FindBy(name = "Email")
    private WebElement email;

    @FindBy(name = "Password")
    private WebElement password;

    public PhantomJSFluentTests(PhantomJSDriver driver) {
        this.driver = driver;
        wait = new FluentWait(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @BeforeEach
    public void setDefaultPage() {
        driver.get("http://flowershopmfi.azurewebsites.net/account/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));
        driver.findElement(By.id("Email")).sendKeys("testtest@wp.pl");
        driver.findElement(By.id("Password")).sendKeys("Test123!");
        driver.findElement(By.id("Password")).submit();
        driver.get("http://flowershopmfi.azurewebsites.net/customer");
    }

    @Test
    public void testTitlePage()
    {
        wait.until(ExpectedConditions.titleIs("Customers - Flower"));
        assertEquals("Customers - Flower", driver.getTitle());
    }

    @Test
    public void searchWithEmptyString()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("SearchString")));
        int size = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        driver.findElement(By.name("SearchString")).sendKeys("");
        driver.findElement(By.id("SearchButton")).click();
        int sizeAfter = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(size, sizeAfter);
    }

    @Test
    public void searchWithString_TableShouldBeEmpty()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("SearchString")));
        driver.findElement(By.name("SearchString")).sendKeys("haskjdhkjahsdkjhakjsdhkjahsdkjhakjsdhkajhsdkjhaskjdha");
        driver.findElement(By.id("SearchButton")).click();
        int sizeAfter = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(0, sizeAfter);
    }

    @Test
    public void searchWithString_TableShouldHaveTwoRows()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("SearchString")));
        driver.findElement(By.name("SearchString")).sendKeys("gol");
        driver.findElement(By.id("SearchButton")).click();
        int sizeAfter = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(2, sizeAfter);
    }

    @Test
    public void searchWithStringByPhone_TableShouldHaveThreeRows()
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("SearchString")));
        driver.findElement(By.name("SearchString")).sendKeys("123");
        driver.findElement(By.id("SearchButton")).click();
        int sizeAfter = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(3, sizeAfter);
    }
}

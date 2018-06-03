import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class FirefoxTests
{
    private static FirefoxDriver driver;
    private Wait wait;

    public FirefoxTests(FirefoxDriver driver)
    {
        this.driver = driver;

        wait = new FluentWait(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @BeforeEach
    public void setDefaultPageAndAddCustomer() {
        driver.get("http://flowershopmfi.azurewebsites.net/account/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));
        driver.findElement(By.id("Email")).sendKeys("testtest@wp.pl");
        driver.findElement(By.id("Password")).sendKeys("Test123!");
        driver.findElement(By.id("Password")).submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("UserMessage")));
        driver.get("http://flowershopmfi.azurewebsites.net/customer");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CreateCustomer")));
        driver.findElement(By.id("CreateCustomer")).click();
        wait.until(ExpectedConditions.titleIs("Create - Flower"));
        driver.findElement(By.id("Name")).sendKeys("Dodany");
        driver.findElement(By.id("Surname")).sendKeys("Przed");
        driver.findElement(By.id("Phone")).sendKeys("123-111-111");
        driver.findElement(By.id("Email")).sendKeys("Chwila@wp.pl");
        driver.findElement(By.id("City")).sendKeys("Bo");
        driver.findElement(By.id("Country")).sendKeys("Tak");
        driver.findElement(By.id("Name")).submit();
        wait.until(ExpectedConditions.titleIs("Customers - Flower"));
    }

    @Test
    public void EditCustomerNameWithProperData() throws InterruptedException
    {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[last()]/a[1])"));
        driver.navigate().to(tr.getAttribute("href"));
        wait.until(ExpectedConditions.titleIs("Edit - Flower"));
        driver.findElement(By.id("Name")).clear();
        driver.findElement(By.id("Name")).sendKeys("Zmiana");
        driver.findElement(By.id("Name")).submit();
        wait.until(ExpectedConditions.titleIs("Customers - Flower"));
        WebElement name = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[1])"));
        assertEquals("Zmiana", name.getText());
    }

    @Test
    public void EditCustomerSurnameWithProperData() throws InterruptedException
    {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[last()]/a[1])"));
        driver.navigate().to(tr.getAttribute("href"));
        wait.until(ExpectedConditions.titleIs("Edit - Flower"));
        driver.findElement(By.id("Surname")).clear();
        driver.findElement(By.id("Surname")).sendKeys("Zmiana");
        driver.findElement(By.id("Surname")).submit();
        wait.until(ExpectedConditions.titleIs("Customers - Flower"));
        WebElement name = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[2])"));
        assertEquals("Zmiana", name.getText());
    }

    @Test
    public void EditCustomerPhoneWithProperData() throws InterruptedException
    {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[last()]/a[1])"));
        driver.navigate().to(tr.getAttribute("href"));
        wait.until(ExpectedConditions.titleIs("Edit - Flower"));
        driver.findElement(By.id("Phone")).clear();
        driver.findElement(By.id("Phone")).sendKeys("111-111-111");
        driver.findElement(By.id("Phone")).submit();
        wait.until(ExpectedConditions.titleIs("Customers - Flower"));
        WebElement name = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[3])"));
        assertEquals("111-111-111", name.getText());
    }

    @Test
    public void EditCustomerEmailWithProperData() throws InterruptedException
    {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[last()]/a[1])"));
        driver.navigate().to(tr.getAttribute("href"));
        wait.until(ExpectedConditions.titleIs("Edit - Flower"));
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("dobra@zmiana.pl");
        driver.findElement(By.id("Email")).submit();
        wait.until(ExpectedConditions.titleIs("Customers - Flower"));
        WebElement name = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[4])"));
        assertEquals("dobra@zmiana.pl", name.getText());
    }

    @AfterEach
    public void CleanUp()
    {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[last()]/a[last()])"));
        driver.navigate().to(tr.getAttribute("href"));
        driver.findElement(By.id("DeleteButton")).click();
        driver.quit();
    }
}

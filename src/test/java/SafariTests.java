import PageObjects.AddCustomerPageObject;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class SafariTests
{
    private static SafariDriver driver;
    private WebDriverWait wait;

    public SafariTests(SafariDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @BeforeEach
    public void setDefaultPage() {
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
    }

    @Test
    public void AddCustomerWithNotCapitalizedName()
    {
        wait.until(ExpectedConditions.titleIs("Create - Flower"));
        driver.findElement(By.id("Name")).sendKeys("zleimie");
        driver.findElement(By.id("Name")).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']/span")));
        assertEquals("Name must start with capital letter.", driver.findElement(By.xpath("//div[@class='form-group']/span")).getText());
        driver.quit();
    }

    @Test
    public void AddCustomerWithNotCapitalizedSurname()
    {
        wait.until(ExpectedConditions.titleIs("Create - Flower"));
        driver.findElement(By.id("Surname")).sendKeys("zleimie");
        driver.findElement(By.id("Surname")).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='form-group'])[2]/span")));
        assertEquals("Surname must start with capital letter.", driver.findElement(By.xpath("(//div[@class='form-group'])[2]/span")).getText());
        driver.quit();
    }


    @Test
    public void AddCustomerWithBadPhoneNumber()
    {
        wait.until(ExpectedConditions.titleIs("Create - Flower"));
        driver.findElement(By.id("Phone")).sendKeys("zlynumer");
        driver.findElement(By.id("Phone")).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='form-group'])[3]/span")));
        assertEquals("Invalid phone number format (use format: xxx-xxx-xxx).", driver.findElement(By.xpath("(//div[@class='form-group'])[3]/span")).getText());
        driver.quit();
    }

    @Test
    public void AddCustomerWithBadEmailNumber()
    {
        wait.until(ExpectedConditions.titleIs("Create - Flower"));
        driver.findElement(By.id("Email")).sendKeys("zlyemail");
        driver.findElement(By.id("Email")).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='form-group'])[4]/span")));
        assertEquals("Invalid email(use proper format).", driver.findElement(By.xpath("(//div[@class='form-group'])[4]/span")).getText());
        driver.quit();
    }
}

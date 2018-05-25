import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PurchaseAddPageObject
{
    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(name = "Email")
    private WebElement email;

    @FindBy(name = "Password")
    private WebElement password;

    @FindBy(name = "SellerID")
    private Select sellerSelect;

    @FindBy(name = "CustomerID")
    private Select customerSelect;

    @FindBy(name = "ProductID")
    private Select productSelect;

    @FindBy(id = "PurchaseDate-error")
    private WebElement datePrompt;

    public PurchaseAddPageObject(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://flowershopmfi.azurewebsites.net/account/login");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void SignInAsAdminAndNavigateToAddPage()
    {
        driver.get("http://flowershopmfi.azurewebsites.net/account/login");
        email.sendKeys("testtest@wp.pl");
        password.sendKeys("Test123!");
        password.submit();
        List<WebElement> menuList = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']/li/a"));
        System.out.println("Size: "+menuList.size());
        for (WebElement element: menuList)
        {
            String href = element.getAttribute("href");
            if(href.contains("purch"))
            {
                driver.navigate().to(element.getAttribute("href"));
            }
        }

    }

    public void AddPurchaseWithoutDate()
    {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container body-content]/a")));
        System.out.println(button.getAttribute("href"));
        button.click();
        sellerSelect.selectByValue("3");
        customerSelect.selectByValue("6");
        productSelect.selectByValue("2");
        driver.findElement(By.className("btn btn-success btn-sm")).submit();
    }

    public WebElement AddPurchaseWithoutDateAssert()
    {
        return datePrompt;
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(id = "Create")
    private WebElement createButton;

    @FindBy(id = "SellerID")
    private WebElement seller;

    @FindBy(id = "CustomerID")
    private WebElement customer;

    @FindBy(id = "ProductID")
    private WebElement product;

    @FindBy(id = "Submit")
    private WebElement Submit;

    @FindBy(id = "PurchaseDate-error")
    private WebElement datePrompt;

    public PurchaseAddPageObject(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void SignInAsAdminAndNavigateToAddPage()
    {
        driver.get("http://flowershopapp.azurewebsites.net/account/login");
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
        createButton.click();
        Select sellerSelect = new Select(seller);
        sellerSelect.selectByValue("3");
        Select customerSelect = new Select(customer);
        customerSelect.selectByValue("6");
        Select productSelect = new Select(product);
        productSelect.selectByValue("2");
        Submit.submit();
    }

    public WebElement AddPurchaseWithoutDateAssert()
    {
        return datePrompt;
    }
}

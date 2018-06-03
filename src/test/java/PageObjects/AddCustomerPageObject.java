package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddCustomerPageObject
{
    public WebDriver driver;

    @FindBy(name = "Email")
    private WebElement email;

    @FindBy(name = "Password")
    private WebElement password;

    public AddCustomerPageObject(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://flowershopapp.azurewebsites.net/account/login");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void SignIn(String Email, String Password)
    {
        email.sendKeys(Email);
        password.sendKeys(Password);
        password.submit();
    }

    public void GoToCustomerPageAndClickCreateButton()
    {
        driver.get("http://flowershopapp.azurewebsites.net/customer");
    }

}

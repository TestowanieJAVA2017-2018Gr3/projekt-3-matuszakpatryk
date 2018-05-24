import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPageObject
{
    public WebDriver driver;

    @FindBy(name = "Email")
    private WebElement email;

    @FindBy(name = "Password")
    private WebElement password;

    @FindBy(id = "WelcomeMessage")
    private WebElement welcomeMessage;

    private List<WebElement> liItems;

    public LoginPageObject(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://flowershopmfi.azurewebsites.net/account/login");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void SignIn(String Email, String Password)
    {
        email.sendKeys(Email);
        password.sendKeys(Password);
        password.submit();
    }

    public boolean LoginWithoutDataAssert()
    {
        liItems = driver.findElements(By.xpath("//div[@id='Test']/ul/li"));
        for (WebElement element: liItems)
        {
            if(!element.getText().contains("field is required"))
            {
                return false;
            }
        }
        return true;
    }
}

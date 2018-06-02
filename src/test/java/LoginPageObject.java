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

    @FindBy(id = "Password-error")
    private WebElement passwordPrompt;

    private List<WebElement> liItems;

    private WebElement invalidPassword;

    public LoginPageObject(WebDriver driver)
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

    public boolean SignInWithoutDataAssert()
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

    public String SignInWithoutPasswordAssert()
    {
        return passwordPrompt.getText();
    }

    public String SignInWithIncorrectPasswordAssert()
    {
        invalidPassword = driver.findElement(By.xpath("//div[@id='Test']/ul/li"));
        return invalidPassword.getText();
    }

    public String SignInWithCorrectDataAssert()
    {
        return welcomeMessage.getText();
    }


}

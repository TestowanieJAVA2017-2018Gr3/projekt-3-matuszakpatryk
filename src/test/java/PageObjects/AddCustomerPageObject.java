package PageObjects;

import org.openqa.selenium.By;
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

    @FindBy(id = "CreateCustomer")
    private WebElement CreateCustomer;

    @FindBy(id = "Name")
    private WebElement Name;

    @FindBy(id = "Surname")
    private WebElement Surname;

    @FindBy(id = "Phone")
    private WebElement Phone;

    @FindBy(id = "Email")
    private WebElement Email;

    @FindBy(id = "City")
    private WebElement City;

    @FindBy(id = "Country")
    private WebElement Country;

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
        CreateCustomer.click();
    }

    public void InsertData(String name, String surname, String phone, String email, String city, String country)
    {
        Name.sendKeys(name);
        Surname.sendKeys(surname);
        Phone.sendKeys(phone);
        Email.sendKeys(email);
        City.sendKeys(city);
        Country.sendKeys(country);
        Country.submit();
    }

    public String CreateCustomerWithoutNameAssert()
    {
        return driver.findElement(By.xpath("//div[@class='form-group']/span")).getText();
    }

    public String CreateCustomerWithoutSurnameAssert()
    {
        return driver.findElement(By.xpath("(//div[@class='form-group'])[2]/span")).getText();
    }

    public String CreateCustomerWithoutPhoneAssert()
    {
        return driver.findElement(By.xpath("(//div[@class='form-group'])[3]/span")).getText();
    }

    public String CreateCustomerWithoutEmailAssert()
    {
        return driver.findElement(By.xpath("(//div[@class='form-group'])[4]/span")).getText();
    }

    public String CreateCustomerWithoutCountryAssert()
    {
        return driver.findElement(By.xpath("(//div[@class='form-group'])[5]/span")).getText();
    }

    public String CreateCustomerWithoutCityAssert()
    {
        return driver.findElement(By.xpath("(//div[@class='form-group'])[6]/span")).getText();
    }
}

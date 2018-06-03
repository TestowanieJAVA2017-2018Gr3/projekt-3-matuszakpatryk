import PageObjects.LoginPageObject;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeSteps extends Steps
{
    private static WebDriver driver;
    private ExamplesTable table;
    private ArrayList<String> numbers;
    private int size, sizeAfter;

    @Given("Mam webdrivera")
    public void givenIHaveWebsite() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
    }

    @When("Zaloguje sie loginem <a> i haslem <b>")
    public void SignInWithoutDataTest(@Named("a") String a, @Named("b") String b) throws Exception {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn(a, b);
    }

    @Then("Otrzymam taki napis <wynik>")
    public void CheckLogin(@Named("wynik") String wynik)
    {
        assertTrue(driver.findElement(By.id("LoginMessage")).getText().contains(wynik));
        driver.quit();
    }

    @Given("Mam dane: $ranksTable")
    public void givenWebsite(ExamplesTable ranksTable){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        System.out.println("Test: " + ranksTable.getRow(0).get("login"));
        loginPage.SignIn(ranksTable.getRow(0).get("login"), ranksTable.getRow(0).get("pass"));
    }

    @Then("Napis jest: $table")
    public void thenStringContains(ExamplesTable table)
    {
        assertTrue(driver.findElement(By.id("LoginMessage")).getText().contains(table.getRow(0).get("message")));
        driver.quit();
    }

    @Given("Mam strone customera")
    public void goToCustomerPage() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("testtest@wp.pl", "Test123!");
        driver.get("http://flowershopmfi.azurewebsites.net/customer");
    }

    @When("Nic nie wpisze")
    public void insertEmptyString() throws Exception {
        size = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        driver.findElement(By.name("SearchString")).sendKeys("");
        driver.findElement(By.id("SearchButton")).click();
        sizeAfter = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(size, sizeAfter);
    }

    @Then("Tabela zostanie pełna")
    public void CheckLogin()
    {
        assertEquals(size, sizeAfter);
        driver.quit();
    }

    @Given("Mam driver")
    public void givenDriver() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
    }

    @When("Wejde na strone glowna")
    public void goToHomePage() throws Exception {
        driver.get("http://flowershopmfi.azurewebsites.net");
    }

    @Then("Tytul bedzie poprawny")
    public void checkTitle()
    {
        assertEquals("FlowerShop - Flower", driver.getTitle());
        driver.quit();
    }

    @Given("Mam chromedrivera")
    public void givenChromeDriver() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
    }

    @When("Zaloguje sie i przejde do seller")
    public void goToSellerPageAfterLogin() throws Exception {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("useruser@wp.pl", "User123!");
        driver.get("http://flowershopmfi.azurewebsites.net/seller");
    }

    @Then("Wyskoczy access denied")
    public void CheckIfSuccessful()
    {
        assertTrue(driver.findElement(By.xpath("//h2[@class='text-danger']")).getText().contains("denied"));
        driver.quit();
    }

    @Given("Mam chromedriveraa")
    public void givenChromeDrivera() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
    }

    @When("Zaloguje sie i przejde do customer")
    public void goToCustomerAfterLogin() throws Exception {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("useruser@wp.pl", "User123!");
        driver.get("http://flowershopmfi.azurewebsites.net/customer");
    }

    @Then("Nie bedzie edycji itp")
    public void CheckIsThereAnyEditOption()
    {
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("EditButton")));
        driver.quit();
    }


}

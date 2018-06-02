import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeSteps extends Steps
{
    private static WebDriver driver;

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


}

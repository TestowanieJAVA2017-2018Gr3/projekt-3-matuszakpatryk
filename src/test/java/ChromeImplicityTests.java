import PageObjects.LoginPageObject;
import PageObjects.PurchaseAddPageObject;
import io.github.bonigarcia.SeleniumExtension;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumExtension.class)
public class ChromeImplicityTests
{
    private static ChromeDriver driver;

    public ChromeImplicityTests(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    public void SignInWithoutDataTest() throws Exception {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("", "");
        assertTrue(loginPage.SignInWithoutDataAssert());
    }

    @Test
    public void SignInWithoutPasswordTest() throws Exception
    {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("testtest@wp.pl", "");
        assertThat(loginPage.SignInWithoutPasswordAssert(), notNullValue());
    }

    @Test
    public void SignInWithIncorrectPasswordTest() throws Exception
    {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("testtest@wp.pl", "ThisPasswordIsBad");
        assertThat(loginPage.SignInWithIncorrectPasswordAssert(), stringContains(loginPage.SignInWithIncorrectPasswordAssert()));
    }

    @Test
    public void SignInWithCorrectDataTest() throws Exception
    {
        LoginPageObject loginPage = PageFactory.initElements(driver, LoginPageObject.class);
        loginPage.SignIn("testtest@wp.pl", "Test123!");
        assertThat(loginPage.SignInWithCorrectDataAssert(), containsString("Hurra"));
    }

    @Test
    public void AddPurchaseWithoutDate()
    {
        PurchaseAddPageObject pageObject = PageFactory.initElements(driver, PurchaseAddPageObject.class);
        pageObject.SignInAsAdminAndNavigateToAddPage();
        pageObject.AddPurchaseWithoutDate();
        assertEquals(pageObject.AddPurchaseWithoutDateAssert().getText(), "Enter Date");
    }

    @Test
    public void AddPurchaseWithCorrectData()
    {
        PurchaseAddPageObject pageObject = PageFactory.initElements(driver, PurchaseAddPageObject.class);
        pageObject.SignInAsAdminAndNavigateToAddPage();
        pageObject.AddPurchaseWithDate();
        assertTrue(pageObject.AddPurchaseWithDateAssert().contains("11/11/2011"));
        pageObject.DeleteLastItem();
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception
    {
        driver.quit();
    }

    private Matcher<String> stringContains(final String value) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(final Object item) {
                return value.contains("Invalid");
            }
            @Override
            public void describeTo(final Description description) {
                description.appendText("Expected string should contain: ").appendValue("Invalid");
            }
        };
    }
}

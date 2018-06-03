import PageObjects.AddCustomerPageObject;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class OperaTests
{
    private static OperaDriver driver;

    public OperaTests(OperaDriver driver) {
        this.driver = driver;
    }

    @Test
    public void AddCustomerWithoutNameTest() throws Exception {
        AddCustomerPageObject page = PageFactory.initElements(driver, AddCustomerPageObject.class);
        page.SignIn("testtest@wp.pl", "Test123!");
        page.GoToCustomerPageAndClickCreateButton();
        page.InsertData("", "Dodany", "123-123-123", "dodajsie@prosze.com", "Moze", "Dodam");
        assertEquals("Enter Name", page.CreateCustomerWithoutNameAssert());
        driver.quit();
    }

    @Test
    public void AddCustomerWithoutSurnameTest() throws Exception {
        AddCustomerPageObject page = PageFactory.initElements(driver, AddCustomerPageObject.class);
        page.SignIn("testtest@wp.pl", "Test123!");
        page.GoToCustomerPageAndClickCreateButton();
        page.InsertData("Dodany", "", "123-123-123", "dodajsie@prosze.com", "Moze", "Dodam");
        assertEquals("Enter Surname", page.CreateCustomerWithoutSurnameAssert());
        driver.quit();
    }

    @Test
    public void AddCustomerWithoutPhoneTest() throws Exception {
        AddCustomerPageObject page = PageFactory.initElements(driver, AddCustomerPageObject.class);
        page.SignIn("testtest@wp.pl", "Test123!");
        page.GoToCustomerPageAndClickCreateButton();
        page.InsertData("Dodany", "Chyba", "", "dodajsie@prosze.com", "Moze", "Dodam");
        assertEquals("Enter Phone", page.CreateCustomerWithoutPhoneAssert());
        driver.quit();
    }

    @Test
    public void AddCustomerWithoutEmailTest() throws Exception {
        AddCustomerPageObject page = PageFactory.initElements(driver, AddCustomerPageObject.class);
        page.SignIn("testtest@wp.pl", "Test123!");
        page.GoToCustomerPageAndClickCreateButton();
        page.InsertData("Dodany", "Chyba", "123-123-123", "", "Moze", "Dodam");
        assertEquals("Enter Email", page.CreateCustomerWithoutEmailAssert());
        driver.quit();
    }

    @Test
    public void AddCustomerWithoutCityTest() throws Exception {
        AddCustomerPageObject page = PageFactory.initElements(driver, AddCustomerPageObject.class);
        page.SignIn("testtest@wp.pl", "Test123!");
        page.GoToCustomerPageAndClickCreateButton();
        page.InsertData("Dodany", "Chyba", "123-123-123", "dodajsie@prosze.com", "", "Dodam");
        assertEquals("Enter City Name", page.CreateCustomerWithoutCityAssert());
        driver.quit();
    }

    @Test
    public void AddCustomerWithoutCountryTest() throws Exception {
        AddCustomerPageObject page = PageFactory.initElements(driver, AddCustomerPageObject.class);
        page.SignIn("testtest@wp.pl", "Test123!");
        page.GoToCustomerPageAndClickCreateButton();
        page.InsertData("Dodany", "Chyba", "123-123-123", "dodajsie@prosze.com", "Moze", "");
        assertEquals("Enter Country Name", page.CreateCustomerWithoutCountryAssert());
        driver.quit();
    }
}

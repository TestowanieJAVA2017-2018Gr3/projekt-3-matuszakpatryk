import PageObjects.AddCustomerPageObject;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(page.SignInWithoutDataAssert());
    }
}

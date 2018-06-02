import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class PhantomJSFluentTests
{
    private PhantomJSDriver driver;

    public PhantomJSFluentTests(PhantomJSDriver driver) {
        this.driver = driver;
    }

    @BeforeEach
    public void setDefaultPage() {
        driver.get("https://duckduckgo.com/");
    }

    @Test
    public void testTitlePage() {
        assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
    }
}

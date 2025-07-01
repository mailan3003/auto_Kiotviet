package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import io.cucumber.java.After;
import pages.LoginPage;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("the user opens the login page")
    public void open_login_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @When("the user enters {string} and {string}")
    public void enter_credentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the user should see the dashboard")
    public void check_dashboard() {
        Assert.assertTrue("Dashboard is not visible!", loginPage.isDashboardVisible());
    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertNotNull("No error message was displayed!", actualMessage);
        System.out.println("Actual error: " + actualMessage);
        Assert.assertEquals("Unexpected error message!", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    WebDriver driver;

    @Given("the user opens the login page")
    public void open_login_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login"); // <- sửa thành trang thật
    }

    @When("the user enters {string} and {string}")
    public void enter_credentials(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click(); // hoặc tên button thật
    }

    @Then("the user should see the dashboard")
    public void verify_dashboard() {
        String expectedUrl = "https://the-internet.herokuapp.com/login";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
        driver.quit();
    }

    @Then("the user should see an error message")
    public void verify_error() {
        WebElement error = driver.findElement(By.id("errorMessage")); // sửa ID
        Assert.assertTrue(error.isDisplayed());
        driver.quit();
    }
}

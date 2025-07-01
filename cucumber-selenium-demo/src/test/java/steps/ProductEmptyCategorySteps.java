package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.ProductPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.Assert;



import java.time.Duration;

public class ProductEmptyCategorySteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;

    @Given("Given the user is logged in and opens the product page with empty category case")
    public void user_is_on_product_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("admin", "Kiotviet123456");

        productPage = new ProductPage(driver);
        productPage.navigateToDanhMucPage();
    }

    @When("the user adds a new product with name {string} and no category")
    public void user_adds_product_with_no_category(String name) {
        productPage.clickAddNewButton();
        productPage.fillProductInfo(name);
        // KHÔNG gọi selectCategory
        productPage.saveProduct();
    }

    @Then("the system should show an error message {string}")
    public void system_should_show_error_message(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tìm phần tử chứa thông báo lỗi
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.toast.toast-error div.toast-message")));

        String actualErrorMessage = errorElement.getText();
        System.out.println("Actual error message: " + actualErrorMessage);

        // Kiểm tra nội dung thông báo lỗi
        Assert.assertTrue("Expected error message not shown!",
                actualErrorMessage.contains(expectedMessage));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Đóng toàn bộ trình duyệt
        }
    }
}

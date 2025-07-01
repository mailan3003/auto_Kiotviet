package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.ProductPage;
import io.cucumber.java.After;
import java.time.Duration;

public class ProductSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;

    @Given("the user is logged in and on the product page")
    public void the_user_is_logged_in_and_on_product_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Đăng nhập
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("admin", "Kiotviet123456");

        // Điều hướng tới trang danh mục hàng hóa
        productPage = new ProductPage(driver);
        productPage.navigateToDanhMucPage();
    }

    @When("the user adds a new product with name {string} and category {string}")
    public void the_user_adds_a_new_product(String name, String category) {
        productPage.clickAddNewButton();
        productPage.fillProductInfo(name);
        productPage.selectCategory();
        productPage.saveProduct();
    }

    @Then("the product should be added successfully")
    public void product_should_be_added_successfully() {
        Assert.assertTrue("Product was not added successfully!", productPage.isProductAddedSuccessfully());
        driver.quit();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Đóng toàn bộ trình duyệt
        }
    }

}

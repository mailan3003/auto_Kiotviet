package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Mở trang đăng nhập
    public void open() {
        driver.get("https://testz23.kiotviet.com/man/#/login");
    }

    // Nhập tên đăng nhập
    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    // Nhập mật khẩu
    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // Click nút đăng nhập
    public void clickLoginButton() {
        driver.findElement(By.cssSelector("input[type='submit'][name='quan-ly']")).click();
    }

    // Đăng nhập đầy đủ
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // Kiểm tra dashboard hiển thị
    public boolean isDashboardVisible() {
        try {
            WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.dashboardLeft")));
            return dashboard.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Lấy thông báo lỗi
    public String getErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.ng-binding")));
            return error.getText().trim();
        } catch (TimeoutException | NoSuchElementException e) {
            return null;
        }
    }
}

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBanHangButton() {
        WebElement banHangLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/sale/' or contains(@href, '/sale')][span[contains(text(),'Bán hàng')]]")
        ));
        banHangLink.click();
    }

}

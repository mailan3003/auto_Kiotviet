package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Đóng overlay vodal nếu xuất hiện
    public void closeOverlayIfPresent() {
        try {
            List<WebElement> overlays = driver.findElements(By.cssSelector("div.vodal-mask"));
            if (!overlays.isEmpty() && overlays.get(0).isDisplayed()) {
                WebElement closeButton = driver.findElement(By.cssSelector("span.vodal-close"));
                closeButton.click();
                wait.until(ExpectedConditions.invisibilityOf(closeButton)); // chờ tắt hoàn toàn
            }
        } catch (Exception e) {
            System.out.println("Overlay không tồn tại hoặc không thể đóng: " + e.getMessage());
        }
    }

    // Điều hướng tới trang "Danh mục"
    public void navigateToDanhMucPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        closeOverlayIfPresent();

        WebElement hangHoaMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='ng-binding' and contains(text(),'Hàng hóa')]")));
        hangHoaMenu.click();

        WebElement danhMucSubMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='ng-binding' and contains(text(),'Danh mục')]")));
        danhMucSubMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Thêm mới')]")));
    }

    // Click "Thêm mới"
    public void clickAddNewButton() {
        closeOverlayIfPresent();

        // Bước 1: Click vào nút "Thêm mới"
        WebElement dropdownThemMoi = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'btn-success') and .//span[contains(text(),'Thêm mới')]]")));
        dropdownThemMoi.click();

        // Bước 2: Chờ và click "Thêm hàng hóa"
        WebElement themHangHoa = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@ng-click='AddProduct(pTypeValue.Purchased)' and contains(text(),'Thêm hàng hóa')]")));
        themHangHoa.click();

        // Bước 3: Đảm bảo form hiện ra
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idSuggestProductNameSearchTerm")));
    }

    // Trường nhập tên hàng mới
    private By productNameInput = By.id("idSuggestProductNameSearchTerm");

    // Nhập tên và mã hàng hóa (chỉ nhập tên hàng theo yêu cầu mới)
    public void fillProductInfo(String name) {
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameInput));
        nameInput.clear();
        nameInput.sendKeys(name);
        nameInput.sendKeys(Keys.ENTER); // Nhấn Enter luôn, không cần chờ gợi ý
    }

     //Chọn nhóm hàng từ danh sách
    public void selectCategory() {
        // Bước 1: Click dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class,'k-dropdown') and contains(@class,'product-select')]")
        ));
        dropdown.click();

        // Bước 2: Chờ danh sách xuất hiện
        By listLocator = By.xpath("//ul[contains(@id,'ddlCat') and contains(@id,'_listbox')]");
        WebElement listElement = wait.until(ExpectedConditions.presenceOfElementLocated(listLocator));

        // Bước 3: Chờ cho danh sách có ít nhất 2 lựa chọn
        wait.until(driver -> listElement.findElements(By.tagName("li")).size() > 1);

        // Bước 4: Lấy lựa chọn đầu tiên (bỏ qua '---Lựa chọn---')
        List<WebElement> items = listElement.findElements(By.tagName("li"));
        WebElement firstOption = items.get(1); // phần tử thứ 2, vì li[0] là placeholder

        // Bước 5: Scroll tới item và click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstOption);
        wait.until(ExpectedConditions.elementToBeClickable(firstOption)).click();
    }

    // Lưu sản phẩm
    public void saveProduct() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@ng-click='SaveProduct()' and contains(text(),'Lưu')]")));
        saveButton.click();
    }


    // Kiểm tra sản phẩm đã được thêm thành công
    public boolean isProductAddedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Sản phẩm đã được thêm mới thành công')]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Kiểm tra toast message lỗi
    public boolean isErrorMessageDisplayed(String expectedMessage) {
        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'toast-message') and contains(text(),'" + expectedMessage + "')]")));
            return toast.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }



}

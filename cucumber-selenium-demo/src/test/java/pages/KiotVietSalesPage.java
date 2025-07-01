package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.time.Duration;

public class KiotVietSalesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//input[@placeholder='Tìm hàng hóa (F3)']")
    private WebElement productSearchBox;

    @FindBy(css = "a.nav-link.active span")
    private WebElement activeInvoiceName;

    @FindBy(className = "product-dropdown")
    private WebElement productDropdown;

    @FindBy(xpath = "//div[contains(@class, 'invoice-item')]")
    private List<WebElement> invoiceItems;

    @FindBy(xpath = "//div[contains(@class, 'invoice-item')][last()]//span[@class='product-name']")
    private WebElement lastProductName;

    @FindBy(xpath = "//div[contains(@class, 'invoice-item')][last()]//span[@class='barcode']")
    private WebElement lastProductBarcode;

    @FindBy(xpath = "//div[contains(@class, 'invoice-item')][last()]//input[@class='quantity']")
    private WebElement lastProductQuantity;

    @FindBy(xpath = "//div[contains(@class, 'invoice-item')][last()]//span[@class='price']")
    private WebElement lastProductPrice;

    public KiotVietSalesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //Tắt overlay nếu có
    public void closeIntroOverlayIfPresent() {
        try {
            WebElement skipButton = driver.findElement(By.cssSelector("a.introjs-skipbutton"));
            if (skipButton.isDisplayed()) {
                skipButton.click();
                // Chờ overlay biến mất nếu cần (ví dụ: div.introjs-overlay)
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.introjs-overlay")));
            }
        } catch (Exception e) {
            System.out.println("Không có overlay introjs hoặc không thể đóng: " + e.getMessage());
        }
    }

    //Tab vào tab bán thường
    public void clickTabBanThuong() {
        try {
            // Chờ loading spinner biến mất nếu có
            By spinnerLocator = By.id("loading-bar-spinner");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));

            // Sau đó mới click vào tab "Bán thường"
            WebElement tabBanThuong = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='nav-link' and contains(., 'Bán thường')]"))
            );
            tabBanThuong.click();

            // Chờ màn "Bán thường" load xong (nếu có thể xác định)
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder='Tìm hàng hóa (F3)']"))
            );

        } catch (Exception e) {
            System.out.println("Không thể click tab Bán thường: " + e.getMessage());
        }
    }



    public boolean isSalesScreenDisplayed() {
        try {
            return productSearchBox.isDisplayed() && activeInvoiceName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getActiveInvoice() {
        return activeInvoiceName;
    }

    public boolean isInvoiceActive(String invoiceName) {
        try {
            String actualName = activeInvoiceName.getText().trim();
            System.out.println("Tên hóa đơn đang mở: " + actualName);
            return actualName.equalsIgnoreCase(invoiceName.trim());
        } catch (Exception e) {
            System.out.println("Không lấy được tên hóa đơn: " + e.getMessage());
            return false;
        }
    }


    public void clickProductSearchBox() {
        productSearchBox.click();
    }

    public WebElement getProductSearchBox() {
        return productSearchBox;
    }

    public void enterProductSearchTerm(String searchTerm) {
        productSearchBox.clear();
        productSearchBox.sendKeys(searchTerm);
    }

    public boolean hasProductDropdownItems() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productDropdown));
            List<WebElement> items = productDropdown.findElements(By.className("dropdown-item"));
            return !items.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectFirstProductFromDropdown() {
        WebElement firstItem = productDropdown.findElement(By.className("dropdown-item"));
        firstItem.click();
    }

    public void nhanEnterDeThemSanPham() {
        WebElement searchBox = driver.findElement(By.cssSelector("input[placeholder='Tìm hàng hóa (F3)']")); // chỉnh lại selector nếu cần
        searchBox.sendKeys(Keys.ENTER);
    }


    public boolean isProductDisplayed(String productName, String barcode, String price, int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1. Check product name
        String xpathName = String.format("//span[contains(@title, 'Tồn') and contains(text(), \"%s\")]", productName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathName)));

        // 2. Check barcode
        String xpathBarcode = String.format("//div[contains(@class, 'cell-code') and contains(text(), \"%s\")]", barcode);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathBarcode)));

        // 3. Check quantity
        String xpathQuantity = String.format("//input[contains(@class, 'note-cartitem') and @value='%d']", quantity);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathQuantity)));

        // 4. Check price
        String xpathPrice = String.format("//button[contains(@class, 'cart-item') and contains(text(), \"%s\")]", price);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathPrice)));

        return true;
    }


    public boolean isProductInInvoice(String productName) {
        return invoiceItems.stream()
                .anyMatch(item -> item.findElement(By.className("product-name"))
                        .getText().contains(productName));
    }

    public String getLastAddedProductName() {
        wait.until(ExpectedConditions.visibilityOf(lastProductName));
        return lastProductName.getText();
    }

    public String getLastAddedProductBarcode() {
        return lastProductBarcode.getText();
    }

    public boolean isBarcodeVisible() {
        return lastProductBarcode.isDisplayed();
    }

    public int getLastAddedProductQuantity() {
        String quantityText = lastProductQuantity.getAttribute("value");
        return Integer.parseInt(quantityText);
    }

    public int getLastAddedProductPrice() {
        String priceText = lastProductPrice.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }

    public String getLastAddedProductPriceText() {
        return lastProductPrice.getText();
    }
}
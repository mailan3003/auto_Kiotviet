package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.KiotVietSalesPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.DriverManager;

import java.time.Duration;

public class KiotVietSalesSteps {

    WebDriver driver = DriverManager.getDriver();
    KiotVietSalesPage salesPage = new KiotVietSalesPage(driver);
    LoginPage loginPage;
    ProductPage productPage;
    DashboardPage dashboardPage;

    @Given("nhân viên đang ở màn hình bán hàng KiotViet")
    public void nhanVienDangOManhinhBanHang() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Mở trang login và đăng nhập
        loginPage = new LoginPage(driver);
        loginPage.open(); // -> mở trang login (không cần gọi driver.get() thủ công nữa)
        loginPage.login("admin", "Kiotviet123456");

        // 2. Đóng overlay nếu có
        productPage = new ProductPage(driver);
        productPage.closeOverlayIfPresent();

        // 3. Click vào nút "Bán hàng" ở dashboard
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickBanHangButton();

        // 4. Đóng popup giới thiệu nếu có
        salesPage = new KiotVietSalesPage(driver);
        salesPage.closeIntroOverlayIfPresent();

        // 5. Chờ spinner loading biến mất
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar-spinner")));

        // 6. Chờ ô tìm kiếm hiển thị
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productSearchInput")));

        // 7. Click tab "Bán thường" nếu chưa được chọn
        salesPage.clickTabBanThuong();

        // 8. Kiểm tra URL + Kiểm tra ô tìm kiếm có placeholder đúng
        String currentUrl = driver.getCurrentUrl().trim();
        String expectedUrl = "https://testz23.kiotviet.com/sale/#/";

        WebElement searchInput = driver.findElement(By.id("productSearchInput"));
        String actualPlaceholder = searchInput.getAttribute("placeholder");

        boolean isSalesScreenDisplayed = currentUrl.equals(expectedUrl)
                && searchInput.isDisplayed()
                && "Tìm hàng hóa (F3)".equals(actualPlaceholder);

        Assert.assertTrue("❌ Không hiển thị màn hình bán hàng.\n" +
                        "👉 Đang ở URL: " + currentUrl +
                        "\n🔗 Mong đợi: " + expectedUrl +
                        "\n🔍 Placeholder: " + actualPlaceholder,
                isSalesScreenDisplayed);
    }


    @Given("có hóa đơn {string} đang mở")
    public void coHoaDonDangMo(String invoiceName) {
        Assert.assertTrue("Hóa đơn không đúng", salesPage.isInvoiceActive(invoiceName));
    }

    @When("nhân viên click vào ô {string}")
    public void nhanVienClickVaoO(String fieldName) {
        // Field name có thể dùng để xác thực thêm nếu có nhiều ô nhập
        salesPage.clickProductSearchBox();
    }

    @When("nhập {string}")
    public void nhapTuKhoaTimKiem(String keyword) {
        salesPage.enterProductSearchTerm(keyword);
    }

    @When("nhấn Enter hoặc chọn sản phẩm từ dropdown")
    public void chonSanPhamTuDropdown() {
        salesPage.nhanEnterDeThemSanPham();  // mới
    }


    @Then("sản phẩm {string} được thêm vào hóa đơn")
    public void sanPhamDuocThemVaoHoaDon(String tenSanPham) {
        boolean isDisplayed = salesPage.isProductDisplayed(
                tenSanPham,
                "8936131180934", // hoặc lấy động nếu có nhiều sản phẩm khác nhau
                "25,000",
                1
        );
        Assert.assertTrue("Không tìm thấy sản phẩm đã thêm vào hóa đơn", isDisplayed);
    }

    @Then("hiển thị mã vạch {string}")
    public void hienThiMaVach(String expectedBarcode) {
        Assert.assertTrue("Không hiển thị mã vạch", salesPage.isBarcodeVisible());
        String actualBarcode = salesPage.getLastAddedProductBarcode();
        Assert.assertEquals("Mã vạch không đúng", expectedBarcode, actualBarcode);
    }

    @Then("số lượng mặc định là {int}")
    public void soLuongMacDinh(int expectedQuantity) {
        int actualQuantity = salesPage.getLastAddedProductQuantity();
        Assert.assertEquals("Số lượng không đúng", expectedQuantity, actualQuantity);
    }

    @Then("giá bán hiển thị là {string}")
    public void giaBanHienThiLa(String expectedPriceText) {
        String actualPriceText = salesPage.getLastAddedProductPriceText();
        Assert.assertEquals("Giá bán không khớp", expectedPriceText, actualPriceText);
    }
}

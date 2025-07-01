package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

public class LoginSteps {
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.out.println("User is on login page");
    }

    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        System.out.println("User enters valid credentials");
    }

    @Then("the user should see the dashboard")
    public void the_user_should_see_the_dashboard() {
        //System.out.println("User sees the dashboard");
        // Giả lập: kết quả trả về sau khi login
        String actualTitle = "Dashboard - Welcome";  // Đây là kết quả giả định hoặc lấy từ ứng dụng
        String expectedTitle = "Dashboard - Welcome";

        // So sánh kết quả thực tế với mong đợi
        Assert.assertEquals("Dashboard title không đúng!", expectedTitle, actualTitle);

    }
}

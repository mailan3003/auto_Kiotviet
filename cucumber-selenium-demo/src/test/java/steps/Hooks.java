package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverManager;

public class Hooks {

    @Before
    public void setup() {
        DriverManager.getDriver(); // Mở trình duyệt
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver(); // Đóng trình duyệt
    }
}

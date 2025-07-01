Feature: Login Test on Kiotviet

  Scenario: Login with valid credentials
    Given the user opens the login page
    When the user enters "admin" and "Kiotviet123456"
    Then the user should see the dashboard

  Scenario: Login with invalid credentials
    Given the user opens the login page
    When the user enters "user1" and "Kiotviet12345"
    Then the user should see an error message "Sai tên đăng nhập hoặc mật khẩu."

Feature: Real Login UI

  Scenario: Login with correct credentials
    Given the user opens the login page
    When the user enters "admin" and "123456"
    Then the user should see the dashboard

  Scenario: Login with wrong credentials
    Given the user opens the login page
    When the user enters "admin" and "wrongpass"
    Then the user should see an error message

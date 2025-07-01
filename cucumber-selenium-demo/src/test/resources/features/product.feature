Feature: Add new product

  Scenario: Add product with valid name and category
    Given the user is logged in and on the product page
    When the user adds a new product with name "Bánh Mì Test" and category "Thực phẩm"
    Then the product should be added successfully

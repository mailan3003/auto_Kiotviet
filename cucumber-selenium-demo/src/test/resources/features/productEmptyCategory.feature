Feature: Thêm hàng hóa - Bỏ trống nhóm hàng

  Scenario: Thêm hàng hóa nhưng không chọn nhóm hàng
    Given Given the user is logged in and opens the product page with empty category case
    When the user adds a new product with name "Bánh Mì Test" and no category
    Then the system should show an error message "Vui lòng chọn Nhóm hàng hóa"

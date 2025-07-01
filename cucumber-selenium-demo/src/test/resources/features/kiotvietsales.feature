Feature: Tìm kiếm và thêm sản phẩm vào hóa đơn
  Scenario: Tìm kiếm sản phẩm bằng ô "Tìm hàng hóa (F3)"
    Given nhân viên đang ở màn hình bán hàng KiotViet
    And có hóa đơn "Hóa đơn 1" đang mở
    When nhân viên click vào ô "Tìm hàng hóa (F3)"
    And nhập "Thebol sữa tắm"
    And nhấn Enter hoặc chọn sản phẩm từ dropdown
    Then sản phẩm "Thebol sữa tắm thảo dược Vitamin E nước hoa 2 plus vàng 650g" được thêm vào hóa đơn
    And hiển thị mã vạch "8936131180934"
    And số lượng mặc định là 1
    And giá bán hiển thị là 25,000 VND

#  Scenario: Quét mã vạch để thêm sản phẩm
#    Given nhân viên đang ở màn hình bán hàng
#    And có hóa đơn đang mở
#    When nhân viên quét mã vạch "8936131180934"
#    Then sản phẩm "Thebol sữa tắm thảo dược Vitamin E nước hoa 2 plus vàng 650g" được thêm vào hóa đơn
#    And số lượng mặc định là 1
#    And thành tiền hiển thị 25,000 VNĐ
#
#  Scenario: Sử dụng phím tắt F3 để tìm hàng hóa
#    Given nhân viên đang ở màn hình bán hàng
#    When nhân viên nhấn phím F3
#    Then con trỏ focus vào ô "Tìm hàng hóa (F3)"
#    And nhân viên có thể nhập tên hoặc mã sản phẩm
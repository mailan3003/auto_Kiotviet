Feature: Chỉnh sửa số lượng và giá sản phẩm trong hóa đơn
  Scenario: Thay đổi số lượng sản phẩm bằng cách click vào ô số lượng
    Given có sản phẩm "Thebol sữa tắm" trong hóa đơn với số lượng 1
    When nhân viên click vào ô số lượng (hiển thị "1")
    And nhập số lượng mới là 3
    And nhấn Enter
    Then số lượng được cập nhật thành 3
    And thành tiền được tính lại thành 75,000 VND
    And tổng tiền hàng được cập nhật

  Scenario: Sử dụng nút "+" để tăng số lượng
    Given có sản phẩm trong hóa đơn với số lượng 1
    When nhân viên click vào nút "+" bên cạnh số lượng
    Then số lượng tăng lên 2
    And thành tiền được tính lại tự động

  Scenario: Sử dụng nút "..." để truy cập menu sản phẩm
    Given có sản phẩm trong hóa đơn
    When nhân viên click vào nút "..." ở cuối dòng sản phẩm
    Then hiển thị menu với các tùy chọn
    And nhân viên có thể chọn xóa sản phẩm, chỉnh sửa giá, hoặc các tùy chọn khác
Feature: Quản lý khách hàng

  Scenario: Tìm kiếm khách hàng bằng ô "Tìm khách hàng (F4)"
    Given nhân viên đang ở màn hình bán hàng
    When nhân viên click vào ô "Tìm khách hàng (F4)"
    And nhập số điện thoại "0901234567"
    Then hệ thống tìm kiếm khách hàng theo SĐT
    And hiển thị thông tin khách hàng nếu tìm thấy

  Scenario: Sử dụng phím tắt F4 để tìm khách hàng
    Given nhân viên đang ở màn hình bán hàng
    When nhân viên nhấn phím F4
    Then con trỏ focus vào ô "Tìm khách hàng (F4)"
    And nhân viên có thể nhập thông tin khách hàng

  Scenario: Thêm khách hàng mới bằng nút "+"
    Given nhân viên đang ở màn hình bán hàng
    When nhân viên click vào nút "+" bên cạnh ô tìm khách hàng
    Then mở popup thêm khách hàng mới
    And nhân viên có thể nhập thông tin khách hàng
Feature: Chọn danh sách sản phẩm từ bảng giá chung
  Scenario: Chọn sản phẩm từ bảng giá chung bên phải
    Given màn hình bán hàng đang hiển thị bảng giá chung bên phải
    And có các sản phẩm như "Thebol sữa tắm" (25,000), "Kitkat 4F vuông" (10,000), "Ứng quản răng ri" (250,000)
    When nhân viên click vào sản phẩm "Redbull" (100,000)
    Then sản phẩm "Redbull" được thêm vào hóa đơn bên trái
    And số lượng mặc định là 1
    And giá bán hiển thị 100,000 VND
  Scenario: Cuộn danh sách sản phẩm để xem thêm
    Given bảng giá chung đang hiển thị các sản phẩm
    When nhân viên cuộn xuống để xem thêm sản phẩm
    Then hiển thị thêm các sản phẩm khác như "Jmeter imei 8845", "Jmeter 83588", etc.
    And nhân viên có thể click để chọn bất kỳ sản phẩm nào
Feature: Xử lý thanh toán
  Scenario: Thanh toán với tổng tiền hàng
    Given có hóa đơn với 1 sản phẩm "Thebol sữa tắm"
    And tổng tiền hàng hiển thị "22,500" (sau khi trừ khuyến mãi từ 25,000)
    And có ghi chú "Khuyến mại hóa đơn" màu hồng
    When nhân viên click nút "THANH TOÁN" màu xanh
    Then mở màn hình thanh toán
    And hiển thị số tiền cần thu
  Scenario: Sử dụng nút "IN" để in hóa đơn
    Given có hóa đơn đã hoàn thành
    When nhân viên click nút "IN" màu xám
    Then hóa đơn được in ra máy in
    And format hóa đơn theo cấu hình của cửa hàng
  Scenario: Kiểm tra phân trang hóa đơn
    Given có nhiều hóa đơn
    And hiển thị "1/99" ở góc dưới bên phải
    When nhân viên click nút "<" hoặc ">"
    Then chuyển sang hóa đơn trước hoặc sau
    And số trang được cập nhật tương ứng
Feature: Chức năng ghi chú và khuyến mãi
  Scenario: Thêm ghi chú đơn hàng
    Given có hóa đơn đang soạn thảo
    When nhân viên click vào "Ghi chú đơn hàng" ở góc dưới bên trái
    And nhập nội dung "Giao hàng trước 5h chiều"
    Then ghi chú được lưu và hiển thị
    And ghi chú được in cùng hóa đơn
  Scenario: Áp dụng khuyến mãi hóa đơn
    Given có hóa đơn với tổng tiền 25,000 VND
    When hệ thống tự động áp dụng khuyến mãi
    Then hiển thị dòng "Khuyến mại hóa đơn" màu hồng
    And tổng tiền được giảm xuống 22,500 VND
    And hiển thị số tiền được giảm
Feature: Các tab bán hàng (Bán nhanh, Bán thường, Bán giao hàng)
  Scenario: Chuyển đổi giữa các tab bán hàng
    Given nhân viên đang ở tab "Bán nhanh" (được highlight)
    When nhân viên click vào tab "Bán thường"
    Then chuyển sang chế độ bán thường
    And giao diện có thể thay đổi tương ứng với chế độ bán
  Scenario: Sử dụng tab "Bán giao hàng"
    Given nhân viên đang ở màn hình bán hàng
    When nhân viên click vào tab "Bán giao hàng"
    Then chuyển sang chế độ bán giao hàng
    And có thể hiển thị thêm các trường thông tin giao hàng
Feature: Thông tin cửa hàng và chi nhánh
  Scenario: Kiểm tra thông tin chi nhánh
    Given nhân viên đang ở màn hình bán hàng
    When nhận viên xem thông tin ở góc dưới bên phải
    Then hiển thị "Chi nhánh trung tâm"
    And hiển thị số hotline "1900 6522"
    And hiển thị thông tin "WPS Office"
  Scenario: Liên hệ hotline từ màn hình bán hàng
    Given nhân viên cần hỗ trợ
    When nhân viên click vào số "1900 6522"
    Then có thể gọi điện để được hỗ trợ
Feature: Xóa sản phẩm khỏi hóa đơn
  Scenario: Xóa sản phẩm bằng nút xóa
    Given có sản phẩm "Thebol sữa tắm" trong hóa đơn
    When nhân viên click vào biểu tượng thùng rác ở đầu dòng sản phẩm
    Then sản phẩm được xóa khỏi hóa đơn
    And tổng tiền hàng được cập nhật lại
    And nếu không còn sản phẩm nào, hóa đơn trở về trạng thái trống
  Scenario: Đóng hóa đơn hiện tại
    Given có hóa đơn "Hóa đơn 1" đang mở
    When nhân viên click vào dấu "X" bên cạnh "Hóa đơn 1"
    Then hóa đơn được đóng
    And nếu có thay đổi chưa lưu, hiển thị xác nhận
Feature: Validation và Error Handling
  Scenario: Xử lý khi không tìm thấy sản phẩm
    Given nhân viên đang ở ô tìm hàng hóa
    When nhân viên nhập "Sản phẩm không tồn tại xyz123"
    And nhấn Enter
    Then hệ thống hiển thị "Không tìm thấy sản phẩm"
    And không thêm sản phẩm nào vào hóa đơn
  Scenario: Xử lý khi nhập số lượng không hợp lệ
    Given có sản phẩm trong hóa đơn
    When nhân viên nhập số lượng "-1" hoặc "abc"
    Then hệ thống hiển thị lỗi "Số lượng không hợp lệ"
    And giữ nguyên số lượng cũ
  Scenario: Xử lý thanh toán khi hóa đơn trống
    Given hóa đơn không có sản phẩm nào
    When nhân viên click nút "THANH TOÁN"
    Then hệ thống hiển thị "Vui lòng thêm sản phẩm vào hóa đơn"
    And không cho phép thanh toán

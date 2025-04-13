@allure.label.owner=cuongvd
Feature: Forgot Password Test
  Background: user tap huỷ cập nhật
#    Given User cancel update version
    And User close banner
    And User logged out


  @FP_10
  Scenario Outline: Check nhập mã OTP < 6 số
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    Then Compare Otp entered "<RowName>"
    Examples:
     |    SheetName    | RowName |
     | Forgot Password | FP_10   |


  @FP_13
  Scenario Outline: Check nhập sai mã OTP
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    Then Check user input invalid Otp
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_13   |


  @FP_15
  Scenario Outline: Check xóa mã OTP đã nhập
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User delete a number Otp
    Then Compare Otp after deleting a number "<RowName>"
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_15   |


  @FP_19
  Scenario Outline: Check nhập mã OTP hợp lệ khi còn thời gian đếm
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input Otp forgot password "<RowName>"
    Then Show change password screen
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_19   |


  @FP_20_21
  Scenario Outline: Kiểm tra lấy lại mã OTP mới khi hết hạn
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User waiting to timeout Otp
    * User tab resend Otp
    * User input Otp forgot password "<RowName>"
    Then Show change password screen
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_20   |


  @FP_25
  Scenario Outline: Kiểm tra đổi pass khi nhập pass 5 ký tự
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    Then Check new pass less 6 character
    * Check re input new pass less 6 character
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_25   |


  @FP_26
  Scenario Outline: Kiểm tra đổi pass khi nhập pass = 6 ký tự
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    Then Check show new password "<Password>"
    * Check show re input password "<Password>"
    Examples:
      |    SheetName    | RowName  | Password |
      | Forgot Password  | FP_26   | ******   |


  @FP_27
  Scenario Outline: Kiểm tra đổi pass khi nhập pass = 20 ký tự
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    Then Check show new password "<Password>"
    * Check show re input password "<Password>"
    Examples:
      |    SheetName    | RowName |Password            |
      | Forgot Password | FP_27   |********************|


  @FP_28
  Scenario Outline: Kiểm tra đổi pass khi nhập pass trên 20 ký tự
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    Then Check show new password "<Password>"
    * Check show re input password "<Password>"
    Examples:
      |    SheetName    | RowName | Password           |
      | Forgot Password | FP_28   |********************|


  @FP_29
  Scenario Outline: Kiểm tra đổi pass khi nhập pass chỉ gồm chữ
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password only word "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab register button
    Then User login success
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_29   |


  @FP_30
  Scenario Outline: Kiểm tra đổi pass khi  nhập pass gồm ký tự đặc biệt, in hoa thường
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password with low, up, special character "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab register button
    Then User login success
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_30   |


  @FP_31
  Scenario Outline: Kiểm tra đổi pass khi nhập pass gồm khoảng trắng
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    Then Check new password after input space
    * Check re-input new password after input space
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_31   |


  @FP_35
  Scenario Outline: Kiểm tra đổi pass khi nhập pass chỉ gồm số
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password only number "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab register button
    Then User login success
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_35   |


  @FP_36
  Scenario Outline: Kiểm tra hoàn tất khi nhập đủ 2 trường nhập MK nhưng không trùng nhau
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab register button
    Then Check user change password not same
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_36   |


  @FP_37
  Scenario Outline: Kiểm tra quay lại khi đang đổi pass
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab back button
    Then Show input password screen
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_37   |


  @FP_40
  Scenario Outline: Kiểm tra đổi pass trùng với pass cũ
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab register button
    Then User login success
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_40   |


  @FP_41
  Scenario Outline: Kiểm tra login bằng pass cũ
    Given User tab menu profile
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input old password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then Check user input incorrect password "<RowName>"
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_29   |


  @FP_42
  Scenario Outline: Kiểm tra login bằng pass mới
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then User login success
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_30   |


  @FP_43
  Scenario Outline: Kiểm tra hiện mật khẩu
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab show new password button
    * User tab show re-new password button
    Then Check show hidden new password "<RowName>"
    * Check show hidden new reInput password "<RowName>"
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_43   |


  @FP_44
  Scenario Outline: Kiểm tra ẩn mật khẩu sau khi tab hiện mật khẩu
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User forgot password, input new password "<RowName>"
    * User forgot password, re-input new password "<RowName>"
    * User tab show new password button
    * User tab show re-new password button
    * User tab show new password button
    * User tab show re-new password button
    Then Check show hidden new password "<RowName>"
    * Check show hidden new reInput password "<RowName>"
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_44   |


  @FP_46
  Scenario Outline: Kiểm tra tab back khi đang tại màn đổi mk
    Given User tab menu profile
    When User tab login button
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User tab forgot password button
    * User input Otp forgot password "<RowName>"
    * User tab back button
    Then Show input password screen
    Examples:
      |    SheetName    | RowName |
      | Forgot Password | FP_46   |
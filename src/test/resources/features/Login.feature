@allure.label.owner=cuongvd
Feature: Login Test

  Background: user tap huỷ cập nhật
#    Given User cancel update version
#    And User close banner
    And User logged out

  @Login_1
  @critical
  Scenario Outline: Chưa đăng nhập, kiểm tra đang nhập khi xem video DRM
    And User tab video DRM
    Then Show request login
    And User tab login in video DRM
    And  User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then User login success
    Examples:
      |SheetName| RowName |
      |  Login  | LG_20   |


  @Login_3_4
  @critical
  Scenario: Kiểm tra màn cho phép hiển thị màn Lựa chọn hình thức đăng nhập,đăng ký rồi ấn quay lại
    Given User tab menu profile
    When User tab login button
    Then Show login screen
    And User tab close login button
    Then Show menu screen when not login


  @Login_5
  @critical
  Scenario Outline: Kiểm tra đăng nhập với tài khoản đã bị xoá vĩnh viễn.
  Given User tab menu profile
  When User tab login button
  And User input username, sheet "<SheetName>" and row "<RowName>"
  Then Show alert account is deleted
    Examples:
      |SheetName| RowName |
      |  Login  | LG_5   |


  @Login_12
  Scenario Outline: Kiểm tra đăng nhập bằng số điện thoại nhỏ hơn 10 số
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    Then Show error invalid phone number "<RowName>"
    Examples:
      |SheetName| RowName |
      |  Login  | LG_12   |

  @Login_13
  Scenario Outline: Kiểm tra đăng nhập bằng số điện thoại lớn hơn 10 số
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    Then Check phone number after inputting than 10 number "<RowName>"
    Examples:
      |SheetName| RowName |
      |  Login  | LG_13   |

  @Login_14_18
  Scenario Outline: Kiểm tra đăng nhập bằng số điện thoại đầu số khác 0
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    Then Show error invalid phone number "<RowName>"
    Examples:
      |SheetName| RowName |
      |  Login  | LG_14   |

  @Login_20
  Scenario Outline: Kiểm tra đăng nhập với pass bằng 6 ký tự
    Given User tab menu profile
    When User tab login button
    And  User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then User login success
    Examples:
      |SheetName| RowName |
      |  Login  | LG_20   |

#  @Login_21
#  Scenario: Kiểm tra đăng xuất thành công
#    Given User tab menu profile
#    And User tap Account button
#    And User tap log out
#    And User tap accept log out
#    Then User logout success


  @Login_25
  Scenario Outline: Kiểm tra đăng ký với OTP không tồn tại
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input invalid Otp "<RowName>"
    Then Check user input invalid Otp
    Examples:
      |RowName |
      | LG_25  |


  @Login_26
  Scenario Outline: Kiểm tra đăng ký với OTP nhỏ hơn 6 ký tự
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input invalid Otp "<RowName>"
    Then Check screen when user inputting less 6 Otp characters "<RowName>"
    Examples:
      |RowName |
      | LG_26  |

  @Login_27
  Scenario Outline: Kiểm tra đăng ký với OTP tồn tại
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input Otp has existed
    Then Check user input invalid Otp
    Examples:
      |RowName |
      | LG_27  |


  @Login_41
  Scenario Outline: Kiểm tra hiện thị mật khẩu trên màn nhập mật khẩu
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    Then Check password show "<Password>"
    Examples:
      |SheetName| RowName | Password |
      |Login    | LG_19   |  ••••••  |


  @Login_42
  Scenario Outline: Kiểm tra đăng nhập với pass sai
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then Check user input incorrect password "<RowName>"
    Examples:
      |SheetName| RowName |
      |  Login  | LG_42   |


  @Login_44
  Scenario Outline: Kiểm tra nhập pass sai sau đó nhập lại pass đúng, sau đó nhấn [Hoàn tất]
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input incorrect password "<RowName>"
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then User login success
    Examples:
      |SheetName| RowName |
      |  Login  | LG_44   |


  @Login_47_48
  Scenario Outline: Kiểm tra đăng nhập với SĐT chưa đăng ký, màn hình mặc định nhập mã Otp
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    Then Check input Otp screen
    Examples:
      | RowName |
      | LG_47   |


  @Login_50
  Scenario Outline: Check nhập mã OTP đúng
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    Then Check input sign up password screen
    Examples:
      | RowName |
      | LG_50   |


  @Login_52
  Scenario Outline: Check nhập mã OTP sai sau đó nhập lại OTP đúng
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input invalid Otp "<RowName>"
    * User input valid Otp "<RowName>"
    Then Check input sign up password screen
    Examples:
      | RowName |
      | LG_52   |


#  @Login_53_54
#  Scenario: Kiểm tra số lần nhận mã OTP trong ngày của 1 user
#    Given User tab menu profile
#    When User tab login button
#    And User input username "<RowName>"
#    * User tab continue button
#    * User input incorrect password "<RowName>"
#    * User input password "<RowName>"
#    * User tab continue button
#    Then Check user input incorrect password "<RowName>"


  @Login_56_57
  Scenario Outline: Kiểm tra hiển thị khi hết thời gian đếm ngược mã OTP, sau đó nhấn Gửi lại Otp
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User waiting to timeout Otp
    * User tab resend Otp
    Then Check input Otp screen
    Examples:
      | RowName |
      | LG_56   |


  @Login_60
  Scenario Outline: Kiểm tra khi tại màn "Nhập mã OTP" thực hiện nhấn Back sau đó chọn quay lại Màn Nhập mã OTP
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User tab back button
    * User tab continue button
    Then Check input Otp screen
    Examples:
      | RowName |
      | LG_60   |


  @Login_61_62
  Scenario Outline: Kiểm tra khi tại màn "Nhập mã OTP" thực hiện nhấn Back, đợi 60s sau đó chọn quay lại Màn Nhập mã OTP mới
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User tab back button
    * User waiting 60s at login screen
    * User tab continue button
    * User input valid Otp "<RowName>"
    Then Check input sign up password screen
    Examples:
      | RowName |
      | LG_61   |


  @Login_63
  Scenario Outline: Kiểm tra hiển thị mặc định trên màn hình "Nhập mật khẩu"
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    Then Check input sign up password screen
    Examples:
      | RowName |
      | LG_63   |


  @Login_64
  Scenario Outline: Check nhập mật khẩu dưới 6 số
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    * Sign up,user input new password "<RowName>"
    * User input confirm password "<RowName>"
    Then Disable Hoan Tat button
    Examples:
      | RowName |
      | LG_64   |


  @Login_65
  Scenario Outline: Kiểm tra nhập vào mật khẩu bằng 6 số
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    * Sign up,user input new password "<RowName>"
    * User input confirm password "<RowName>"
    Then Check input sign up password screen
    Examples:
      | RowName |
      | LG_65   |


  @Login_66
  Scenario Outline: Kiểm tra mã OTP khi tại màn "Nhập mật khẩu" ấn Back về màn nhập SĐT
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    * User tab back button
    Then Show login screen
    * Compare phone number "<RowName>"
    Examples:
      | RowName |
      | LG_66   |


  @Login_67
  Scenario Outline: Kiểm tra mã OTP khi tại màn "Nhập mật khẩu" ấn Back về màn nhập SĐT rồi ấn [Tiếp tục]
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    * User tab back button
    * User tab continue button
    Then Check input Otp screen
    Examples:
      | RowName |
      | LG_67   |


  @Login_68
  Scenario Outline: Kiểm tra hiện thị mật khẩu trên màn nhập mật khẩu
    Given User tab menu profile
    When User tab login button
    And User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tap show pass word
    Then Check hidden password show "<RowName>"
    Examples:
      |SheetName| RowName |
      |  Login  | LG_68   |


  @Login_69
  Scenario Outline: Check hiển thị khi nhấn vào button [Hoàn tất], user đăng ký thành công
    Given User tab menu profile
    When User tab login button
    And User input username "<RowName>" phone number is random
    * User tab continue button
    * User input valid Otp "<RowName>"
    * Sign up,user input new password "<RowName>"
    * User input confirm password "<RowName>"
    * User tab Dang ky button
    Then User sign up successfully
    Examples:
        | RowName |
        | LG_69   |

@allure.label.owner=cuongvd
Feature: Profile Test

  Background: user tap huỷ cập nhật
#    Given User cancel update version
    And User close banner
    And User logged out

  @Profile-1
  @UserInform
  @critical
  Scenario Outline: Kiểm tra thông tin hiển thị trên màn hình profile với account chưa mua gói
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Then Show profile screen with account not purchased package
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_1    |


  @Profile-2-3 @UserInform
  @critical
  Scenario Outline: Kiểm tra thông tin hiển thị trên màn hình profile với account đã mua gói
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Then Show profile screen with account purchased package
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_2    |


  @Profile-4 @UserInform
  @critical
  Scenario Outline: Kiểm tra thông tin hiển thị người dùng trên màn hình tài khoản
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    Then Show user profile screen
    Then Check all fields user inform
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_4    |


  @Profile-5-6
  @UserInform
  Scenario Outline: Kiểm tra khi nhấn button đăng xuất
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tap log out
    Then Show pop up confirm logout
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_5    |


  @Profile-7-8
  @UserInform
  Scenario Outline: Kiểm tra khi chọn "huỷ" , "đồng ý" đăng xuất
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tap log out
    * User tap cancel log out
    Then Show user profile screen
    And User tap log out
    * User tap accept log out
    Then User logout success
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_7    |


  @Profile-9-10
  @UserInform
  Scenario Outline: Kiểm tra khi tab "Sửa" button
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    Then Show edit account screen
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_9    |


  @Profile-11-12
  @UserInform
  Scenario Outline: Kiểm tra trường avatar
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit avatar button
    Then Show list images in photo app
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_11    |


  @Profile-14
  @UserInform
  Scenario Outline: Kiểm tra nhập tên chỉ gồm khoảng trắng
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit user name
    * User input user name "<RowName>"
    * User tab confirm edit
    Then Show Toast message 'Vui lòng nhập tên'
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_14    |


  @Profile-15
  @UserInform
  Scenario Outline: Kiểm tra nhập tên gồm chữ hoa, chữ thường , số, ký tự đặc biệt
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit user name
    * User input user name "<RowName>"
    * User tab confirm edit
    Then Show message save success
    Then Save name success "<RowName>"
    Examples:
      |SheetName | RowName  |
      |Profile   |  PF_15   |


  @Profile-17
  @UserInform
  Scenario Outline: Kiểm tra nhập  đúng định dạng @vtvlive.vn
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit email button
    * User input email "<RowName>"
    * User tab confirm edit
    Then Show message save success
    Then Save email success "<RowName>"
    Examples:
      |SheetName| RowName   |
      |Profile  |  PF_17    |


  @Profile-18
  @UserInform
  Scenario Outline: Kiểm tra nhập  đúng định dạng @vtvlive.com.vn
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit email button
    * User input email "<RowName>"
    * User tab confirm edit
    Then Show message save success
    Then Save email success "<RowName>"
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_18   |


  @Profile-19
  @UserInform
  Scenario Outline: Kiểm tra nhập  đúng định dạng @gmail.com
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit email button
    * User input email "<RowName>"
    * User tab confirm edit
    Then Show message save success
    Then Save email success "<RowName>"
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_19   |


  @Profile-20
  @UserInform
  Scenario Outline: Kiểm tra nhập  sai định dạng email
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit email button
    * User input email "<RowName>"
    * User tab confirm edit
    Then Show Toast message 'Vui lòng đúng định dạng email'
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_20   |


  @Profile-22
  @UserInform
  Scenario Outline: Kiểm tra nhập email để trống
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit email button
    * User input email "<RowName>"
    * User tab confirm edit
    Then Show Toast message 'Vui lòng đúng định dạng email'
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_22   |


  @Profile-24-25
  @UserInform
  Scenario Outline: Kiểm tra cập nhật ngày sinh thành công
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab edit button
    * User tab edit birth day button
    * User tab select birth day in date picker "<RowName>"
    * User tab ok date picker
    * User tab confirm edit
    Then Show message save success
    Then Save birth day success "<RowName>"
    Examples:
      |SheetName| RowName   |
      |Profile  |  PF_24    |


#  @Profile-29-30
#  @PackagePurchaseHistory
#  Scenario Outline: Kiểm tra hiển thị với TH chưa mua gói
#    Given User tab menu profile
#    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
#    And User tab account button
#
#    Then Show message save success
#    Examples:
#      |SheetName| RowName  |
#      |Profile  |  PF_29   |
#
#
#  @Profile-32
#  @PackagePurchaseHistory
#  Scenario Outline: Kiểm tra hiển thị với TH đã mua gói, thông tin gói
#    Given User tab menu profile
#    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
#    And User tab account button
#
#    Examples:
#      |SheetName| RowName  |
#      |Profile  |  PF_32  |
#
#  @Profile-33
#  @PackagePurchaseHistory
#  Scenario Outline: Kiểm tra hiển thị với TH đã mua gói, kiểm tra bộ lọc
#    Given User tab menu profile
#    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
#    And User tab account button
#
#    Examples:
#      |SheetName| RowName  |
#      |Profile  |  PF_33   |

  @Profile-38
  @PackagePurchaseHistory
  Scenario Outline: Kiểm tra khi nhấn icon Back
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab button back from 'Tai khoan' screen
    Then Show user profile screen
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_38   |


  @Profile-39
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, đổi pass khi nhập pass 5 ký tự
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input new password with <Number> character "<RowName>"
    * User re-input new password "<RowName>"
    Then Show message 'Mật khẩu phải từ 6 kí tự trở lên'
    Examples:
      |SheetName| RowName  | Number |
      |Profile  |  PF_39   | 5      |


  @Profile-39.1
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, đổi pass khi nhập pass = 6 ký tự
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input new password with <Number> character "<RowName>"
    * User re-input new password "<RowName>"
    Examples:
      |SheetName|  RowName  |  Number |
      |Profile  |  PF_39.1  |   6     |


  @Profile-39.2
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, đổi pass khi nhập pass trên 20 ký tự
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    Then Check input password than 20 character
    Examples:
      |SheetName| RowName    |
      |Profile  |  PF_39.2   |


  @Profile-39.3
  @ChangePassWord
  Scenario Outline: Kiểm tra đổi mật khẩu thành công, khi nhập pass chỉ gồm chữ hoặc số (trùng pass cũ)
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input current password "<RowName>"
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    * User tab confirm change password
    Then Show message 'Đổi mật khẩu thành công'
    * Show profile screen with account not purchased package
    Examples:
      |SheetName| RowName    |
      |Profile  |  PF_39.3   |


  @Profile-39.4
  @ChangePassWord
  Scenario Outline: Kiểm tra đổi mật khẩu thành công, khi  nhập pass gồm ký tự đặc biệt, in hoa, thường
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input current password "<RowName>"
    * User input new password with low, up, special character "<RowName>"
    * User re-input new password "<RowName>"
    * User tab confirm change password
    Then Show message 'Đổi mật khẩu thành công'
    * Show profile screen with account not purchased package
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_39.4   |


  @Profile-39.5
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, đổi pass khi nhập pass gồm khoảng trắng
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    Then Check new password after input space
    * Check re-input new password after input space
    Examples:
      |SheetName|  RowName  |
      |Profile  |  PF_39.5  |


  @Profile-40-41
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, nhập lại MK mới không trùng trường nhập MK mới
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    Then Show message 'Mật khẩu không trùng khớp'
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_40   |


  @Profile-44-45
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, nhập nhập Mk hiện tại không đúng
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input current password "<RowName>"
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    * User tab confirm change password
    Then Show message 'Mật khẩu không chính xác'
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_44   |


  @Profile-46
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, quay lại khi đang đổi pass
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User tab button back from 'Doi mat khau' screen
    Then Show user profile screen
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_46   |


  @Profile-47
  @ChangePassWord
  Scenario Outline: Kiểm tra màn đổi mật khẩu, quay lại khi đang đổi pass
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input current password "<RowName>"
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    * User tab button back from 'Doi mat khau' screen
    Then Show user profile screen
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_47   |


  @Profile-50
  @ChangePassWord
  Scenario Outline: Kiểm tra login bằng pass cũ
    Given User tab menu profile
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input old password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then Check user input incorrect password "<RowName>"
    Examples:
     |SheetName |  RowName   |
     |Profile |  PF_39.4   |


  @Profile-51
  @ChangePassWord
  Scenario Outline: Kiểm tra login bằng pass mới
    Given User tab menu profile
    * User input username, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    * User input password, sheet "<SheetName>" and row "<RowName>"
    * User tab continue button
    Then User login success
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_39.4   |


  @Profile-52-53
  @ChangePassWord
  Scenario Outline: Kiểm tra ẩn hiện mật khẩu
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab account button
    * User tab change password button
    * User input current password "<RowName>"
    * User input new password "<RowName>"
    * User re-input new password "<RowName>"
    * User tab show current password button
    * User tab show new password button
    * User tab show re-new password button
    Then Check show hidden current password "<RowName>"
    * Check show hidden new password "<RowName>"
    * Check show hidden new reInput password "<RowName>"
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_52   |


  @Profile-55-56-57
  @DeviceManager
  Scenario Outline: Kiểm tra ẩn hiện thị quản lý thiết bị
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Quan ly thiet bi'
    Then Check manager device screen "<RowName>"
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_55   |


  @Profile-58-59
  @DeviceManager
  Scenario Outline: Kiểm tra giao diện, chọn 'xác nhận' đăng xuất
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Quan ly thiet bi'
    * User tab logout device button
    Then Show confirm popup logout
    * User confirm logout device button
    Then User logout device success
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_58   |


  @Profile-60-61
  @DeviceManager
  Scenario Outline: Kiểm tra chọn 'huỷ' đăng xuất, nhấn icon back
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Quan ly thiet bi'
    * User tab logout device button
    * User cancel logout device button
    * User tab back from 'Quan ly thiet bi' screen
    Then Show profile screen with account not purchased package
    Examples:
      |SheetName| RowName  |
      |Profile  |  PF_60   |


  @Profile-62-63
  @Following
  Scenario Outline: Kiểm tra hiện thị màn 'Theo dõi'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Theo doi'
    Then Show following screen
    Examples:
      |SheetName| RowName |
      |Profile  |  PF_62  |


  @Profile-64-65
  @Following
  Scenario Outline: Kiểm tra Huỷ 'Theo dõi' tất cả
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Theo doi'
    * User tab 'Huy theo doi tat ca'
    Then Show popup confirm unfollow all
    * User tab Huy button in 'Huy theo doi tat ca'
    Then Show following screen
    Examples:
      | SheetName | RowName |
      | Profile   |   PF_65 |


  @Profile-71-72
  @LaterView
  Scenario Outline: Kiểm tra hiển thị màn hình 'Xem sau'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Xem sau'
    Then Show late view screen
    Then Check VOD show in late view screen "<RowName>"
    Examples:
      | SheetName | RowName |
      | Profile   |  PF_71 |


  @Profile-73-74-75
  @LaterView
  Scenario Outline: Kiểm tra huỷ/xác nhận xoá tất cả video tại tab 'Xem sau'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Xem sau'
    * User tab 'Xoa tat ca' in 'Xem sau' screen
    Then Show popup delete all video
    And User tab 'Huy' button in 'Xoa tat ca'
    Then Show late view screen
    And User tab 'Xoa tat ca' in 'Xem sau' screen
    * User tab 'Xac nhan' button in 'Xoa tat ca'
    Then Show late view screen
    Then Show empty late view screen
    Examples:
      | SheetName | RowName |
      | Profile   |  PF_73  |


  @Profile-76
  @LaterView
  Scenario Outline: Kiểm tra xoá video tại tab 'Xem sau'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Xem sau'
    * User tab button 'Xoa'
    Then Show late view screen
    * Delete success video in late view screen
    Examples:
      | SheetName | RowName |
      | Profile   |  PF_76  |


  @Profile-79
  @LaterView
  Scenario Outline: Kiểm tra khi click back icon tại tab 'Xem sau'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Xem sau'
    And User tab back from 'Xem sau' screen
    Then Show profile screen with account not purchased package
    Examples:
      | SheetName | RowName |
      | Profile   |  PF_79  |



  @Profile-106
  @TermAndPolicy
  Scenario Outline: Kiểm tra hiển thị màn hình 'Điều khoản dịch vụ'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Dieu khoan dich vu'
    Then Show term and policy screen
    And User tab back from 'Dieu khoan dich vu' screen
    Then Show profile screen with account not purchased package
    Examples:
      | SheetName | RowName |
      | Profile   |  PF_106  |


  @Profile-107
  @TermAndPolicy
  Scenario Outline: Kiểm tra hiển thị màn hình 'Thông tin OnLive Tv'
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    And User tab button 'Thong tin ve OnLive tv'
    Then Show inform of VtvLive screen
    And User tab back from 'Thong tin OnLive tv' screen
    Then Show profile screen with account not purchased package
    Examples:
      | SheetName | RowName |
      | Profile   |  PF_107  |


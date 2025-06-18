@allure.label.owner=cuongvd
Feature: Category Test
  Background: user logout
    And User logged out

  @Cat_2_3
  @NotLogin
  Scenario: Chưa login, Header hiển thị logo mới, icon Tìm kiếm, mỗi card Loại danh mục hiển thị Tên, thumbnail, Mô tả, icon Chuyển detaill
    Given User tab view category
    Then Show header
    * Show pin, detail icon, thumb
    * Show all events in category screen

  @Cat_5_6
  @Login
  Scenario Outline:  Login, Header hiển thị logo mới, icon Tìm kiếm, mỗi card Loại danh mục hiển thị Tên, thumbnail, Mô tả, icon Chuyển detaill
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab view category
    Then Show header
    * Show pin, detail icon, thumb
    * Show all events in category screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @Cat_9_10_11_20_21_22_23
  @NotLogin
  Scenario: Chưa login, kiểm tra pin danh mục thành công sau đó unPin danh mục
    Given User tab view category
    And User tab pin
    Then User pin success
    * check title tab pinned
    And User tab pin first category
    Then User unPin success
    * Show all events in category screen


  @Cat_9_10_11_20_21_22_23.1
  @Login
  Scenario Outline: Login, kiểm tra pin danh mục thành công sau đó unPin
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab view category
    And User tab pin
    Then User pin success
    * check title tab pinned
    And User tab pin first category
    Then User unPin success
    * Show all events in category screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @Cat_13
  @NotLogin
  Scenario: Chưa login, Kiểm tra refresh sau khi pin Loại danh mục
    Given User tab view category
    And User tab pin
    Then User pin success
    * check title tab pinned
    And User refresh categories
    Then check title tab pinned


  @Cat_14
  @Login
  Scenario Outline: Kiểm tra Loại danh mục đã pin khi chưa đăng nhập
    Given User tab view category
    And User tab pin
    Then User pin success
    * check title tab pinned
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab view category
    * check title tab pinned
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @Cat_15_16_17_18
  @Login
  Scenario: Kiểm tra pin Loại danh mục khác khi đã có Loại danh mục được pin
    Given User tab view category
    And User tab pin
    Then User pin success
    * check title tab pinned
    And User tab pin
    Then User pin success
    * check title tab pinned


  @Cat_25_26_27
  @Login
  Scenario Outline: Kiểm tra Loại danh mục đã pin khi đã đăng nhập
    Given User tab view category
    And User tab pin
    Then User pin success
    * check title tab pinned
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab view category
    And User tab pin first category
    Then User unPin success
    * Show all events in category screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @Cat_30_31_32_33
  @DetailCategory
  Scenario: Kiểm tra block banner tại chi tiết danh mục
    Given User tab view category
    * User view first detail categories
    Then Show details category screen


  @Cat_34_35
  @DetailCategory
  Scenario: Kiểm tra click icon X tại chi tiết danh mục
    Given User tab view category
    * User view first detail categories
    * User close detail category
    Then Show header
    * Show pin, detail icon, thumb



  @Cat_39_40
  @DetailCategory
  Scenario: Kiểm tra hiển thị chi tiết danh mục sau khi scroll xuống
    Given User tab view category
    * User view detail categories
    * User scroll down
    Then Show details category after scroll down


  @Cat_41_42
  @DetailCategory
  Scenario: Kiểm tra tab icon X, sau khi scroll xuống tại màn chi tiết danh mục
    Given User tab view category
    * User view detail categories
    * User scroll down
    * User close detail category
    Then Show header
    * Show pin, detail icon, thumb


  @Cat_43_45
  @DetailCategory
  Scenario: Kiểm tra thông tin các Tab Danh mục
    Given User tab view category
    * User view detail categories
    Then Show all tabs in detail category


  @Cat_46_47_50
  @DetailCategory
  Scenario: Kiểm tra thông tin các Tab Danh mục
    Given User tab view category
    * User view detail categories
    Then Focus tab 'Tất cả'


  @Cat_51
  @DetailCategory
  Scenario: Kiểm tra một tab danh mục khi không có thông tin
    Given User tab view category
    * Get tab categories with content "emptyAll"
    * User tab to category
    * User tab to sub category
    Then Empty tab


  @Cat_52
  @DetailCategory
  Scenario: Kiểm tra một tab danh mục khi có thông tin
    Given User tab view category
    * User view detail categories
    * User tab any categories
    Then Show content in detail category


  @Cat_53
  @DetailCategory
  Scenario: Kiểm tra một tab danh mục khi không có sự kiện
    Given User tab view category
    * Get tab categories with content "emptyEvent"
    * User tab to category
    * User tab to sub category
    Then Empty event


  @Cat_54_57
  @DetailCategory
  Scenario: Kiểm tra Khối Sự kiện, button tải thêm
    Given User tab view category
    * Get tab categories with content "than1Event"
    * User tab to category
    * User tab to sub category
    Then Show load more button


  @Cat_59_60_61_62_63_64
  @DetailCategory
  Scenario Outline: Kiểm tra thông tin sự kiện đang diễn ra
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab view category
    * Get event in tab category is "live"
    * User tab to category
    * User tab to sub category
    Then Show event "live"
    And User tab thumbnail event VOD
    Then Show vod live
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @Cat_65_66_67_68_69_70_72
  @DetailCategory
  Scenario: Kiểm tra thông tin sự kiện sắp diễn ra, sự kiện chưa đặt thông báo
    Given User tab view category
    * Get event in tab category is "upComing"
    * User tab to category
    * User tab to sub category
    * User find event coming
    * User tab thumbnail event VOD
    Then Show popup notice "active"
    * User tab 'Nhắc tôi' notice
    * User tab thumbnail event VOD
    * User tab 'Đóng' notice
    Then Show list tabs
    * Show thumb images


  @Cat_74_75_76_77_78_79_80
  @DetailCategory
  Scenario: Kiểm tra thông tin sự kiện sắp diễn ra, sự kiện đã đặt thông báo trước đó
    Given User tab view category
    * Get event in tab category is "upComing"
    * User tab to category
    * User tab to sub category
    * User find event coming
    * User tab thumbnail event VOD
    Then Show popup notice "inactive"
    * User tab 'Huỷ nhắc nhở' notice
    * User tab thumbnail event VOD
    * User tab 'Đóng' notice
    Then Show list tabs
    * Show thumb images


  @Cat_81
  @DetailCategory
  Scenario: Kiểm tra tab vào chip danh mục của sự kiện đang diễn ra
    Given User tab view category
    * Get event in tab category is "live"
    * User tab to category
    * User tab to sub category
    * User tab chip category
    Then Show detail category after tab chip category


  @Cat_82
  @DetailCategory
  Scenario: Kiểm tra tab vào chip danh mục của sự kiện sắp diễn ra
    Given User tab view category
    * Get event in tab category is "upComing"
    * User tab to category
    * User tab to sub category
    * User find event coming
    * User tab chip category
    Then Show detail league after tab chip league

  @Cat_83
  @DetailCategory
  Scenario: Kiểm tra tab vào chip giải đấu  của sự kiện đang diễn ra
    Given User tab view category
    * Get event in tab category is "live"
    * User tab to category
    * User tab to sub category
    * User tab chip league
    Then Show detail category after tab chip category


  @Cat_84
  @DetailCategory
  Scenario: Kiểm tra tab vào chip giải đấu của sự kiện sắp diễn ra
    Given User tab view category
    * Get event in tab category is "upComing"
    * User tab to category
    * User tab to sub category
    * User find event coming
    * User tab chip league
    Then Show detail league after tab chip league


  @Cat_90_92
  @NotLogin
  @DetailCategory
  Scenario: Chưa login, Kiểm tra action Đặt thông báo, tắt thông báo thành công với sự kiện chưa diễn ra
    Given User tab view category
    * Get event in tab category is "upComing"
    * User tab to category
    * User tab to sub category
    * User find event coming
    * User tab 3 dot
    Then Show bottom sheet
    And User tab set notice
    Then Set notice success
    * User tab 3 dot
    And User tab set notice
    Then Set disable notice success


  @Cat_91_93
  @Login
  @DetailCategory
  Scenario Outline: Đã login, Kiểm tra action Đặt thông báo, tắt thông báo thành công với sự kiện chưa diễn ra
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab view category
    * Get event in tab category is "upComing"
    * User tab to category
    * User tab to sub category
    * User find event coming
    * User tab 3 dot
    Then Show bottom sheet
    And User tab set notice
    Then Set notice success
    * User tab 3 dot
    And User tab set notice
    Then Set disable notice success
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @Cat_103
  @DetailCategory
  Scenario: Kiểm tra một tab danh mục khi không có video
    Given User tab view category
    * Get tab categories with content "emptyVideo"
    * User tab to category
    * User tab to sub category
    Then Empty video


  @Cat_104_106_109_110
  @DetailCategory
  Scenario: Kiểm tra một tab danh mục khi có video, sau đó scroll down
    Given User tab view category
    * Get tab categories with content "emptyEvent"
    * User tab to category
    * User tab to sub category
    * User scroll down
    Then Show list video



  @Cat_112
  @DetailCategory
  Scenario: Kiểm tra click Thumb của VOD
    Given User tab view category
    * Get tab categories with content "emptyEvent"
    * User tab to category
    * User tab to sub category
    * User scroll down
    * User tab thumbnail VOD
    Then Show vod live


  @Cat_113
  @DetailCategory
  Scenario: Kiểm tra click Chip giải đấu tại VOD
    Given User tab view category
    * Get tab categories with content "emptyEvent"
    * User tab to category
    * User tab to sub category
    * User scroll down
    * User tab chip league in vod
    Then Show btn follow
    * Show details league screen

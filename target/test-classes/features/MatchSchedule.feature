@allure.label.owner=cuongvd
Feature: Match schedule Test

  Background: user tap huỷ cập nhật
#    And User close banner
#    And User logged out

  @LiveScore-2
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra tổng thể giao diện live source
    Given User tab live source
#    Then Show match schedule screen
    Then Show all events in match schedule screen


  @LiveScore-7-8
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra lịch hiển thị ngày hiện tại
    Given User tab live source
    Then Check calender display same design


  @LiveScore-9
  @NotLoggedIn
  Scenario: User chưa login, chọn ngẫu nhiên một ngày trên khung lịch (không phải ngày hôm nay)
    Given User tab live source
    And User select random a day in line date "not today"
    Then Show match schedule screen


  @LiveScore-10-11
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra lịch hiển thị khi tab button hôm nay
    Given User tab live source
    And User tab to day button
    Then Show match schedule screen


  @LiveScore-12-13
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị lịch thi đấu khi chọn ngẫu nhiên một ngày
    Given User tab live source
    And User tab calender button
    * User select random a day in calender
    * User tab ok date picker
    Then Show match schedule screen
    * Show correct date after select day in date picker


  @LiveScore-14
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị lịch thi đấu khi chọn ngẫu nhiên một ngày rồi huỷ
    Given User tab live source
    And User tab calender button
    * User select random a day in calender
    * User tab cancel date picker
    Then Show match schedule screen


  @LiveScore-15-16
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị các tab nội dung
    Given User tab live source
    Then Check display content tabs

  @LiveScore-17
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị khi chọn ngẫu nhiên mội tab nội dung
    Given User tab live source
    And User tab content tab
    Then Show match schedule screen

  @LiveScore-18
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị khi chọn ngẫu nhiên category trong khối trận đấu
    Given User tab live source
    And User tab category name
    Then Check show category detail screen


  @LiveScore-19
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị khi chọn ngẫu nhiên tên giải đấu trong khối trận đấu
    Given User tab live source
    And User tab tournament name
    Then Check show tournament detail screen


  @LiveScore-20
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra hiển thị trận đấu với thời gian từ sớm đến muộn nhất
    Given User tab live source
    Then Check match time in match schedule


  @LiveScore-33
  @NotLoggedIn
  Scenario: User chưa login, kiểm tra sự kiện khi có nhiều hơn 2 đội thi đấu
    Given User tab live source
    Then Check match event than 2 team


  @LiveScore-2.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra tổng thể giao diện live source
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    Then Show match schedule screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-7-8.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra lịch hiển thị ngày hiện tại
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    Then Check calender display same design
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-9.1
  @LoggedIn
  Scenario Outline: User login, chọn ngẫu nhiên một ngày trên khung lịch (không phải ngày hôm nay)
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User select random a day in line date "not today"
    Then Show match schedule screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-10-11.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra lịch hiển thị khi tab button hôm nay
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User select random a day in line date "not today"
    And User tab to day button
    Then Show match schedule screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-12-13.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra hiển thị lịch thi đấu khi chọn ngẫu nhiên một ngày
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User tab calender button
    * User select random a day in calender
    * User tab ok date picker
    Then Show match schedule screen
    * Show correct date after select day in date picker
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-14.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra hiển thị lịch thi đấu khi chọn ngẫu nhiên một ngày rồi huỷ
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User tab calender button
    * User select random a day in calender
    * User tab cancel date picker
    Then Show match schedule screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-15-16.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra hiển thị các tab nội dung
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    Then Check display content tabs
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-17.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra hiển thị khi chọn ngẫu nhiên mội tab nội dung
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User tab content tab
    Then Show match schedule screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |

  @LiveScore-18.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra hiển thị khi chọn ngẫu nhiên category trong khối trận đấu
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User tab category name
    Then Check show category detail screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-19.1
  @LoggedIn
  Scenario Outline: User login, kiểm tra hiển thị khi chọn ngẫu nhiên tên giải đấu trong khối trận đấu
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    And User tab tournament name
    Then Check show tournament detail screen
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-20.1
  @LoggedIn
  Scenario Outline: User chưa login, kiểm tra hiển thị trận đấu với thời gian từ sớm đến muộn nhất
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    Then Check match time in match schedule
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |


  @LiveScore-33.1
  @LoggedIn
  Scenario Outline: User chưa login, kiểm tra sự kiện khi có nhiều hơn 2 đội thi đấu
    Given User tab menu profile
    When User login to app with userName, passWord from sheet "<SheetName>" and row "<RowName>"
    Given User tab live source
    Then Check match event than 2 team
    Examples:
      |SheetName | RowName  |
      |Login     |  LG_1    |
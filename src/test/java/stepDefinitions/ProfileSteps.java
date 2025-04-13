package stepDefinitions;

import base.BasePage;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locator.Locator;
import mySQL.MonGoDb;
import mySQL.PostGre;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static constant.Query.TV_ID_QUERY_USER;

public class ProfileSteps extends BasePage {
    private HashMap<String, String> dataProfile;
    public HomePage homePage;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public TestContext testContext;
    public PostGre postGre;
    public MonGoDb monGoDb;
    public ProfileSteps(TestContext testContext) {
        logger.info("constructor profile step ");
        this.testContext = testContext;
        homePage = testContext.getPageObjectManager().getHomePage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
        profilePage = testContext.getPageObjectManager().getProfilePage();
        postGre = testContext.getPageObjectManager().getPostGre();
        monGoDb = testContext.getPageObjectManager().getMonGoDb();
        excelReader.initWorkbook();
        excelReader.readSheet("Profile");
    }
    @When("User login to app with old password, from sheet {string} and row {string}")
    public void userLoginToApp(String Sheet, String rowName) {
        excelReader.readSheet(Sheet);
        loginPage.gotoLogin();
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputUserName(dataProfile.get("User name"));
        loginPage.continueLogin();
        loginPage.inputPassWord(dataProfile.get("Old password"));
        loginPage.continueLogin();
        loginPage.isMore3Devices();
    }
    @And("User tab account button")
    public void userTabAccountBtn() {
        profilePage.clickAccount();
    }
    @And("User tab button 'Quan ly thiet bi'")
    public void userTabDeviceManager() {
        profilePage.clickDeviceManager();
    }
    @And("User tab button 'Theo doi'")
    public void userTabFollowBtn() {
        profilePage.clickFollow();
    }
    @And("User tab button 'Xem sau'")
    public void userTabViewLaterBtn() {
        profilePage.clickVỉewLater();
    }
    @And("User tab button 'Dieu khoan dich vu'")
    public void userTabTermAndServiceBtn() {
        profilePage.clickTermAndService();
    }
    @And("User tab button 'Thong tin ve OnLive tv'")
    public void userTabInformOfOnLiveBtn() {
        profilePage.clickInformOfOnLive();
    }
    @And("User tab button back from 'Tai khoan' screen")
    public void userTabBackUserProfile() {
        profilePage.clickBack();
    }
    @And("User tab button back from 'Doi mat khau' screen")
    public void userTabBackChangePassWord() {
        profilePage.clickBack();
    }
    @And("User tab change password button")
    public void userTabChangePassBtn() {
        profilePage.clickChangePassWord();
    }

    @And("User input current password {string}")
    public void userInPutCurrentPass(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.inputCurrentPass(dataProfile.get("Current password"));
    }
    @And("User input new password {string}")
    public void userInPutNewPass(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.inputNewPass(dataProfile.get("New password"));
    }
    @And("User input new password with {int} character {string}")
    public void userInPutNewRandomPass(int number, String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        String pass = profilePage.createRandomPassWord("number", number);
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("New password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("ReInput password"));
        profilePage.inputNewPass(dataProfile.get("New password"));
    }
    @And("User input new password with low, up, special character {string}")
    public void userInPutNewUpperLowerSpecialPass(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        excelReader.setCell(dataProfile.get("Current password"), excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Old password"));
        String pass = profilePage.createRandomPassWord("lowerUpperSpecial", 9);
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("New password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("ReInput password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Current password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Password"));
        profilePage.inputNewPass(dataProfile.get("New password"));
    }
    @And("User re-input new password {string}")
    public void userReInPutNewPass(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.reInputNewPass(dataProfile.get("ReInput password"));
    }
    @And("User tab show current password button")
    public void userTabShowCurrentPass() {
        profilePage.clickShowCurrentPass();
    }

    @And("User tab show new password button")
    public void userTabShowNewPass() {
        profilePage.clickShowNewPass();
    }
    @And("User tab show re-new password button")
    public void userTabShowReNewPass() {
        profilePage.clickShowNewRePass();
    }
    @And("User tab confirm change password")
    public void tabConfirmChangePass() {
        loginPage.continueLogin();
    }
    @And("User tab edit button")
    public void userTabEditBtn() {
        profilePage.clickEdit();
    }
    @And("User tab edit avatar button")
    public void userTabEditAvatarBtn() {
        profilePage.clickEditAvatar();
    }
    @And("User tab confirm edit")
    public void userTabConfirmEdit() {
        profilePage.clickConfirmEdit();
    }
    @And("User tab edit user name")
    public void userTabEditUserName() {
        profilePage.clickEditName();
    }
    @And("User input user name {string}")
    public void userInputUserName(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.inputName(dataProfile.get("User name"));
    }
    @And("User tab edit email button")
    public void userTabEditEmail() {
        profilePage.clickEditEmail();
    }
    @And("User tab edit birth day button")
    public void userTabEditBirthDay() {
        profilePage.clickEditBirthDay();
    }

    @And("User tab select birth day in date picker {string}")
    public void userSelectBirthDay(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.setAndroidDatePicker(dataProfile.get("Birth day"));
    }
    @And("User tab ok date picker")
    public void userTabOkBtnDatePicker() {
        profilePage.clickOkBtnDatePicker();
    }
    @And("User tab cancel date picker")
    public void userTabCancelBtnDatePicker() {
        profilePage.cancelDatePicker();
    }
    @And("User input email {string}")
    public void userInputEmail(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.inputEmail(dataProfile.get("Email"));
    }
    @And("User tab logout device button")
    public void userTabLogoutDeviceBtn() {
        profilePage.clickLogoutDevice();
    }
    @And("User tab back from 'Quan ly thiet bi' screen")
    public void userTabBackFromDeviceManager() {
        profilePage.clickBack();
    }
    @And("User cancel logout device button")
    public void userTabCancelLogoutDeviceBtn() {
        profilePage.cancelLogoutDevice();
    }
    @And("User confirm logout device button")
    public void userTabConfirmLogoutDeviceBtn() {
        homePage.acceptNotice();
    }
    @And("User logout device success")
    public void userLogoutDeviceSuccess() {
        profilePage.logoutDeviceSuccess();
    }
    @And("User tab 'Huy theo doi tat ca'")
    public void userTabUnFollowAll() {
        profilePage.unFollowAll();
    }
    @And("User tab Huy button in 'Huy theo doi tat ca'")
    public void userTabCancelUnFollow() {
        homePage.cancelNotice();
    }
    @And("User tab Xac nhan button in 'Huy theo doi tat ca'")
    public void userTabConfirmUnFollow() {
        homePage.acceptNotice();
    }
    @And("User tab back from 'Xem sau' screen")
    public void userTabBackFromLateView() {
        profilePage.clickBack();
    }
    @And("User tab button 'Xoa'")
    public void userTabDeleteBtnInLateView() {
        testContext.getScenarioContext().setContext(Context.NAME_VIDEO_DELETED, keyword.getText(Locator.LATE_VIEW_LBL_FIRST_NAME_VIDEO));
        profilePage.clickDeleteInLateView();
    }
    @And("User tab 'Huy' button in 'Xoa tat ca'")
    public void userTabCancelDeleteAll() {
        homePage.cancelNotice();
    }
    @And("User tab 'Xac nhan' button in 'Xoa tat ca'")
    public void userTabConfirmDeleteAll() {
        homePage.acceptNotice();
    }
    @And("User tab 'Xoa tat ca' in 'Xem sau' screen")
    public void userDeleteAllVideoInLateView() {
        profilePage.deleteAllVideoInLateViewTab();
    }

    @And("User tab back from 'Dieu khoan dich vu' screen")
    public void userTabBackFromTermAndPolicy() {
        profilePage.clickBack();
    }
    @And("User tab back from 'Thong tin OnLive tv' screen")
    public void userTabBackFromInformOnLiveTv() {
        profilePage.clickBack();
    }





    @Then("Show message 'Đổi mật khẩu thành công'")
    public void checkChangePassWordSuccess() {
        profilePage.successChangePassWord();
    }
    @Then("Show message 'Mật khẩu phải từ 6 kí tự trở lên'")
    public void checkShowMessageErrorLess6Character() {
        profilePage.errorLess6Character();
    }
    @Then("Check input password than 20 character")
    public void checkInputPassThan20Character() {
        profilePage.inputThan20Character();
    }
    @Then("Show message 'Mật khẩu không trùng khớp'")
    public void checkShowMessageErrorSamePassWord() {
        profilePage.errorNotSamePassword();
    }
    @Then("Show message 'Mật khẩu không chính xác'")
    public void checkShowMessageIncorrectPass() {
        profilePage.errorIncorrectPassword();
    }
    @Then("Check new password after input space")
    public void passWordAfterInputSpace() {
        profilePage.newSpacePassWord();
    }
    @Then("Check re-input new password after input space")
    public void rePassWordAfterInputSpace() {
        profilePage.reNewSpacePassWord();
    }

    @Then("Check show hidden current password {string}")
    public void checkHiddenCurrentPassWord(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.checkHiddenAndShowCurrentPass(dataProfile.get("Current password"));
    }
    @Then("Check show hidden new password {string}")
    public void checkHiddenNewPassWord(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.checkHiddenAndShowNewPass(dataProfile.get("New password"));
    }
    @Then("Check show hidden new reInput password {string}")
    public void checkHiddenReNewPassWord(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.checkHiddenAndShowReNewPass(dataProfile.get("ReInput password"));
    }
    @Then("Show Toast message 'Vui lòng nhập tên'")
    public void checkShowMessageNameError() {
        profilePage.showToastPleaseInputName();
    }
    @Then("Show Toast message 'Vui lòng đúng định dạng email'")
    public void checkShowMessageEmailError() {
        profilePage.showToastErrorEmailForm();
    }
    @Then("Show profile screen with account not purchased package")
    public void checkInformProfile() {
        profilePage.checkProfileScreen();
    }
    @Then("Show profile screen with account purchased package")
    public void checkInformProfilePurchasedPackage() {
        profilePage.checkProfileScreen();
    }
    @Then("Show user profile screen")
    public void checkInformProfileUser() {
        profilePage.checkProfileUserScreen();
    }
    @Then("Check all fields user inform")
    public void checkUserInform() {
        postGre.setUpConnectTvPlayId();
        profilePage.checkUserInform(postGre, "PHONE_NUMBER", "all");
    }
    @Then("Show pop up confirm logout")
    public void checkLogoutPopup() {
        profilePage.checkPopUpConfirmLogout();
    }
    @Then("Show edit account screen")
    public void checkEditUserScreen() {
        profilePage.checkEditUserScreen();
    }
    @Then("Show message save success")
    public void checkUserSaveSuccessInform() {
        profilePage.saveInformSuccess();
    }
    @Then("Save name success {string}")
    public void checkUserNameAfterEdit(String rowName) {
        postGre.setUpConnectTvPlayId();
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.checkInformUserName(dataProfile.get("User name"));
        profilePage.checkUserInform(postGre, "84" + dataProfile.get("User name").substring(1), "name");

    }
    @Then("Save email success {string}")
    public void checkUserEmailAfterEdit(String rowName) {
        postGre.setUpConnectTvPlayId();
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.checkInformEmail(dataProfile.get("Email"));
        profilePage.checkUserInform(postGre, dataProfile.get("Email"), "email");
    }
    @Then("Save birth day success {string}")
    public void checkBirthDayAfterEdit(String rowName) {
        postGre.setUpConnectTvPlayId();
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        profilePage.checkInformBirthDay(dataProfile.get("Birth day"));
        profilePage.checkUserInform(postGre, dataProfile.get("Birth day"), "birth day");
    }
    @Then("Show list images in photo app")
    public void showPhotoApp() {
        keyword.verifyElementDisplay(Locator.ANDROID_PHOTO_APP);
    }
    @Then("Check manager device screen {string}")
    public void showMngDeviceScreen(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        postGre.setUpConnectTvPlayId();
        ResultSet res  = postGre.queryDb(TV_ID_QUERY_USER.replace("key", "84" + dataProfile.get("User name").substring(1)));
        HashMap<String, String> dbData = postGre.getResultDataBase(res);
        profilePage.checkManagerDeviceScreen();
        profilePage.checkShowInformDevice(monGoDb, dbData.get("id"));
    }
    @Then("Show confirm popup logout")
    public void showConfirmLogoutPopup() {
        profilePage.showConfirmLogoutPopup();
    }
    @Then("Show following screen")
    public void showFollowingScreen() {
        profilePage.checkFollowingScreen();
    }
    @Then("Show late view screen")
    public void showLateViewScreen() {
        profilePage.checkLateViewScreen();
    }
    @Then("Show empty late view screen")
    public void showEmptyLateViewScreen() {
        keyword.verifyElementPresent(Locator.LATE_VIEW_LBL_EMPTY_VIDEO);
        keyword.verifyElementNotDisplayed(Locator.LATE_VIEW_LBL_NAME_VIDEO);
    }
    @Then("Check VOD show in late view screen {string}")
    public void checkVODShowInLateView(String rowName) {
        dataProfile = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        postGre.setUpConnectTvPlayId();
        ResultSet res  = postGre.queryDb(TV_ID_QUERY_USER.replace("key", "84" + dataProfile.get("User name").substring(1)));
        HashMap<String, String> dbData = postGre.getResultDataBase(res);
        postGre.setUpConnectTvPlayTv();
        profilePage.checkVODShowInLateView(postGre, dbData.get("id"));
    }
    @Then("Delete success video in late view screen")
    public void deleteSuccessVideo() {
        profilePage.deleteSuccessVideo(testContext.getScenarioContext().getContext(Context.NAME_VIDEO_DELETED).toString());
    }
    @Then("Show popup confirm unfollow all")
    public void showConfirmCancelFollowPopup() {
        profilePage.showConfirmCancelFollowPopup();
    }
    @Then("Show popup delete all video")
    public void showConfirmDeleteVideoInLateView() {
        profilePage.showConfirmDeleteAllVideoInLateView();
    }
    @Then("Show term and policy screen")
    public void showTermAndPolicyScreen() {
        profilePage.checkTermAndPolicyScreen();
    }
    @Then("Show inform of VtvLive screen")
    public void showInformOfVtvLiveScreen() {
        profilePage.checkInformOfVtvLiveScreen();
    }









    @Then("User enters invalid credentials and Login will be unsuccessful with error message")
    public void entersInvalidCredential(DataTable userTable) {
        System.out.println("Enter Credentials");
        List<Map<String, String>> user = userTable.asMaps(String.class, String.class);
        String userName = user.get(0).get("Username");
        System.out.println("Username :" + userName);
        String passWord = user.get(0).get("Password");
        System.out.println("Password :" + passWord);
        String errorMessage = user.get(0).get("ErrorMessage");
    }
}

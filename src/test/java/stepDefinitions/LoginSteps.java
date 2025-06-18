package stepDefinitions;

import base.BasePage;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locator.Locator;
import mySQL.MySQL;
import mySQL.PostGre;
import pages.HomePage;
import pages.LoginPage;

import java.sql.ResultSet;
import java.util.HashMap;
import static constant.Constant.*;
import static constant.Query.*;

public class LoginSteps extends BasePage {
    public HomePage homePage;
    public LoginPage loginPage;
    public PostGre postGre;
    public MySQL mySQL;
    private HashMap<String, String> dataLogin;
    public TestContext testContext;
    public LoginSteps(TestContext testContext) {
        logger.info("constructor login step ");
        this.testContext = testContext;
        homePage = testContext.getPageObjectManager().getHomePage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
        postGre = testContext.getPageObjectManager().getPostGre();
        mySQL = testContext.getPageObjectManager().getMySQL();
        excelReader.initWorkbook();
        excelReader.readSheet("Login");
    }
    @When("User login to app with userName, passWord from sheet {string} and row {string}")
    public void userLoginToApp(String sheet, String rowName) {
        excelReader.readSheet(sheet);
        loginPage.gotoLogin();
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputUserName(dataLogin.get("User name"));
        loginPage.continueLogin();
        loginPage.inputPassWord(dataLogin.get("Password"));
        loginPage.continueLogin();
        loginPage.isMore3Devices();
    }
    @When("User tab login button")
    public void userTabLoginButton() {
        loginPage.gotoLogin();
    }
    @And("User logged out")
    public void userLoggedOut() {
        loginPage.isUserLogout();
    }
    @And("User input username, sheet {string} and row {string}")
    public void userInputUsername(String sheet, String rowName) {
        excelReader.readSheet(sheet);
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @And("User input username {string} (phone number is random)")
    public void userInputRandomUsername(String rowName) {
        String phone = loginPage.getPhoneNumber();
        excelReader.setCell(phone, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("User name"));
        loginPage.inputUserName(phone);
    }
    @And("User input password, sheet {string} and row {string}")
    public void userInputPassword(String sheet, String rowName) {
        excelReader.readSheet(sheet);
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputPassWord(dataLogin.get("Password"));
    }
    @And("User input old password, sheet {string} and row {string}")
    public void userInputOldPassword(String sheet, String rowName) {
        excelReader.readSheet(sheet);
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputPassWord(dataLogin.get("Old password"));
    }
    @And("User input incorrect password {string}")
    public void userInputIncorrectPassword(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputPassWord(dataLogin.get("Error password"));
    }
    @And("Sign up,user input new password {string}")
    public void userInputNewPasswordSignUp(String rowName) {
        keyword.pressKeyNumber("escape");
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputPassWord(dataLogin.get("New Password"));
    }
    @And("User input confirm password {string}")
    public void userInputConfirmPassword(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputConfirmPassWord(dataLogin.get("Confirm Password"));
    }
    @And("User tab continue button")
    public void userTabContinueButton() {
        loginPage.continueLogin();
    }
    @And("User tab Dang ky button")
    public void userTabSignUpButton() {
        loginPage.continueLogin();
    }
    @And("User waiting 60s at login screen")
    public void userWaiting(){
        for (int i = 0; i < 12; i++){
            loginPage.waitForNextStep(5);
            loginPage.clickUserNameTxt();
        }
    }
    @And("User tab back button")
    public void userTabBackButton(){
        loginPage.goBack();
    }
    @And("User tab close login button")
    public void userTabCloseLoginBtn(){
        loginPage.closeLogin();
    }
    @And("User input valid Otp {string}")
    public void userInputValidOtp(String rowName){
        postGre.setUpConnectTvPlayId();
        keyword.webDriverWaitForElementPresent(Locator.SIGN_UP_TXT_ENTER_OPT,10);
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        ResultSet res  = postGre.queryDb(TV_ID_QUERY_USER.replace("key", "84" + dataLogin.get("User name").substring(1)));
        HashMap<String, String> dbData = postGre.getResultDataBase(res);
        loginPage.inputOtp(dbData.get("otp_code"));
    }
    @And("User input invalid Otp {string}")
    public void userInputInvalidOtp(String rowName){
        keyword.webDriverWaitForElementPresent(Locator.SIGN_UP_TXT_ENTER_OPT,10);
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputOtp(dataLogin.get("Invalid otp"));
    }
    @And("User input Otp has existed")
    public void userInputExistedOtp(){
        postGre.setUpConnectTvPlayId();
        keyword.webDriverWaitForElementPresent(Locator.SIGN_UP_TXT_ENTER_OPT,10);
        ResultSet res  = postGre.queryDb(TV_ID_QUERY_USER.replace("key", "84" + PHONE_NUMBER.substring(1)));
        HashMap<String, String> dbData = postGre.getResultDataBase(res);
        loginPage.inputOtp(dbData.get("otp_code"));
    }



    @And("User tap Account button")
    public void userTabAccountBtn(){
        loginPage.viewUserInform();
    }
    @And("User tap log out")
    public void userTapLogout(){
        loginPage.clickLogout();
    }
    @And("User tap accept log out")
    public void userTapAcceptLogout() {
        homePage.acceptNotice();
    }
    @And("User tap cancel log out")
    public void userTapCancelLogout() {
        loginPage.cancelLogout();
    }
    @And("User tap show pass word")
    public void userTapShowPassword() {
        loginPage.clickShowPass();
    }
    @And("User waiting to timeout Otp")
    public void userWaitingTimeOtp() {
        loginPage.waitTimeOtp();
    }
    @And("User tab resend Otp")
    public void userTabResendOtp() {
        loginPage.resendOtp();
    }

    @Then("Show login screen")
    public void showInformLoginSignUpScreen() {
        loginPage.checkLoginScreen();
    }
    @Then("Show input password screen")
    public void showInputPasswordScreen() {
        loginPage.checkInputPassWordScreen();
    }
    @Then("Compare phone number {string}")
    public void comparePhoneNumberInLoginScreen(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.comparePhoneNumber(dataLogin.get("User name"));
    }
    @Then("Show menu screen when not login")
    public void showProfileScreenWhenNotLogin() {
        loginPage.checkProfileScreenWhenNotLogin();
    }
    @Then("Show error invalid phone number {string}")
    public void showInvalidPhoneNumber(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.verifyMessPassWord(dataLogin.get("Error message"));
    }
    @Then("Check phone number after inputting than 10 number {string}")
    public void checkPhoneNumberAfterInput(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.checkPhoneNumberAfterInputThan10Number(dataLogin.get("User name"));
    }
    @Then("Check user input incorrect password {string}")
    public void checkIncorrectPassword(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.incorrectPassword(dataLogin.get("Error message"));
    }
    @Then("User login success")
    public void userLoginSuccess() {
        loginPage.successLogin();
    }
    @Then("User logout success")
    public void userLogoutSuccess() {
        loginPage.successLogout();
    }
    @Then("Check user input invalid Otp")
    public void checkUserInputInvalidOtp() {
        loginPage.invalidOtp();
    }
    @Then("Check screen when user inputting less 6 Otp characters {string}")
    public void checkUserInputOtpLess6Characters(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.compareOtpEntered(dataLogin.get("Invalid Otp"));
    }
    @Then("Check password show {string}")
    public void checkShowPassword(String passWord) {
        loginPage.comparePassword(passWord);
    }
    @Then("Check hidden password show {string}")
    public void checkShowHiddenPassword(String rowName) {
        dataLogin = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.comparePassword(dataLogin.get("Password"));
    }
    @Then("Check input Otp screen")
    public void checkDefaultInformOtpScreen() {
        loginPage.verifyOtpScreen();
    }
    @Then("Disable Hoan Tat button")
    public void disableContinueButton() {
        loginPage.continueLogin();
        loginPage.verifyInputPasswordSignUpScreen();
    }
    @Then("Check input sign up password screen")
    public void checkDefaultPasswordScreen() {
        loginPage.verifyInputPasswordSignUpScreen();
    }
    @Then("User sign up successfully")
    public void userSignUpSuccess() {
        loginPage.successLogin();
    }

    @Then("Show alert account is deleted")
    public void alertDeletedAccount() {
        loginPage.messDeletedAccount();
    }
}

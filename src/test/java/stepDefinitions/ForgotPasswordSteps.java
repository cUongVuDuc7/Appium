package stepDefinitions;

import base.BasePage;
import cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.util.HashMap;

public class ForgotPasswordSteps extends BasePage {
    public ForgotPasswordPage forgotPassWordPage;
    private HashMap<String, String> dataForgot;
    public LoginPage loginPage;
    public HomePage homePage;
    public ProfilePage profilePage;
    public TestContext testContext;
    public ForgotPasswordSteps(TestContext testContext) {
        logger.info("constructor forgot step");
        this.testContext = testContext;
        homePage = testContext.getPageObjectManager().getHomePage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
        profilePage = testContext.getPageObjectManager().getProfilePage();
        forgotPassWordPage = testContext.getPageObjectManager().getForgotPassWordPage();
        excelReader.initWorkbook();
        excelReader.readSheet("Forgot Password");
    }
    @And("User tab forgot password button")
    public void userTabForgotPassWordButton() {
        forgotPassWordPage.clickForgot();
    }
    @And("User input Otp forgot password {string}")
    public void userInputOtp(String rowName){
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.inputOtp(dataForgot.get("Otp"));
    }
    @And("User delete a number Otp")
    public void userDeleteANumberOtp(){
        loginPage.deleteOtp(1);
    }
    @And("User tab register button")
    public void userTabRegisterBtn(){
        loginPage.continueLogin();
    }
    @And("User forgot password, input new password {string}")
    public void userInPutCurrentPass(String rowName) {
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        forgotPassWordPage.inputNewPass(dataForgot.get("New Password"));
    }
    @And("User forgot password, input new password with low, up, special character {string}")
    public void userInPutNewUpperLowerSpecialPass(String rowName) {
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        excelReader.setCell(dataForgot.get("Password"), excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Old password"));
        String pass = profilePage.createRandomPassWord("lowerUpperSpecial", 6);
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("New password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("ReInput password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Password"));
        profilePage.inputNewPass(dataForgot.get("New password"));
    }
    @And("User forgot password, input new password only word {string}")
    public void userReInPutNewOnlyWordPass(String rowName) {
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        excelReader.setCell(dataForgot.get("Password"), excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Old password"));
        String pass = profilePage.createRandomPassWord("lowerChar", 6);
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("New password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("ReInput password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Password"));
        profilePage.inputNewPass(dataForgot.get("New password"));
    }
    @And("User forgot password, input new password only number {string}")
    public void userReInPutNewOnlyNumberPass(String rowName) {
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        excelReader.setCell(dataForgot.get("Password"), excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Old password"));
        String pass = profilePage.createRandomPassWord("number", 6);
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("New password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("ReInput password"));
        excelReader.setCell(pass, excelReader.getIndexRow(rowName) , excelReader.getIndexCell("Password"));
        profilePage.inputNewPass(dataForgot.get("New password"));
    }
    @And("User forgot password, re-input new password {string}")
    public void userInPutNewPass(String rowName) {
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        forgotPassWordPage.reInputNewPass(dataForgot.get("ReInput Password"));
    }
    @And("User tab show new pass")
    public void userTabShowPass1() {
        forgotPassWordPage.clickShowPass1();
    }
    @And("User tab show re input new pass")
    public void userTabReInputShowPass() {
        forgotPassWordPage.clickShowPass2();
    }
    
    @Then("Compare Otp entered {string}")
    public void compareSameOtp(String rowName){
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.compareOtpEntered(dataForgot.get("Otp"));
    }
    @Then("Compare Otp after deleting a number {string}")
    public void compareOtpDeleted(String rowName){
        dataForgot = excelReader.getDataFromExcel(excelReader.getIndexRow(rowName));
        loginPage.compareOtpAfterDeleting(dataForgot.get("Otp"), 1);
    }
    @Then("Show change password screen")
    public void showChangePassword(){
        forgotPassWordPage.showChangePassWordScreen();
    }
    @Then("Check new pass less 6 character")
    public void newPassLess6Character(){
        forgotPassWordPage.passWordLess6Char();
    }
    @Then("Check re input new pass less 6 character")
    public void reInputNewPassLess6Character(){
        forgotPassWordPage.reInputPassWordLess6Char();
    }
    @Then("Check show new password {string}")
    public void checkShowNewPassWord(String pass) {
        profilePage.checkHiddenAndShowNewPass(pass);
    }
    @Then("Check show re input password {string}")
    public void checkShowReInputPassWord(String pass) {
        profilePage.checkHiddenAndShowReNewPass(pass);
    }
    @Then("Check user change password not same")
    public void notSamePassword() {
        forgotPassWordPage.errorNotSamePassword();
    }

}

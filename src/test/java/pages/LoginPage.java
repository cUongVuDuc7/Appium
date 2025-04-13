package pages;

import base.BasePage;
import constant.Constant;
import locator.Locator;
import mySQL.MySQL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.Normalizer;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static constant.Constant.*;

public class LoginPage extends BasePage {
    public HomePage homePage;
    public LoginPage(){
        logger.info("constructor login page ");
        homePage = new HomePage();
    }
    public void gotoLogin() {
        keyword.click(Locator.LOGIN_BTN);
    }
    public void isMore3Devices(){
        if(keyword.verifyElementPresent(Locator.LOGIN_LBL_NOTICE_3_DEVICE) && keyword.verifyElementPresent(Locator.LOGOUT_TITLE_POP_UP))
        {
            keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, MESS_NOTICE_MORE_3_DEVICES);
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
        }
    }
    public void waitForNextStep(int time){
        keyword.sleep(time);
    }
    public void goBack() {
        logger.info("go back");
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_BTN_BACK, 10);
        keyword.click(Locator.LOGIN_BTN_BACK);
    }
    public void closeLogin() {
        logger.info("close login");
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_BTN_CLOSE, 10);
        keyword.click(Locator.LOGIN_BTN_CLOSE);
    }
    public String getPhoneNumber(){
        int ranNum = ThreadLocalRandom.current().nextInt(100000,999999);
        logger.info("Get phone number " + ranNum);
        return "0363" + ranNum;
    }
    public void checkLoginScreen(){
        logger.info("check Login Screen");
        keyword.pressKeyNumber("escape");
        keyword.verifyElementDisplay(Locator.LOGIN_TXT_USER_NAME);
        keyword.verifyElementDisplay(Locator.LOGIN_BTN_CONTINUE);
        keyword.verifyElementDisplay(Locator.LOGIN_DDL_COUNTRY);
        keyword.verifyElementDisplay(Locator.LOGIN_LOGO_FLAG);
        keyword.verifyElementDisplay(Locator.LOGIN_BTN_CLOSE);
        keyword.assertEqual(Locator.SIGN_UP_LBL_TITLE_PHONE, Constant.TITLE_USERNAME);
        keyword.assertEqual(Locator.LOGIN_LBL_SUB_TITLE_LOGIN, Constant.SUB_TITLE_LOGIN);
        keyword.assertEqual(Locator.LOGIN_LBL_SOCIAL, Constant.LOGIN_TITLE_SOCIAL);
        
    }
    public void checkInputPassWordScreen(){
        logger.info("check Input Password Screen");
        keyword.verifyElementDisplay(Locator.LOGIN_LBL_TITLE_LOGIN);
        keyword.verifyElementDisplay(Locator.LOGIN_TXT_USER_NAME);
        keyword.verifyElementDisplay(Locator.LOGIN_BTN_CONTINUE);
        keyword.assertEqual(Locator.SIGN_UP_LBL_TITLE_PHONE, Constant.TEXT_BOX_PASSWORD);
    }

    public void logOut(String flag){
        logger.info("logOut ");
        clickLogout();
        if(flag.equals("success")) {
            homePage.acceptNotice();
//            keyword.assertEqual(Locator.LOGOUT_TOAST_SUCCESS, MESSAGE_SUCCESS_LOGOUT);
//            keyword.webDriverWaitInvisibleElement(Locator.LOGOUT_TOAST_SUCCESS,10);
            keyword.sleep(5);
        }
        else {
            cancelLogout();
        }
    }
    public void isUserLogout() {
        logger.info("is User Logout");
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_MENU_PROFILE,10);
        keyword.click(Locator.HOME_BTN_MENU_PROFILE);
        keyword.sleep(1);
        if (keyword.verifyElementPresent(Locator.LOGIN_BTN)) {
            keyword.click(Locator.HOME_BTN_HOME);
        }
        else {
            viewUserInform();
            logOut("success");
        }
    }
    public void checkHiddenText(By locator, String text){
        logger.info("check Hidden Text");
        keyword.assertEqual(locator, text);
    }
    public void inputUserName(String name){
        logger.info("input User Name ");
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_USER_NAME, name);
    }
    public void clickUserNameTxt(){
        logger.info("Click User Name ");
        keyword.click(Locator.LOGIN_TXT_USER_NAME);
    }
    public void continueLogin(){
        logger.info("continue login ");
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    public void inputPassWord(String pass){
        logger.info("input Pass Word ");
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_PASSWORD,pass);
    }
    public void inputConfirmPassWord(String passWord){
        logger.info("Input confirm pass word " + passWord);
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_CONFIRM_PASSWORD, passWord);
    }
    public void login(String phoneNumber, String passWord) {
        logger.info("loginSuccess ");
        inputUserName(phoneNumber);
        inputPassWord(passWord);
    }
    public void checkProfileScreenWhenNotLogin(){
        logger.info("check Profile Screen When not Login");
        keyword.verifyElementDisplay(Locator.LOGIN_BTN);
        keyword.verifyElementDisplay(Locator.MENU_BTN_TERM_SERVICE);
        keyword.verifyElementDisplay(Locator.MENU_BTN_VTV_INFORM);
        keyword.assertEqual(Locator.MENU_BTN_TERM_SERVICE, Constant.TITLE_TERM_AND_POLICY);
        keyword.assertEqual(Locator.MENU_BTN_VTV_INFORM, Constant.TITLE_INFORM_OF_VTV);
    }
    public void clickShowPass(){
        keyword.click(Locator.LOGIN_BTN_SHOW_PASS);
    }
    public void comparePassword(String passWord){
        keyword.sleep(0.3);
        keyword.assertEqualData(keyword.getText(Locator.LOGIN_TXT_PASSWORD), passWord);
    }

    public void verifyMessPassWord(String mess){
        keyword.sleep(0.2);
        keyword.assertEqual(Locator.LOGIN_LBL_ERROR, mess);
    }
    public void checkPhoneNumberAfterInputThan10Number(String phone){
        Assert.assertEquals(keyword.getText(Locator.LOGIN_TXT_USER_NAME).replaceAll(" ",""), phone.substring(0, phone.length() - 1));
    }
    public void incorrectPassword(String mess){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_MESS_LOGIN_SUCCESS, 10);
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, mess);

    }
    public void successLogin(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_LOGIN_SUCCESS, 10);
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, Constant.MESSAGE_SUCCESS_LOGIN);
    }
    public void messDeletedAccount(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_LOGIN_SUCCESS, 10);
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, Constant.MESSAGE_LOGIN_BY_DELETED_ACC);
    }
    public void successLogout(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_SUCCESS, 10);
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, Constant.MESSAGE_SUCCESS_LOGOUT);
    }
    public void verifyOtpScreen(){
        keyword.webDriverWaitForElementPresent(Locator.SIGN_UP_LBL_TITLE_PHONE, 10);
        keyword.verifyElementDisplay(Locator.SIGN_UP_TIMELINE_OTP);
        keyword.assertEqual(Locator.LOGIN_LBL_SUB_TITLE_LOGIN, Constant.SUB_TITLE_CONFIRM_PHONE_NUMBER);
        keyword.assertTrue(keyword.getText(Locator.SIGN_UP_TIMELINE_OTP).contains(Constant.MESSAGE_TIME_LIVE_OTP));
        keyword.assertTrue(keyword.getText(Locator.SIGN_UP_LBL_TITLE_PHONE).contains(Constant.TITLE_PHONE));
    }
    public void verifyInputPasswordSignUpScreen(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_LBL_HELLO, 10);
        keyword.pressKeyNumber("escape");
        keyword.assertEqual(Locator.SIGN_UP_LBL_SET_PASS, Constant.SIGN_UP_TITLE_SET_PASS);
        keyword.verifyElementDisplay(Locator.CHANGE_PASSWORD_TXT_CURRENT);
        keyword.verifyElementDisplay(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN);
        keyword.verifyElementDisplay(Locator.CHANGE_PASSWORD_BTN_SHOW_PASS);
        keyword.verifyElementDisplay(Locator.LOGIN_BTN_CONTINUE);
    }
    public void inputOtp(String otp){
        keyword.sleep(0.5);
        System.out.println("OTP " + otp);
        keyword.click(By.id(Locator.SIGN_UP_TXT_EDIT_OPT.replace("index", "1")));
        for (int i = 0; i < otp.split("").length; i++) {
            keyword.pressKeyNumber(otp.split("")[i]);
        }
    }
    public void invalidOtp(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_ERROR, 10);
        keyword.assertEqual(Locator.LOGIN_TOAST_ERROR, MESSAGE_INVALID_OTP);
    }
    public void viewUserInform(){
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.click(Locator.PROFILE_BTN_USER_ACCOUNT);
    }
    public void clickLogout(){
        keyword.click(Locator.LOGOUT_BTN);
    }
    public void cancelLogout(){
        keyword.click(Locator.LOGOUT_BTN_CANCEL);
    }
    public void waitTimeOtp(){
        while (true){
            keyword.sleep(5);
            if(keyword.verifyElementPresent(Locator.SIGN_UP_BTN_RESEND_OTP)){
                break;
            }
            keyword.click(Locator.SIGN_UP_TIMELINE_OTP);
        }
        keyword.assertEqual(Locator.SIGN_UP_TIMELINE_OTP, MESSAGE_EXPIRED_OTP);
    }
    public void resendOtp(){
        keyword.click(Locator.SIGN_UP_BTN_RESEND_OTP);
    }
    public void comparePhoneNumber(String phoneNumber){
        logger.info("compare Phone Number");
        Assert.assertEquals(keyword.getText(Locator.LOGIN_TXT_USER_NAME).replaceAll(" ",""), phoneNumber);
    }
    public void compareOtpEntered(String otp){
        logger.info("compare otp");
        for (int i = 0; i < otp.split("").length; i++) {
            keyword.assertEqualData(keyword.getText(By.id(Locator.SIGN_UP_TXT_EDIT_OPT.replace("index", String.valueOf(i+1)))), otp.split("")[i]);
        }
    }
    public void deleteOtp(int numberDel){
        logger.info("delete otp");
        for (int i = 0; i < numberDel; i++) {
            keyword.clearText(By.id(Locator.SIGN_UP_TXT_EDIT_OPT.replace("index", String.valueOf(i+1))));
        }
    }
    public void compareOtpAfterDeleting(String otp, int numberDelete){
        logger.info("compare otp");
        int size = otp.split("").length;
        for (int i = 0; i < numberDelete; i++) {
            keyword.assertEqualData(keyword.getText(By.xpath(Locator.SIGN_UP_TXT_EDIT_OPT.replace("index", String.valueOf(i+1)))), "");
        }
        for (int i = numberDelete; i < size - numberDelete; i++) {
            keyword.assertEqualData(keyword.getText(By.xpath(Locator.SIGN_UP_TXT_EDIT_OPT.replace("index", String.valueOf(i+1)))), otp.split("")[i]);
        }

    }
}

package pages;

import base.BasePage;
import constant.Constant;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import locator.Locator;
import mySQL.MonGoDb;
import mySQL.PostGre;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import static constant.Constant.*;
import static constant.Query.*;
import static locator.Locator.LATE_VIEW_LBL_NAME_VIDEO_DELETED;
import static utilities.DateTime.getMonthNameInThreeChars;
import static utilities.DateTime.getMonthNumber;

public class ProfilePage extends BasePage {
    public HomePage homePage;
    public ProfilePage(){
        logger.info("constructor profile page ");
        homePage = new HomePage();
    }
    public String createRandomPassWord(String flag, int length){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        String specialChar = PropertiesFile.getPropValue("SPECIAL_CHARACTER");
        switch (flag){
            case "lowerChar" :
                for (int i = 0; i < length; i++) {
                    sb.append((char) ('a' + random.nextInt(26)));
                }
                break;
            case "upperChar" :
                for (int i = 0; i < length; i++) {
                    sb.append((char) ('A' + random.nextInt(26)));
                }
                break;
            case "specialChar" :
                for (int i = 0; i < length; i++) {
                    sb.append(specialChar.charAt(random.nextInt(specialChar.length())));
                }
                break;
            case "number" :
                for (int i = 0; i < length; i++) {
                    sb.append( (char) (random.nextInt(10)));
                }
                break;
            case "lowerUpperSpecial" :
                for (int i = 0; i < length / 3; i++) {
                    sb.append(specialChar.charAt(random.nextInt(specialChar.length())));
                    sb.append((char) ('A' + random.nextInt(26)));
                    sb.append((char) ('a' + random.nextInt(26)));
                }
                break;
        }
        return sb.toString();
    }
    public void checkProfileScreen(){
        logger.info("check Profile Screen");
        keyword.verifyElementDisplay(Locator.PROFILE_IMG_AVATAR);
        keyword.verifyElementDisplay(Locator.PROFILE_LBL_USER_NAME);
        keyword.verifyElementDisplay(Locator.PROFILE_BTN_USER_ACCOUNT);
        keyword.verifyElementDisplay(Locator.PROFILE_BTN_MANAGE_DEVICE);
        keyword.verifyElementDisplay(Locator.PROFILE_BTN_FOLLOW);
        keyword.verifyElementDisplay(Locator.PROFILE_BTN_VIEW_LATER);
        keyword.verifyElementDisplay(Locator.PROFILE_BTN_INFORM_ON_LIVE);
        keyword.verifyElementDisplay(Locator.PROFILE_BTN_TERM_AND_SERVICE);
    }
    public void checkProfileUserScreen(){
        logger.info("check Profile Screen");
        keyword.verifyElementDisplay(Locator.PROFILE_ACCOUNT_BTN_EDIT);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL);
        keyword.verifyElementDisplay(Locator.PROFILE_ACCOUNT_LBL_BIRTH_DAY);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_PHONE);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_CHANGE_PASS);
        keyword.verifyElementDisplay(Locator.LOGOUT_BTN);
    }
    public void checkPopUpConfirmLogout(){
        logger.info("check Profile Screen");
        keyword.verifyElementDisplay(Locator.LOGOUT_BTN_CANCEL);
        keyword.verifyElementDisplay(Locator.LOGOUT_BTN_CONFIRM);
        keyword.verifyElementDisplay(Locator.LOGOUT_TITLE_POP_UP);
        keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, MESSAGE_YOU_SURE_LOGOUT);
    }
    public void checkEditUserScreen(){
        logger.info("check edit user screen");
        keyword.verifyElementDisplay(Locator.PROFILE_IMG_AVATAR);
        keyword.verifyElementDisplay(Locator.PROFILE_ACCOUNT_BTN_EDIT);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_BIRTH_DAY);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_EDIT_PHONE);
        keyword.verifyElementDisplay(Locator.EDIT_ACCOUNT_BTN_CHANGE_PASS);
    }
    public void checkManagerDeviceScreen(){
        logger.info("check device manager screen");
        keyword.verifyElementDisplay(Locator.DEVICE_MANAGER_BTN_LAYOUT_DEVICE);
        keyword.assertEqual(Locator.DEVICE_MANAGER_LBL_TITLE, TITLE_DEVICE_MANAGE);
        keyword.verifyElementDisplay(Locator.DEVICE_MANAGER_LBL_CURRENT);
        keyword.verifyElementDisplay(Locator.DEVICE_MANAGER_LBL_NAME_DEVICE);
        keyword.verifyElementDisplay(Locator.DEVICE_MANAGER_BTN_LOGOUT);
    }
    public void checkShowInformDevice(MonGoDb monGoDb, String idUser){
        monGoDb.connectMonGoDb("MONGO_TV_PLAY_ID_URL");
        monGoDb.getDatabase("TV_PLAY_ID_USER");
        monGoDb.getTable("session_device");
        String deviceName = monGoDb.getResultDevice(eq("user_id", Integer.parseInt(idUser)));
        keyword.assertEqualData(keyword.getText(Locator.DEVICE_MANAGER_LBL_NAME_DEVICE),deviceName);
    }
    public void checkFollowingScreen(){
        logger.info("check device manager screen");
        keyword.verifyElementDisplay(Locator.FOLLOWING_BTN_CANCEL_FOLLOW);
        keyword.assertEqual(Locator.FOLLOWING_LBL_TITLE, TITLE_FOLLOWING);
    }
    public void checkLateViewScreen(){
        logger.info("check later view screen");
        keyword.verifyElementDisplay(Locator.LATE_VIEW_BTN_DELETE_ALL);
        keyword.verifyElementDisplay(Locator.LATE_VIEW_BTN_DELETE);
        keyword.assertEqual(Locator.LATE_VIEW_LBL_TITLE, TITLE_LATE_VIEW_ON_LIVE);
    }
    public String getListNameVod(){
        String listName = "";
        List<WebElement> nameElement = keyword.getListElement(Locator.LATE_VIEW_LBL_NAME_VIDEO);
        for (int i = 0; i < nameElement.size(); i++) {
            listName += nameElement.get(i).getText() + ";";
        }
        return listName;
    }
    public void checkVODShowInLateView(PostGre postGre, String userId){
        logger.info("check vod show in later view");
        if(keyword.elementIsDisplayed(Locator.LATE_VIEW_LIST_VIDEO)){
            keyword.verifyElementPresent(Locator.LATE_VIEW_BTN_VIDEO);
            String listNameVideo = getListNameVod();
            ResultSet res  = postGre.queryDb(TV_PLAY_QUERY_USER_EVENT_VOD.replace("key", userId));
            Map<Integer, Map<String, Object>> dataMap = postGre.getResultDbThan1Rows(res);
            HashMap<String, String> dataVideo;
            for (int i = 0; i < dataMap.size(); i++) {
                ResultSet res2  = postGre.queryDb(TV_PLAY_QUERY_EVENT_VIDEO.replace("key", dataMap.get(i).get("video_id").toString()));
                dataVideo = postGre.getResultDataBase(res2);
                keyword.assertTrue(listNameVideo.contains(dataVideo.get("name")));
            }
        }
    }
    public void checkTermAndPolicyScreen(){
        logger.info("check term and policy screen");
        keyword.verifyElementDisplay(Locator.TERM_AND_POLICY_DOCUMENT);
        keyword.assertEqual(Locator.INFORM_ON_LIVE_TV_LBL_TITLE, TITLE_MAIN_POLICY_TERM);
    }
    public void checkInformOfVtvLiveScreen(){
        logger.info("check inform of VtvLive screen");
        keyword.verifyElementDisplay(Locator.TERM_AND_POLICY_DOCUMENT);
        keyword.assertEqual(Locator.INFORM_ON_LIVE_TV_LBL_TITLE, TITLE_INFORM_OF_VTV);
    }
    public void clickAccount(){
        logger.info("click account");
        keyword.click(Locator.PROFILE_BTN_USER_ACCOUNT);
    }
    public void clickDeviceManager(){
        logger.info("click Device Manager");
        keyword.click(Locator.PROFILE_BTN_MANAGE_DEVICE);
    }
    public void clickFollow(){
        logger.info("click Follow");
        keyword.click(Locator.PROFILE_BTN_FOLLOW);
    }
    public void clickVỉewLater(){
        logger.info("click xem sau");
        keyword.click(Locator.PROFILE_BTN_VIEW_LATER);
    }
    public void clickInformOfOnLive(){
        logger.info("click xem thông tin onlive tv");
        keyword.click(Locator.PROFILE_BTN_INFORM_ON_LIVE);
    }
    public void clickTermAndService(){
        logger.info("click dieu khoan dich vu");
        keyword.click(Locator.PROFILE_BTN_TERM_AND_SERVICE);
    }
    public void clickBack(){
        logger.info("click back");
        keyword.click(Locator.PROFILE_ACCOUNT_BTN_BACK);
    }
    public void clickChangePassWord(){
        logger.info("click change password");
        keyword.click(Locator.PROFILE_ACCOUNT_BTN_CHANGE_PASS);
    }
    public void inputCurrentPass(String pass){
        logger.info("input current password");
        keyword.clearTextAndSendKey(Locator.CHANGE_PASSWORD_TXT_CURRENT, pass);
    }
    public void inputNewPass(String pass){
        logger.info("input current password");
        keyword.clearTextAndSendKey(Locator.CHANGE_PASSWORD_TXT_NEW_PASS, pass);
    }
    public void reInputNewPass(String pass){
        logger.info("input current password");
        keyword.clearTextAndSendKey(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN, pass);
    }
    public void errorLess6Character(){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_LBL_ERROR, MESSAGE_ERROR_LESS_6_CHARACTER);
    }
    public void inputThan20Character(){
        int lengthNewPass = keyword.getText(Locator.CHANGE_PASSWORD_TXT_NEW_PASS).length();
        int lengthReInputPass = keyword.getText(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN).length();
        Assert.assertEquals(lengthNewPass, lengthReInputPass, 20);
    }
    public void errorNotSamePassword(){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_LBL_ERROR_SAME_PASS, MESSAGE_ERROR_SAME_PASSWORD);
    }
    public void errorIncorrectPassword(){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_LBL_INCORRECT_PASS, MESSAGE_ERROR_INCORRECT_PASSWORD);
    }

    public void newSpacePassWord(){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_TXT_NEW_PASS, TEXT_BOX_LBL_INPUT_PASSWORD);
    }
    public void reNewSpacePassWord() {
        keyword.assertEqual(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN, TEXT_BOX_LBL_RE_INPUT_PASSWORD);
    }
    public void successChangePassWord(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_SUCCESS, 10);
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, Constant.MESSAGE_SUCCESS_CHANGE_PASS);
    }
    public void clickEdit(){
        logger.info("click edit");
        keyword.click(Locator.PROFILE_ACCOUNT_BTN_EDIT);
    }
    public void clickEditAvatar(){
        logger.info("click edit avatar");
        keyword.click(Locator.PROFILE_IMG_AVATAR);
    }
    public void clickConfirmEdit(){
        logger.info("click confirm edit");
        keyword.sleep(0.5);
        keyword.click(Locator.PROFILE_ACCOUNT_BTN_CONFIRM_EDIT);
    }
    public void clickEditName(){
        logger.info("click edit user name ");
        keyword.click(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME);
    }
    public void inputName(String name){
        logger.info("nhập tên ");
        keyword.clearTextAndSendKey(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME, name);
    }
    public void clickEditEmail(){
        logger.info("click edit email ");
        keyword.click(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL);
    }
    public void inputEmail(String email){
        logger.info("nhập email ");
        keyword.clearTextAndSendKey(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL, email);
    }
    public void clickEditBirthDay(){
        logger.info("click birth day");
        keyword.click(Locator.EDIT_ACCOUNT_BTN_EDIT_BIRTH_DAY);
    }
    public void clickShowCurrentPass(){
        logger.info("click show current pass");
        keyword.click(Locator.CHANGE_PASSWORD_BTN_SHOW_PASS);
    }
    public void clickShowNewPass(){
        logger.info("click show new pass");
        keyword.click(Locator.CHANGE_PASSWORD_BTN_SHOW_PASS_2);
    }
    public void clickShowNewRePass(){
        logger.info("click show re_new pass");
        keyword.click(Locator.CHANGE_PASSWORD_BTN_SHOW_PASS_3);
    }
    public void showToastPleaseInputName(){
        keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_FAIL_NAME, MESSAGE_UPDATE_FAIL_NAME);
    }
    public void showToastErrorEmailForm(){
        keyword.sleep(0.1);
        keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_FAIL_EMAIL, MESSAGE_UPDATE_FAIL_EMAIL_INFORM);
    }
    public void saveInformSuccess(){
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, USER_INFORM_MESS_EDIT_SUCCESS);
    }
    public void checkInformUserName(String name){
        keyword.assertEqual(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME, name);
    }
    public void checkInformEmail(String email){
        keyword.assertEqual(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL, email);
    }
    public void checkInformBirthDay(String day){
        keyword.assertEqual(Locator.EDIT_ACCOUNT_BTN_EDIT_BIRTH_DAY, day);
    }
    public void checkHiddenAndShowCurrentPass(String passWord){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_TXT_CURRENT, passWord);
    }
    public void checkHiddenAndShowNewPass(String passWord){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_TXT_NEW_PASS, passWord);
    }
    public void checkHiddenAndShowReNewPass(String passWord){
        keyword.assertEqual(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN, passWord);
    }

    public void setAndroidDatePicker(String date) {
        keyword.sleep(0.5);
        logger.info("date input from excel : " + date);
        int thisYear = Integer.parseInt(keyword.getText(Locator.DATE_PICKER_LBL_YEAR));
        String today = keyword.getText(Locator.DATE_PICKER_LBL_DATE);
        int thisMonth = getMonthNumber(getMonthNameInThreeChars(today));
        logger.info("current Month : " + thisMonth);
        String[] splitDate = date.split("-");
        int givenDay = Integer.valueOf(splitDate[0]);
        int givenMonth = Integer.valueOf(splitDate[1]);
        int givenYear = Integer.valueOf(splitDate[2]);
        logger.info("day : " + givenDay + " month: " + givenMonth +  " year: " + givenYear);
        int forwardTaps = 0;
        int backwardTaps = 0;
        int yearFactor = 0;
        if (givenYear == thisYear)
        {
            if (givenMonth >= thisMonth)
            {
                forwardTaps = givenMonth - thisMonth;
            } else {
                backwardTaps = thisMonth - givenMonth;
            }
        }
        else if (givenYear > thisYear)
        {
            yearFactor = (givenYear - thisYear) * 12;
            if (givenMonth >= thisMonth)
            {
                forwardTaps = yearFactor + (givenMonth - thisMonth);
            } else {
                forwardTaps = yearFactor - (thisMonth - givenMonth);
            }
        }
        else {
            yearFactor = (thisYear - givenYear) * 12;
            if (givenMonth >= thisMonth)
            {
                backwardTaps = yearFactor - (givenMonth - thisMonth);
            } else {
                backwardTaps = yearFactor + (thisMonth - givenMonth);
            }
        }
        logger.info("forward Taps " + forwardTaps);
        logger.info("backward Taps " + backwardTaps);
        for (int i=1; i<=forwardTaps; i++) {
            keyword.click(Locator.DATE_PICKER_BTN_NEXT);
        }
        for (int i=1; i<=backwardTaps; i++) {
            keyword.click(Locator.DATE_PICKER_BTN_PRE);
        }
        String xpath = Locator.DATE_PICKER_BTN_DATE;
        keyword.click(By.xpath(xpath.replace("day", String.valueOf(givenDay))));
    }
    public void clickOkBtnDatePicker(){
        logger.info("oke birth day ");
        keyword.click(Locator.DATE_PICKER_BTN_OK);
    }
    public void cancelDatePicker(){
        logger.info("cancel birth day ");
        keyword.click(Locator.DATE_PICKER_BTN_CANCEL);
    }
    public void getListDevice(){
        List<WebElement> listDevice = keyword.getListElement(By.xpath(Locator.DEVICE_MANAGER_LBL_NAME_DEVICES));
    }
    public void clickLogoutDevice(){
        logger.info("Tab logout device");
        keyword.click(Locator.DEVICE_MANAGER_BTN_LOGOUT);
    }
    public void showConfirmLogoutPopup(){
        logger.info("Check logout device popup");
        keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, TITLE_DEVICE_MANAGE_LOGOUT);
    }
    public void cancelLogoutDevice(){
        logger.info("cancel logout device");
        keyword.click(Locator.NOTICE_BTN_CANCEL);
    }
    public void logoutDeviceSuccess(){
        logger.info("success logout device");
        keyword.assertEqual(Locator.LOGOUT_TOAST_SUCCESS, MESSAGE_LOGOUT_SUCCESS_DEVICE);
        homePage.verifyHomeScreen();
    }
    public void unFollowAll(){
        logger.info("un follow");
        keyword.click(Locator.FOLLOWING_BTN_CANCEL_FOLLOW);
    }
    public void showConfirmCancelFollowPopup(){
        logger.info("Check cancel follow  popup");
        keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, TITLE_CANCEL_FOLLOWING_POPUP);
    }
    public void clickDeleteInLateView(){
        logger.info("click Xoa in xem sau");
        keyword.click(Locator.LATE_VIEW_BTN_DELETE);
    }
    public void deleteAllVideoInLateViewTab(){
        logger.info("delete All Video In Late ViewTab ");
        keyword.click(Locator.LATE_VIEW_BTN_DELETE_ALL);
    }
    public void showConfirmDeleteAllVideoInLateView(){
        logger.info("Check delete all video popup in lateView");
        keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, TITLE_LATE_VIEW_POPUP);
    }

    public void deleteSuccessVideo(String nameVideo){
        logger.info("delete success video");
        keyword.verifyElementNotDisplayed(By.xpath(LATE_VIEW_LBL_NAME_VIDEO_DELETED.replace("key", nameVideo)));
    }
    public String getBirthDay(String day){
        String[] date = day.split("-");
        return date[2] + "-" + date[1] + "-" + date[0];
    }
    @Step("Kiểm tra thông tin user: {0} với trường: {1}")
    public void checkUserInform(PostGre postGre ,String key, String cases){
        logger.info("check User Inform ");
        String getKey = PropertiesFile.getPropValue(key);
        if(getKey == null){
            getKey = key;
        }
        ResultSet res  = postGre.queryDb(TV_ID_QUERY_USER.replace("key", getKey));
        HashMap<String, String> dataMap = postGre.getResultDataBase(res);
        String birthDay = getBirthDay(keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_BIRTH_DAY));
        switch (cases) {
            case "name":
                postGre.checkDataBase(dataMap, "fullname", keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME));
                break;
            case "email":
                postGre.checkDataBase(dataMap, "email", keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL));
                break;
            case "birth day":
                postGre.checkDataBase(dataMap, "dob", birthDay);
                break;
            case "all":
                postGre.checkDataBase(dataMap, "name,fullname,email,dob",
                        keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_PHONE) + "," + keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME)
                                + "," + keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL) + "," + birthDay);
                break;
        }
    }
    public String getUserInform(String flag) {
        logger.info("get User Inform ");
        String inform = "";
        switch (flag) {
            case "phone":
                inform =  keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_PHONE);
                break;
            case "name":
                inform =  keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME);
                break;
            case "email":
                inform = keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL);
                break;
            case "birth day":
                inform = keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_BIRTH_DAY);
                break;
            case "all":
                inform = keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_PHONE)
                        + "," + keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_NAME) + "," +
                        keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_EMAIL) + "," + keyword.getText(Locator.EDIT_ACCOUNT_BTN_EDIT_BIRTH_DAY);
                break;
        }
        return inform;
    }
}

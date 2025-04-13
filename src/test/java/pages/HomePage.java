package pages;

import base.BasePage;
import enums.Context;
import helpers.LogHelper;
import locator.Locator;
import mySQL.MySQL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import static constant.Constant.*;
import static constant.Query.*;

public class HomePage extends BasePage {
    public MySQL mySQL;
    public HomePage(){
        logger.info("constructor home page");
        mySQL = new MySQL();
    }
    public String getVideoIsDRM(String name){
        String drmVOD ="";
        mySQL.setUpConnectTvPlayUpload();
        ResultSet res = mySQL.queryDb(TV_PLAY_UPLOAD.replace("key",name));
        HashMap<String, String> dbData = mySQL.getResultDataBase(res);
        if(dbData.get("is_drm").equals("0")){
            drmVOD = name;
        }
        return drmVOD;
    }
    public void viewMenuProfile(){
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_MENU_PROFILE,10);
        keyword.click(Locator.HOME_BTN_MENU_PROFILE);
    }
    public void viewHomePage(){
        keyword.click(Locator.HOME_BTN_HOME);
    }
    public void viewLiveSource(){
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_LIVE_SCORE,5);
        keyword.click(Locator.HOME_BTN_LIVE_SCORE);
    }
    public void viewHomeNotification(){
        keyword.click(Locator.HOME_BTN_NOTIFICATION);
    }
    public void viewCategory(){
        keyword.click(Locator.HOME_BTN_CATEGORY);
    }
    public void acceptNotice(){
        keyword.webDriverWaitForElementPresent(Locator.NOTICE_BTN_OK, 5);
        keyword.click(Locator.NOTICE_BTN_OK);
    }
    public void cancelNotice() {
        keyword.sleep(0.2);
        keyword.click(Locator.NOTICE_BTN_CANCEL);
    }
    public void closeBanner() {
        keyword.webDriverWaitForElementPresent(Locator.BANNER_BTN_CLOSE, 10);
        keyword.click(Locator.BANNER_IMAGES);
        keyword.click(Locator.BANNER_BTN_CLOSE);
    }
    public void verifyHomeScreen(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_HOME);
        keyword.verifyElementDisplay(Locator.HOME_BTN_MENU_PROFILE);
        keyword.verifyElementDisplay(Locator.HOME_BTN_LIVE_SCORE);
        keyword.verifyElementDisplay(Locator.HOME_BTN_NOTIFICATION);
        keyword.verifyElementDisplay(Locator.HOME_BTN_CATEGORY);
    }
    public void findVideoForYou(){
        String index = "";
        while(true) {
            if ((keyword.verifyElementPresent(Locator.HOME_LBL_VIDEO_FOR_YOU)
            && keyword.verifyElementPresent(By.xpath(Locator.HOME_LBL_TITLE_VIDEO_FOR_YOU.replace("index","1")))) ||
            (keyword.verifyElementPresent(Locator.HOME_LBL_VIDEO_FOR_YOU)
            && keyword.verifyElementPresent(By.xpath(Locator.HOME_LBL_TITLE_VIDEO_FOR_YOU.replace("index","2"))))){
                break;
            }
            keyword.scrollByCoordinates(0.2, -0.2);
        }
        for (int i = 0; i < 4; i++) {
            index = String.valueOf(i+1);
            if (keyword.verifyElementPresent(By.xpath(Locator.HOME_LBL_TITLE_VIDEO_FOR_YOU.replace("index", index)))) {
                if (keyword.getText(By.xpath(Locator.HOME_LBL_TITLE_LIST_VIDEO_FOR_YOU.replace("index", index))).contains("Dành cho bạn")) {
                    keyword.click(By.xpath(Locator.HOME_IMG_VIDEO_FOR_YOU.replace("index", index)));
                    break;
                }
            }
        }
    }
    public void requestLoginToViewVideo() {
        keyword.webDriverWaitForElementPresent(Locator.HOME_VIDEO_LBL_LOGIN, 10);
        keyword.assertEqual(Locator.HOME_VIDEO_LBL_LOGIN, HOME_REQUEST_LOGIN);
        keyword.verifyElementDisplay(Locator.HOME_VIDEO_BTN_LOGIN);
    }
    public void tabLogin() {
        keyword.click(Locator.HOME_VIDEO_BTN_LOGIN);
    }


    public void clickBack() {

    }
    public void inputSearch(String key) {
        keyword.sendKeys(Locator.BANNER_BTN_CLOSE, key);
    }
    public void emptyResult() {

    }
    public void removeKeySearch() {

    }
    public void checkDisplayResult() {

    }
}

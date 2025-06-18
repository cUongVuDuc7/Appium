package pages;

import base.BasePage;
import locator.Locator;
import mySQL.PostGre;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.util.*;

import static constant.Constant.*;
import static constant.Query.*;


public class CategoryPage extends BasePage {
    public HomePage homePage;

    public CategoryPage() {
        logger.info("constructor Category page");
        homePage = new HomePage();
    }

    public void showHeader() {
        logger.info("showHeader");
        keyword.verifyElementDisplay(Locator.CATEGORY_LBL_LOGO);
        keyword.verifyElementDisplay(Locator.CATEGORY_BTN_SEARCH);
    }

    public void showPinAndDetail() {
        logger.info("showPinAndDetail");
        keyword.verifyElementDisplay(Locator.CATEGORY_BTN_PIN);
        keyword.verifyElementDisplay(Locator.CATEGORY_FIRST_BTN_DETAIL);
        keyword.verifyElementDisplay(Locator.CATEGORY_THUMBNAIL_IMG);
    }

    public Map<Integer, Map<String, Object>> getDbTypeCategories(PostGre postGre) {
        logger.info("get DB categories");
        ResultSet res = postGre.queryDb(TV_PLAY_CATEGORIES_TYPE_VISIBLE);
        return postGre.getResultDbThan1Rows(res);
    }

    public Map<String, String> getDbCategory(PostGre postGre, String name) {
        logger.info("get DB category " + name);
        ResultSet res  = postGre.queryDb(TV_PLAY_CATEGORIES_NAME.replace("key", name));
        return postGre.getResultDataBase(res);
    }

    public Map<Integer, Map<String, Object>> getDbTabCategoriseOrderByName(PostGre postGre, String idType) {
        logger.info("get DB category order by name");
        ResultSet res = postGre.queryDb(TV_PLAY_CATEGORIES_TYPE_ORDER_BY_NAME.replace("key", idType));
        return postGre.getResultDbThan1Rows(res);
    }

    public Map<String, String> getDbEventTv(PostGre postGre, String id) {
        logger.info("get Db EventTv");
        ResultSet res = postGre.queryDb(TV_PLAY_EVENT_TV_CATEGORY.replace("key", id));
        return postGre.getResultDataBase(res);
    }

    public Map<Integer, Map<String, Object>> getDbEventTvComingUp(PostGre postGre, String id) {
        logger.info("get Db EventTv");
        ResultSet res = postGre.queryDb(TV_PLAY_EVENT_TV_CATEGORY_NOT_START.replace("key", id));
        return postGre.getResultDbThan1Rows(res);
    }

    public Map<Integer, Map<String, Object>> getDbEventTvLive(PostGre postGre, String id) {
        logger.info("get Db EventTv");
        ResultSet res = postGre.queryDb(TV_PLAY_EVENT_TV_CATEGORY_LIVE.replace("key", id));
        return postGre.getResultDbThan1Rows(res);
    }

    public Map<String, String> getDbEventVideo(PostGre postGre, String id) {
        logger.info("get Db Event Video");
        ResultSet res = postGre.queryDb(TV_PLAY_EVENT_VIDEO_CATEGORY.replace("key", id));
        return postGre.getResultDataBase(res);
    }

    public String getIDCategory(Map<Integer, Map<String, Object>> db, String name) {
        logger.info("get DB id category");
        String id = "";
        for (int i = 0; i < db.size(); i++) {
            logger.info("db Name " + name + "   " + db.get(i).get("name"));
            if (db.get(i).get("name").equals(name)) {
                id = db.get(i).get("id").toString();
                break;
            }
        }
        logger.info("id " + id);
        return id;
    }

    public Map<String, String> getInformTabCategory() {
        logger.info("get Inform Tab Category");
        Map<String, String> informCategory = new HashMap<>();
        String afterScroll = "";
        String index = "";
        String beforeScroll = keyword.getPageSource();
        while (!beforeScroll.equals(afterScroll)) {
            List<WebElement> listEvents = keyword.getListElement(Locator.CATEGORY_THUMBNAIL_IMG);
            for (int i = 0; i < listEvents.size(); i++) {
                index = String.valueOf(i + 1);
                if (keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_LBL_TITLE.replace("index", index))) &&
                        keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_LBL_DESCRIPTION.replace("index", index)))) {
                    informCategory.put(keyword.getText(By.xpath(Locator.CATEGORY_LBL_TITLE.replace("index", index))),
                            keyword.getText(By.xpath(Locator.CATEGORY_LBL_DESCRIPTION.replace("index", index))));
                }
            }
            beforeScroll = keyword.getPageSource();
            keyword.scrollByCoordinates(0.2, -0.1);
            afterScroll = keyword.getPageSource();
        }
        return informCategory;
    }

    public void checkInformCategoriesShow(Map<String, String> informFe, Map<Integer, Map<String, Object>> informDb) {
        logger.info("check Inform Categories Show");
        Integer count = 0;
        Set<String> set = informFe.keySet();
        for (String key : set) {
            logger.info("compare db with Fe");
            keyword.assertEqualData(key, informDb.get(count).get("name").toString());
            keyword.assertEqualData(informFe.get(key), informDb.get(count).get("description").toString());
            count++;
        }
    }

    public void tabPinFirst() {
        logger.info("tabPinFirst");
        keyword.click(By.xpath(Locator.CATEGORY_BTN_PINS.replace("index", "1")));
    }

    public void tabFirstDetailsCategory() {
        logger.info("tabFirstDetailsCategory");
        keyword.click(Locator.CATEGORY_FIRST_BTN_DETAIL);
        keyword.webDriverWaitForElementPresent(Locator.CATEGORY_DETAIL_IMG, 10);
    }

    public void tabLeagueInEvent(String tab) {
        logger.info("tabLeague");
        keyword.click(By.xpath(Locator.CATEGORY_DETAIL_BTN_LEAGUE_INDEX.replace("index",tab)));
    }
    public void tabLeagueInVOD(String tab) {
        logger.info("tabLeague");
        keyword.click(By.xpath(Locator.CATEGORY_DETAIL_BTN_LEAGUE_VOD_INDEX.replace("index",tab)));
    }
    public void tabCategoryInEvent(String tab) {
        logger.info("tabCategoryInEvent");
        keyword.click(By.xpath(Locator.CATEGORY_DETAIL_BTN_CATEGORY_INDEX.replace("index",tab)));
    }
    public void tabDetailsCategory(String title) {
        logger.info("tabDetailsCategory");
        while (true) {
            if (keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_LBL_TITLE_TEXT.replace("key", title)))) {
                keyword.click(By.xpath(Locator.CATEGORY_LBL_TITLE_TEXT.replace("key", title)));
                break;
            }
            keyword.scrollByCoordinates(0.2, -0.1);
        }
        keyword.webDriverWaitForElementPresent(Locator.CATEGORY_DETAIL_IMG, 10);
    }

    public String getTitleCategory(String index) {
        logger.info("getTitleCategory");
        keyword.webDriverWaitForElementPresent(Locator.CATEGORY_THUMBNAIL_IMG, 10);
        return keyword.getText(By.xpath(Locator.CATEGORY_LBL_TITLE.replace("index", index)));
    }

    public String getDescriptionCategory(String index) {
        logger.info("getDescriptionCategory");
        return keyword.getText(By.xpath(Locator.CATEGORY_LBL_DESCRIPTION.replace("index", index)));
    }

    public void showTitleDetailCategory(String title) {
        logger.info("showTitleDetailCategory");
        keyword.verifyElementDisplay(By.xpath(Locator.CATEGORY_DETAILS_LBL_TITLE.replace("title", title)));
    }

    public void closeDetailCategory() {
        logger.info("closeDetailCategory");
//        keyword.webDriverWaitForElementPresent(Locator.CATEGORY_DETAIL_BTN_CLOSE,10);
        keyword.click(Locator.CATEGORY_DETAIL_BTN_CLOSE);
    }

    public void showTopDetailsCategory() {
        logger.info("showTopDetailsCategory");
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_CLOSE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_IMG);
    }

    public void showDetailsCategoryWhenScrollDown() {
        logger.info("showDetailsCategoryWhenScrollDown");
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_CLOSE);
//        keyword.verifyElementNotDisplayed(Locator.CATEGORY_DETAIL_IMG);
    }

    public void showBtnFollow() {
        logger.info("showBtnFollow");
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_LEAGUE_BTN_FOLLOW);
    }
    public String tabPin() {
        logger.info("tabPin");
        keyword.scrollByCoordinates(0.2, -0.5);
        boolean check = false;
        String tab = "";
        String index = "";
        while (true) {
            List<WebElement> listEvents = keyword.getListElement(Locator.CATEGORY_THUMBNAIL_IMG);
            for (int i = 0; i < listEvents.size(); i++) {
                index = String.valueOf(i + 1);
                if (keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_BTN_PINS.replace("index", index))) &&
                        keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_LBL_TITLES.replace("index", index)))) {
                    tab = keyword.getText(By.xpath(Locator.CATEGORY_LBL_TITLES.replace("index", index)));
                    keyword.click(By.xpath(Locator.CATEGORY_BTN_PINS.replace("index", index)));
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            }
            else {
                keyword.scrollByCoordinates(0.2, -0.3);
            }
        }
        return tab;
    }

    public void pinSuccess() {
        logger.info("pinSuccess");
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, CATEGORY_MESS_PIN_SUCCESS);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_LOGIN_SUCCESS, 10);
    }

    public String getFirstCategories() {
        logger.info("getFirstCategories");
        return keyword.getText(By.xpath(Locator.CATEGORY_LBL_TITLE.replace("index", "1")));
    }

    public void unPinSuccess() {
        logger.info("unPinSuccess");
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, CATEGORY_MESS_UNPIN_SUCCESS);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_LOGIN_SUCCESS, 10);
    }

    public List<String> getTabsCategoryDetail() {
        logger.info("getTabsCategoryDetail");
        List<String> list = new ArrayList<>();
        String index;
        boolean check = false;
        while (true) {
            if (keyword.verifyElementPresent(Locator.CATEGORY_DETAIL_TITLE_FIRST_TAB)) {
                list.add(keyword.getText(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE));
                keyword.click(By.xpath(Locator.CATEGORY_BTN_TABS.replace("index", "2")));
                check = true;
            }
            else {
                for (int i = 2; i < 6; i++) {
                    index = String.valueOf(i);
                    if (keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_DETAIL_TITLE_TABS.replace("index", index)))) {
                        list.add(keyword.getText(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE));
                        check = keyword.verifyPresentAndClick(By.xpath(Locator.CATEGORY_BTN_TABS.replace("index", String.valueOf(i + 1))));
                        break;
                    }
                }
            }
            if (!check) {
                break;
            }
        }
        Collections.sort(list);
        return list;
    }

    public List<String> getDbTabs(Map<Integer, Map<String, Object>> dbCategories) {
        logger.info("getDbTabs");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dbCategories.size(); i++) {
            list.add(dbCategories.get(i).get("name").toString());
        }
        list.add("Tất cả");
        Collections.sort(list);
        return list;
    }

    public void checkFocusTab() {
        logger.info("checkFocusTab");
        List<WebElement> listTab = keyword.getListElement(Locator.CATEGORY_BTN_TAB);
        keyword.assertEqualData(keyword.getText(By.xpath(Locator.CATEGORY_DETAIL_TITLE_TABS.replace("index", "1"))), "Tất cả");
        keyword.verifyElementNotDisplayed(By.xpath(Locator.CATEGORY_DETAIL_TITLE_TABS.replace("index", String.valueOf(listTab.size()))));
    }

    public String tabAnyCategoriesTab() {
        logger.info("tabAnyCategoriesTab");
        List<WebElement> listTab = keyword.getListElement(Locator.CATEGORY_BTN_TAB);
        int tab = keyword.randomNumberFromTo(2, listTab.size());
        keyword.click(By.xpath(Locator.CATEGORY_BTN_TABS.replace("index", String.valueOf(tab))));
        return keyword.getText(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE);
    }

    public void checkEmptyTab() {
        logger.info("checkEmptyTab");
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_LBL_EMPTY);
        keyword.assertEqual(Locator.CATEGORY_DETAIL_TITLE_EMPTY, CATEGORY_MESS_EMPTY_TAB);
        keyword.assertEqual(Locator.CATEGORY_DETAIL_SUB_TITLE_EMPTY, CATEGORY_MESS_SUB_EMPTY_TAB);
    }

    public void checkShowEvent(Map<String, String> dbEventTv) {
        logger.info("checkShowEvent");
        keyword.scrollByCoordinates(0.2, -0.05);
        if (!dbEventTv.isEmpty()) {
            keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_TITLE_EVENT);
        }
    }

    public void emptyEvent() {
        logger.info("emptyEvent");
        keyword.scrollByCoordinates(0.2, -0.05);
        keyword.verifyElementNotDisplayed(Locator.CATEGORY_DETAIL_TITLE_EVENT);
    }

    public void checkShowVideo(Map<String, String> dbEventVideo) {
        logger.info("checkShowVideo");
        keyword.scrollByCoordinates(0.3, -0.1);
        if (!dbEventVideo.isEmpty()) {
            keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_TITLE_EVENT_VIDEO);
        }
    }

    public void emptyVideo() {
        logger.info("get Inform Tab Category");
        keyword.scrollByCoordinates(0.2, -0.2);
        keyword.verifyElementNotDisplayed(Locator.CATEGORY_DETAIL_TITLE_EVENT_VIDEO);
    }

    public String findDbCategoryWithVideoOrEvent(PostGre postGre, String flag, Map<Integer, Map<String, Object>> dbCategories) {
        logger.info("findDbCategoryWithVideoOrEvent");
        String tabs = "";
        for (int i = 0; i < dbCategories.size(); i++) {
            System.out.println("findDbCategoryWithVideoOrEvent i " + i);
            Map<Integer, Map<String, Object>> dbTabCategories = getDbTabCategoriseOrderByName(postGre, dbCategories.get(i).get("id").toString());
            for (int j = 0; j < dbTabCategories.size(); j++) {
                System.out.println("findDbCategoryWithVideoOrEvent j " + j);
                Map<String, String> videos = getDbEventVideo(postGre, dbTabCategories.get(j).get("id").toString());
                Map<String, String> events = getDbEventTv(postGre, dbTabCategories.get(j).get("id").toString());
                switch (flag) {
                    case "emptyVideo":
                        if (videos.isEmpty() && !events.isEmpty()) {
                            tabs = dbCategories.get(i).get("name").toString() + "," + dbTabCategories.get(j).get("name").toString();
                        }
                        break;
                    case "emptyEvent":
                        if (events.isEmpty() && !videos.isEmpty()) {
                            tabs = dbCategories.get(i).get("name").toString() + "," + dbTabCategories.get(j).get("name").toString();
                        }
                        break;
                    case "than1Event":
                        if (events.size() > 1) {
                            tabs = dbCategories.get(i).get("name").toString() + "," + dbTabCategories.get(j).get("name").toString();
                        }
                        break;
                    case "emptyAll":
                        if (events.isEmpty() && videos.isEmpty()) {
                            tabs = dbCategories.get(i).get("name").toString() + "," + dbTabCategories.get(j).get("name").toString();
                        }
                        break;
                    case "showAll":
                        if (!events.isEmpty() && !videos.isEmpty()) {
                            tabs = dbCategories.get(i).get("name").toString() + "," + dbTabCategories.get(j).get("name").toString();
                        }
                        break;
                }
                if (!tabs.isEmpty()) {
                    System.out.println("tabs " + tabs);
                    return tabs;
                }
            }
        }
        return "";
    }

    public void tabToSubCategories(String name) {
        logger.info("tabToSubCategories");
        String index = "";
        while (true) {
            if (keyword.verifyElementPresent(Locator.CATEGORY_DETAIL_TITLE_FIRST_TAB)) {
                keyword.click(By.xpath(Locator.CATEGORY_BTN_TABS.replace("index", "2")));
            }
            else {
                for (int i = 2; i < 6; i++) {
                    index = String.valueOf(i);
                    if (keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_DETAIL_TITLE_TABS.replace("index", index)))) {
                        keyword.verifyPresentAndClick(By.xpath(Locator.CATEGORY_BTN_TABS.replace("index", String.valueOf(i + 1))));
                        break;
                    }
                }
            }
            if (keyword.getText(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE).equals(name)) {
                break;
            }
        }
    }

    public String getEventInDb(PostGre postGre, String flag, Map<Integer, Map<String, Object>> dbCategories) {
        logger.info("getEventIn Db " + flag);
        String tabAndName = "";
        for (int i = 0; i < dbCategories.size(); i++) {
            Map<Integer, Map<String, Object>> dbTabCategories = getDbTabCategoriseOrderByName(postGre, dbCategories.get(i).get("id").toString());
            for (int j = 0; j < dbTabCategories.size(); j++) {
                Map<Integer, Map<String, Object>> events = new HashMap<>();
                switch (flag) {
                    case "upComing":
                        events = getDbEventTvComingUp(postGre, dbTabCategories.get(j).get("id").toString());
                        break;
                    case "live":
                        events = getDbEventTvLive(postGre, dbTabCategories.get(j).get("id").toString());
                        break;
                }
                if (!events.isEmpty()) {
                    tabAndName = dbCategories.get(i).get("name").toString() + "," + dbTabCategories.get(j).get("name").toString() + "," +
                            events.get(0).get("name").toString();
                    break;
                }
            }
            if (!tabAndName.isEmpty()) {
                break;
            }
        }
        logger.info("tabAndName: " + tabAndName);
        return tabAndName;
    }

    public void checkEventLive(String flag) {
        logger.info("checkEventLive " + flag);
        if (flag.equals("live")) {
            keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_TXT_LIVE);
        }
        else {
            keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_LBL_UP_COMING);
        }
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_TITLE_NAME_EVENT);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_LEAGUE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_CATEGORY);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_IC_MORE);
    }

    public void loginByNoticeEvent() {
        logger.info("loginByNoticeEvent");
        if (keyword.verifyElementPresent(Locator.NOTICE_BTN_OK)) {
            keyword.click(Locator.NOTICE_BTN_OK);
        }
    }

    public void popupNotice(String flag) {
        logger.info("popupNotice");
        if(flag.equals("active")) {
            keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, CATEGORY_MESS_SUB_TITLE_NOTICE);
            keyword.assertEqual(Locator.NOTICE_BTN_OK, CATEGORY_MESS_BTN_NOTICE);
        }
        else {
            keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, CATEGORY_MESS_ACTIVE_NOTICE);
            keyword.assertEqual(Locator.NOTICE_BTN_OK, CATEGORY_MESS_BTN_INACTIVE_NOTICE);
        }
        keyword.assertEqual(Locator.CATEGORY_DETAIL_NOTICE_LBL_TITLE, CATEGORY_MESS_TITLE_NOTICE);
        keyword.verifyElementDisplay(Locator.NOTICE_BTN_CANCEL);
    }

    public void popUpLoginToViewEvent() {
        logger.info("popUpLoginToViewEvent");
        keyword.assertEqual(Locator.LOGIN_LBL_NOTICE_3_DEVICE, CATEGORY_NOTICE_TITLE_LOGIN);
        keyword.assertEqual(Locator.LOGOUT_TITLE_POP_UP, CATEGORY_NOTICE_SUB_TITLE_LOGIN);
        keyword.verifyElementDisplay(Locator.NOTICE_BTN_OK);
        keyword.verifyElementDisplay(Locator.NOTICE_BTN_CANCEL);
    }
    public void tabLoadMore() {
        logger.info("load More");
        keyword.verifyPresentAndClick(Locator.CATEGORY_DETAIL_BTN_LOAD_MORE);
    }
    public void tab3Dot(String tab) {
        logger.info("tab 3 Dot");
        if (tab == null) {
            keyword.click(Locator.CATEGORY_DETAIL_BTN_IC_MORE);
        }
        else {
            keyword.click(By.xpath(Locator.CATEGORY_DETAIL_BTN_IC_MORE_INDEX.replace("index",tab)));
        }
    }
    public String findEventComing() {
        logger.info("find EventComing");
        keyword.verifyPresentAndClick(Locator.CATEGORY_DETAIL_BTN_LOAD_MORE);
        String index = "";
        while (true) {
            List<WebElement> listView = keyword.getListElement(Locator.CATEGORY_DETAIL_LBL_VIEW);
            for (int i = 0; i < listView.size(); i++) {
                index = String.valueOf(i+1);
                if (keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_DETAIL_LBL_EVENT_COMING.replace("index", index))) &&
                        keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_DETAIL_BTN_LEAGUE_INDEX.replace("index", index)))) {
                    return index;
                }
            }
            keyword.scrollByCoordinates(0.25, -0.04);
        }
    }
    public void tabThumbImgEvent(String index) {
        keyword.click(By.xpath(Locator.CATEGORY_DETAIL_BTN_THUMB_IMG.replace("index", index)));
    }
    public void tabThumbImgVOD(String index) {
        keyword.click(By.xpath(Locator.CATEGORY_DETAIL_BTN_VOD_THUMB_IMG.replace("index", index)));
    }
    public void showVodLive() {
        keyword.webDriverWaitForElementPresent(Locator.CATEGORY_DETAIL_PLAYER_VOD,10);
//        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_PLAYER_LBL_LIVE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_IC_MORE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_PLAYER_BTN_SAVE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_PLAYER_LBL_TITLE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_PLAYER_BTN_CATEGORY);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_PLAYER_BTN_LEAGUE);
    }
    public void showDetail(String flag){
        if(flag.equals("category")){
            keyword.verifyElementPresent(Locator.CATEGORY_DETAIL_LIST_TAB);
        }
        showTopDetailsCategory();
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_LBL_VIEW);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_TITLE_EVENT);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_TITLE_NAME_EVENT);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_IC_MORE);
    }
    public void showListVod() {
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_PLAYER_BTN_LEAGUE);
        keyword.verifyElementDisplay(Locator.LATE_VIEW_LBL_NAME_VIDEO);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_VOD_LBL_TIME);
    }

    public void showBottomSheet() {
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_SET_NOTICE);
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_BTN_SHARE);
    }
    public void tabSetNotice() {
        keyword.click(Locator.CATEGORY_DETAIL_BTN_SET_NOTICE);
    }

    public void setDisableNotice() {
        keyword.sleep(0.3);
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, CATEGORY_NOTICE_SET_DISABLE);
    }
    public void setEnableNotice() {
        keyword.sleep(0.3);
        keyword.assertEqual(Locator.LOGIN_MESS_LOGIN_SUCCESS, CATEGORY_NOTICE_SET_ENABLE);
    }
}


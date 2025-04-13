package stepDefinitions;

import base.BasePage;
import cucumber.TestContext;
import enums.Context;
import helpers.LogHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import locator.Locator;
import mySQL.PostGre;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import pages.CategoryPage;
import pages.HomePage;
import java.util.List;
import java.util.Map;


public class CategorySteps extends BasePage {
    public HomePage homePage;
    public CategoryPage categoryPage;
    public PostGre postGre;
    private static Logger logger = LogHelper.getLogger();
    public TestContext testContext;
    public CategorySteps(TestContext testContext){
        logger.info("constructor home step ");
        this.testContext = testContext;
        homePage = testContext.getPageObjectManager().getHomePage();
        categoryPage = testContext.getPageObjectManager().getCategoryPage();
        postGre = testContext.getPageObjectManager().getPostGre();
    }
    @Before("@DetailCategory")
    public void setting(){
        keyword.setSetting(500);
    }
    @After
    public void closeConnect(){
        postGre.closeConnection();
    }
    @And("User view detail categories")
    public void userViewDetailsCategories() {
        postGre.setUpConnectTvPlayTv();
        Map<Integer, Map<String, Object>> dbCategories = categoryPage.getDbTypeCategories(postGre);
        int rand = keyword.randomNumber(dbCategories.size());
        categoryPage.tabDetailsCategory(dbCategories.get(rand).get("name").toString());
        testContext.getScenarioContext().setContext(Context.TITLE_CATEGORY, dbCategories.get(rand).get("name").toString());
    }
    @And("User view first detail categories")
    public void userViewFirstDetailsCategories() {
        testContext.getScenarioContext().setContext(Context.TITLE_CATEGORY, categoryPage.getTitleCategory("1"));
        testContext.getScenarioContext().setContext(Context.DES_CATEGORY, categoryPage.getDescriptionCategory("1"));
        categoryPage.tabFirstDetailsCategory();
    }
    @And("User refresh categories")
    public void userRefreshCategories() {
        keyword.scrollByCoordinates(0.3,3);
        keyword.untilJqueryIsDone(50L);
    }
    @And("User scroll down")
    public void userScrollDown() {
        keyword.scrollByCoordinates(0.2,-0.3);
    }
    @And("User tab pin first category")
    public void userTabPinFirst() {
        categoryPage.tabPinFirst();
    }
    @And("User tab pin")
    public void userTabPin() {
        String tabPin = categoryPage.tabPin();
        testContext.getScenarioContext().setContext(Context.TAB_PIN, tabPin);
    }
    @And("User close detail category")
    public void userCloseDetail() {
        categoryPage.closeDetailCategory();
    }
    @And("User tab any categories")
    public void userTabAnyCategories() {
        String tab = categoryPage.tabAnyCategoriesTab();
        testContext.getScenarioContext().setContext(Context.TITLE_TAB, tab);
    }
    @And("Get tab categories with content {string}")
    public void getTabCategories(String flag) {
        postGre.setUpConnectTvPlayTv();
        Map<Integer, Map<String, Object>> dbCategories = categoryPage.getDbTypeCategories(postGre);
        String categoryAndTab = categoryPage.findDbCategoryWithVideoOrEvent(postGre, flag, dbCategories);
        testContext.getScenarioContext().setContext(Context.TITLE_CATEGORY, categoryAndTab.split(",")[0]);
        testContext.getScenarioContext().setContext(Context.TITLE_TAB, categoryAndTab.split(",")[1]);
    }
    @And("Get event in tab category is {string}")
    public void getEventInTabCategories(String flag) {
        postGre.setUpConnectTvPlayTv();
        Map<Integer, Map<String, Object>> dbTypeCategories = categoryPage.getDbTypeCategories(postGre);
        String tabsAndName = categoryPage.getEventInDb(postGre, flag, dbTypeCategories);
        testContext.getScenarioContext().setContext(Context.TITLE_CATEGORY, tabsAndName.split(",")[0]);
        testContext.getScenarioContext().setContext(Context.TITLE_TAB, tabsAndName.split(",")[1]);
        testContext.getScenarioContext().setContext(Context.NAME_EVENT, tabsAndName.split(",")[2]);
    }
    @And("User tab to category")
    public void userTabCategory() {
        categoryPage.tabDetailsCategory(testContext.getScenarioContext().getContext(Context.TITLE_CATEGORY).toString());
    }
    @And("User tab to sub category")
    public void userTabSubCategory() {
        categoryPage.tabToSubCategories(testContext.getScenarioContext().getContext(Context.TITLE_TAB).toString());
    }
    @And("User tab thumbnail event VOD")
    public void userTabThumbNailEventVod() {
        String index = testContext.getScenarioContext().getValueContext(Context.INDEX);
        if (!index.isEmpty()) {
            categoryPage.tabThumbImgEvent(index);
        }
        else {
            categoryPage.tabThumbImgEvent("1");
        }
    }
    @And("User tab thumbnail VOD")
    public void userTabThumbNailVod() {
        String index = testContext.getScenarioContext().getValueContext(Context.INDEX);
        if (!index.isEmpty()) {
            categoryPage.tabThumbImgVOD(index);
        }
        else {
            categoryPage.tabThumbImgVOD("1");
        }
    }
    @And("User tab chip league")
    public void userTabChipLeague() {
        String index = testContext.getScenarioContext().getValueContext(Context.INDEX);
        if (!index.isEmpty()) {
            categoryPage.tabLeagueInEvent(index);
        }
        else {
            categoryPage.tabLeagueInEvent("1");
        }
    }
    @And("User tab chip league in vod")
    public void userTabChipLeagueVOD() {
        String index = testContext.getScenarioContext().getValueContext(Context.INDEX);
        if (!index.isEmpty()) {
            categoryPage.tabLeagueInVOD(index);
        }
        else {
            categoryPage.tabLeagueInVOD("1");
        }
    }
    @And("User tab chip category")
    public void userTabCategoryInEvent() {
        String index = testContext.getScenarioContext().getValueContext(Context.INDEX);
        if (!index.isEmpty()) {
            categoryPage.tabCategoryInEvent(index);
        }
        else {
            categoryPage.tabCategoryInEvent("1");
        }
    }
    @And("User find event coming")
    public void userFindEvenComing() {
        String index = categoryPage.findEventComing();
        testContext.getScenarioContext().setContext(Context.INDEX, index);
    }
    @And("User tab load more")
    public void userTabLoadMore() {
        categoryPage.tabLoadMore();
    }
    @And("User tab {string} notice")
    public void userTabRemindMe(String notice) {
        if (notice.equals("Đóng")) {
            keyword.click(Locator.NOTICE_BTN_CANCEL);
        } else {
            keyword.click(Locator.NOTICE_BTN_OK);
        }
    }
    @And("User tab 3 dot")
    public void userTab3Dot() {
        categoryPage.tab3Dot(testContext.getScenarioContext().getContext(Context.INDEX).toString());
    }
    @And("User tab set notice")
    public void userTabSetNotice() {
        categoryPage.tabSetNotice();
    }







    @Then("Show list tabs")
    public void showListTabs() {
        keyword.verifyElementPresent(Locator.CATEGORY_DETAIL_LIST_TAB);
    }
    @Then("Show thumb images")
    public void showThumbImages() {
        keyword.verifyElementPresent(By.xpath(Locator.CATEGORY_DETAIL_BTN_THUMB_IMG.replace("index", testContext.getScenarioContext().getContext(Context.INDEX).toString())));
    }
    @Then("Show details category screen")
    public void stepShowTitleDetailCategory() {
        categoryPage.showTitleDetailCategory(testContext.getScenarioContext().getContext(Context.TITLE_CATEGORY).toString());
        categoryPage.showTopDetailsCategory();
    }
    @Then("Show details league screen")
    public void stepShowDetailLeague() {
        categoryPage.showTopDetailsCategory();
        keyword.verifyElementDisplay(Locator.CATEGORY_DETAIL_LBL_VIEW);
    }
    @Then("Show details category after scroll down")
    public void showDetailCategoryAfterScrollDown() {
        categoryPage.showTitleDetailCategory(testContext.getScenarioContext().getContext(Context.TITLE_CATEGORY).toString());
        categoryPage.showDetailsCategoryWhenScrollDown();
    }
    @Then("Show btn follow")
    public void showBtnFollow() {
        categoryPage.showBtnFollow();
    }
    @Then("Show header")
    public void showHeader() {
        categoryPage.showHeader();
    }
    @Then("Show pin, detail icon, thumb")
    public void showDetailPinThumb() {
        categoryPage.showPinAndDetail();
    }
    @Then("Show all events in category screen")
    public void showAllEventsInCategoryScreen() {
        postGre.setUpConnectTvPlayTv();
        Map<Integer, Map<String, Object>> dbCategories = categoryPage.getDbTypeCategories(postGre);
        Map<String, String> informCategories = categoryPage.getInformTabCategory();
        categoryPage.checkInformCategoriesShow(informCategories, dbCategories);
    }
    @Then("User pin success")
    public void userPinSuccess() {
        categoryPage.pinSuccess();
    }
    @Then("check title tab pinned")
    public void checkTitleTabPined() {
        keyword.assertEqualData(categoryPage.getFirstCategories(), testContext.getScenarioContext().getContext(Context.TAB_PIN).toString());
    }
    @Then("User unPin success")
    public void userUnPinSuccess() {
        categoryPage.unPinSuccess();
    }
    @Then("Show all tabs in detail category")
    public void showAllTabInDetailCategory() {
        postGre.setUpConnectTvPlayTv();
        Map<String, String> dbCategory = categoryPage.getDbCategory(postGre, testContext.getScenarioContext().getContext(Context.TITLE_CATEGORY).toString());
        Map<Integer, Map<String, Object>> dbCategories = categoryPage.getDbTabCategoriseOrderByName(postGre, dbCategory.get("id"));
        List<String> listTabs = categoryPage.getTabsCategoryDetail();
        Assert.assertEquals(categoryPage.getDbTabs(dbCategories), listTabs);
    }
    @Then("Focus tab 'Tất cả'")
    public void focusTabAll() {
        categoryPage.checkFocusTab();
    }
    @Then("Empty tab")
    public void checkEmptyTab() {
        categoryPage.checkEmptyTab();
    }
    @Then("Empty event")
    public void checkEmptyEvent() {
        categoryPage.emptyEvent();
    }
    @Then("Empty video")
    public void checkEmptyVideo() {
        categoryPage.emptyVideo();
    }
    @Then("Show content in detail category")
    public void showContentInDetail() {
        postGre.setUpConnectTvPlayTv();
        Map<String, String> dbCategory = categoryPage.getDbCategory(postGre, testContext.getScenarioContext().getContext(Context.TITLE_CATEGORY).toString());
        Map<Integer, Map<String, Object>> dbCategories = categoryPage.getDbTabCategoriseOrderByName(postGre, dbCategory.get("id"));
        String id = categoryPage.getIDCategory(dbCategories, testContext.getScenarioContext().getContext(Context.TITLE_TAB).toString());
        Map<String, String> dbEventTv = categoryPage.getDbEventTv(postGre, id);
        Map<String, String> dbEventVideo = categoryPage.getDbEventVideo(postGre, id);
        categoryPage.checkShowEvent(dbEventTv);
        categoryPage.checkShowVideo(dbEventVideo);
    }
    @Then("Show load more button")
    public void showLoadMore() {
        keyword.verifyElementPresent(Locator.CATEGORY_DETAIL_BTN_LOAD_MORE);
    }
    @Then("Show event {string}")
    public void showEventComingSoon(String flag) {
        categoryPage.checkEventLive(flag);
    }
    @Then("Show popup notice {string}")
    public void showPopupNotice(String flag) {
        categoryPage.popupNotice(flag);
    }
    @Then("Show vod live")
    public void showVodLive() {
        categoryPage.showVodLive();
    }
    @Then("Show detail category after tab chip category")
    public void showDetailCategory() {
        categoryPage.showDetail("category");
    }
    @Then("Show detail league after tab chip league")
    public void showDetailLeague() {
        categoryPage.showDetail("league");
    }
    @Then("Show list video")
    public void showListVod() {
        categoryPage.showListVod();
    }
    @Then("Show bottom sheet")
    public void showBottomSheet() {
        categoryPage.showBottomSheet();
    }
    @Then("Set notice success")
    public void setNoticeSuccess() {
        categoryPage.setEnableNotice();
    }
    @Then("Set disable notice success")
    public void setDisableNoticeSuccess() {
        categoryPage.setDisableNotice();
    }
}

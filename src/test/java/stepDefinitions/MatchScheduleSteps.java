package stepDefinitions;

import base.BasePage;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import locator.Locator;
import mySQL.PostGre;
import pages.HomePage;
import pages.LoginPage;
import pages.MatchSchedulePage;

import java.util.Map;

public class MatchScheduleSteps extends BasePage {
    public LoginPage loginPage;
    public HomePage homePage;
    public MatchSchedulePage matchSchedulePage;
    public PostGre postGre;
    public TestContext testContext;
    public MatchScheduleSteps(TestContext testContext) {
        logger.info("constructor schedule step");
        this.testContext = testContext;
        homePage = testContext.getPageObjectManager().getHomePage();
        loginPage = testContext.getPageObjectManager().getLoginPage();
        matchSchedulePage = testContext.getPageObjectManager().matchSchedulePage();
        postGre = testContext.getPageObjectManager().getPostGre();
    }
    @And("User tab to day button")
    public void userTabToDayBtn() {
        matchSchedulePage.tabToDayBtn();
    }
    @And("User tab calender button")
    public void userTabCalenderBtn() {
        matchSchedulePage.tabCalenderBtn();
    }
    @And("User select random a day in calender")
    public void userSelectRandomADayInCalender() {
        String date = matchSchedulePage.selectARandomDayInDatePicker();
        testContext.getScenarioContext().setContext(Context.DATE_PICKER, date);
    }
    @And("User select random a day in line date {string}")
    public void userSelectRandomADayInLineDate(String flag) {
        matchSchedulePage.selectARandomDayInLineDate(flag);
    }
    @And("User swipe content tab")
    public void userSwipeTabContent() {
    }
    @And("User tab content tab")
    public void userTabAnyContentTab() {
        matchSchedulePage.selectContentTab();
    }
    @And("User tab category name")
    public void userTabAnyCategoryName() {
        String nameCategory = matchSchedulePage.selectCategory();
        testContext.getScenarioContext().setContext(Context.NAME_CATEGORY, nameCategory);
    }
    @And("User tab tournament name")
    public void userTabAnyTournamentName() {
        String nameTournament = matchSchedulePage.selectTournament();
        testContext.getScenarioContext().setContext(Context.NAME_TOURNAMENT, nameTournament);
    }



    @Then("Show current date in date picker")
    public void showCurrentDate() {
        matchSchedulePage.checkDisplayCurrentCalender();
    }
    @Then("Show correct date after select day in date picker")
    public void checkDateAfterSelectInDatePicker() {
        matchSchedulePage.checkDateAfterSelectInDatePicker(testContext.getScenarioContext().getContext(Context.DATE_PICKER).toString());
    }


    @Then("Show match schedule screen")
    public void showMatchScheduleScreen() {
        matchSchedulePage.checkMatchScheduleScreen();
    }
    @Then("Show all events in match schedule screen")
    public void showAllEventsInMatchScheduleScreen() {
        postGre.setUpConnectTvPlayTv();
        String dbInformEvents = matchSchedulePage.getDbInformEventLiveScore(postGre);
        Map<Integer, Map<String, String>> informEvents = matchSchedulePage.getInformEventLiveScore(dbInformEvents);
        matchSchedulePage.checkInformEventLiveScore(informEvents, dbInformEvents);
    }
    @Then("Check calender display same design")
    public void showLineCalender() {
        matchSchedulePage.checkShowCurrentDateInLineCalender();
    }
    @Then("Check display content tabs")
    public void showContentTabs() {
        postGre.setUpConnectTvPlayTv();
        String dbCategories = matchSchedulePage.getDbListCategory(postGre);
        String categories = matchSchedulePage.getListCategories(dbCategories);
        matchSchedulePage.checkListCategory(dbCategories, categories);
    }
    @Then("Check show category detail screen")
    public void showCategoryDetailScreen() {
        String nameCategory = matchSchedulePage.checkDisplayCategoryDetail();
        keyword.assertEqualData(testContext.getScenarioContext().getContext(Context.NAME_CATEGORY).toString(), nameCategory);
    }
    @Then("Check show tournament detail screen")
    public void showTournamentDetailScreen() {
        String nameTournament = matchSchedulePage.checkDisplayTournamentDetail();
        keyword.assertEqualData(testContext.getScenarioContext().getContext(Context.NAME_TOURNAMENT).toString(), nameTournament);
    }
    @Then("Check match time in match schedule")
    public void checkTimeLiveSource() {
        matchSchedulePage.checkTimeMatch();
    }

    @Then("Check match event than 2 team")
    public void checkEventThan2Team() {
        matchSchedulePage.matchEventThan2Team();
    }


}

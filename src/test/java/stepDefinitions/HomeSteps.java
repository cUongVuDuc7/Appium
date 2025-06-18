package stepDefinitions;

import base.BasePage;
import cucumber.TestContext;
import helpers.LogHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import pages.HomePage;

public class HomeSteps extends BasePage {
    public HomePage homePage;
    private static Logger logger = LogHelper.getLogger();
    public TestContext testContext;
    public HomeSteps(TestContext testContext){
        logger.info("constructor home step ");
        this.testContext = testContext;
        homePage = testContext.getPageObjectManager().getHomePage();
    }
    @Given("User cancel update version")
    public void userCancelUpdateVersion() {
        logger.info("user Cancel Update Version ");
        homePage.cancelNotice();
    }
    @Given("User tab home")
    public void userTabHomeBtn() {
        logger.info("user Tab Home");
        homePage.viewHomePage();
    }
    @Given("User tab live source")
    public void userTabLiveSourceBtn() {
        logger.info("user Tab Live source");
        homePage.viewLiveSource();
    }
    @Given("User tab home notification")
    public void userTabHomeNotice() {
        logger.info("user Tab home notice");
        homePage.viewHomeNotification();
    }
    @Given("User tab view category")
    public void userTabViewCategory() {
        logger.info("user Tab view category");
        homePage.viewCategory();
    }
    @And("User close banner")
    public void userCloseBanner() {
        logger.info("user close banner ");
        homePage.closeBanner();
    }
    @Given("User tab menu profile")
    public void userTabMenuProfile() {
        logger.info("user Tab Menu Profile");
        homePage.viewMenuProfile();
    }
    @And("User tab video DRM")
    public void userTabVideoDrm(){
        homePage.findVideoForYou();
    }
    @And("User tab login in video DRM")
    public void userTabLoginInVideoDrm(){
        homePage.tabLogin();
    }

    @Then("Show request login")
    public void showRequestLogin(){
        homePage.requestLoginToViewVideo();
    }




}

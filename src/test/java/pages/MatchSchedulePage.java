package pages;

import base.BasePage;
import locator.Locator;
import mySQL.PostGre;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.text.Normalizer;
import java.util.*;
import static constant.Constant.*;
import static constant.Query.*;
import static utilities.DateTime.getCurrentDateTime;

public class MatchSchedulePage extends BasePage {
    public HomePage homePage;
    public MatchSchedulePage(){
        logger.info("constructor MatchSchedule page");
        homePage = new HomePage();
    }
    public void swipeContentTab(){
        keyword.scrollFromElementTo(Locator.MATCH_SCHEDULE_BTN_TAB_CATEGORY,-300,0);
    }
    public String getDbListCategory(PostGre postGre){
        ResultSet res = postGre.queryDb(TV_PLAY_QUERY_ID_CATEGORY);
        Map<Integer, Map<String, Object >> db = postGre.getResultDbThan1Rows(res);
        String listDbCategories = "";
        for (int i = 0; i < db.size(); i++) {
            listDbCategories += db.get(i).get("name") + ",";
        }
        System.out.println("List data base Category: \n " + listDbCategories);
        return Normalizer.normalize("Tất cả," + listDbCategories.substring(0, listDbCategories.lastIndexOf(",")), Normalizer.Form.NFC);
    }
    public String getListCategories(String listDbCategories){
        String categories = "";
        String[] arrCategory = listDbCategories.split(",");
        while(true) {
            logger.info("while true");
            List<WebElement> weblist = keyword.getListElement(Locator.MATCH_SCHEDULE_BTN_TAB_CATEGORY);
            for (int i = 0; i < weblist.size(); i++) {
                if (!categories.contains(weblist.get(i).getText())) {
                    categories += weblist.get(i).getText() + ",";
                }
            }
            System.out.println("List Category: \n " + categories);
            keyword.scrollFromElementTo(Locator.MATCH_SCHEDULE_BTN_TAB_CATEGORY,-(keyword.getWidthDevice() / 3),0);
            if(weblist.get(weblist.size() - 1).getText().equals(arrCategory[arrCategory.length - 1])){
                break;
            }
        }
        System.out.println("List Category: \n " + categories);
        return Normalizer.normalize(categories.substring(0, categories.lastIndexOf(",")), Normalizer.Form.NFC);
    }
    public void checkListCategory(String listDbCategories, String listCategories) {
        keyword.assertEqualData(listDbCategories, listCategories);
    }
    public Map<Integer, Map<String, Object>> getDataBaseEventLiveScore(PostGre postGre, String dateMonth){
        String time = getCurrentDateTime("yyyy-") + dateMonth.split("\\.")[1] + "-" + dateMonth.split("\\.")[0];
        ResultSet res  = postGre.queryDb(TV_PLAY_QUERY_LIVE_SCORE.replace("date", time));
        return postGre.getResultDbThan1Rows(res);
    }
    public Map<String, String> getDataBaseCategory(PostGre postGre, String id){
        ResultSet res  = postGre.queryDb(TV_PLAY_QUERY_CATEGORY.replace("key", id));
        return postGre.getResultDataBase(res);
    }
    public String getDbInformEventLiveScore(PostGre postGre){
        logger.info("get DB Inform Event LiveScore");
        String dateMonth = keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_DATE_MONTH.replace("index", String.valueOf(4))));
        Map<Integer, Map<String, Object>> dbLiveScore = getDataBaseEventLiveScore(postGre, dateMonth);
        Map<String, String> dbCategory;
        String nameAndCategories = "";
        for (int i = 0; i < dbLiveScore.size(); i++) {
            dbCategory = getDataBaseCategory(postGre, dbLiveScore.get(i).get("category_id").toString());
            // get name của event và name của category
            nameAndCategories += dbLiveScore.get(i).get("name").toString() + "," + dbCategory.get("name") + ";";
        }
        logger.info("list name and categories:   " + nameAndCategories.substring(0, nameAndCategories.lastIndexOf(";")));
        return nameAndCategories.substring(0, nameAndCategories.lastIndexOf(";"));
    }
    public Map<String , String> getMapInformEventWith2Teams(String index){
        logger.info("get Inform Event LiveScore with 2 team");
        Map<String, String> inform = new HashMap<>();
        inform.put("team", keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_TEAM_HOME.replace("index",index)))
                + "-" + keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_TEAM_AWAY.replace("index",index))));
        inform.put("category", keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY.replace("index",index))));
        return inform;
    }
    public String getInformEventWith2Teams(String index){
        logger.info("get Inform Event LiveScore with 2 team");
         return keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_TEAM_HOME.replace("index",index)))
                + "-" + keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_TEAM_AWAY.replace("index",index)))
        + "," + keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY.replace("index",index)));
    }
    public Map<String, String> getMapInformEventThan2Teams(String index){
        logger.info("get Inform Event LiveScore than 2 teams");
        Map<String, String> inform = new HashMap<>();
        inform.put("tournament", keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOURNAMENT.replace("index",index))));
        inform.put("category", keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY.replace("index",index))));
        return inform;
    }
    public String getInformEventThan2Teams(String index){
        logger.info("get Inform Event LiveScore than 2 teams");
        return keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOURNAMENT.replace("index",index)))
        + "," + keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY.replace("index",index)));
    }
    public Map<Integer, Map<String, String>> getInformEventLiveScore(String dbInformEvents){
        logger.info("get Inform Event LiveScore");
        Map<Integer, Map<String, String>> informEvent = new HashMap<>();
        String[] arrDbInformEvents = dbInformEvents.split(";");
        String content = "";
        while (true) {
            List<WebElement> listEvents = keyword.getListElement(Locator.MATCH_SCHEDULE_LBL_TIME_MATCH_ID);
            for (int i = 0; i < listEvents.size(); i++) {
                String index = String.valueOf(i + 1);
                if (keyword.verifyElementPresent(By.xpath(Locator.MATCH_SCHEDULE_LBL_TEAM_HOME.replace("index", index)))){
                    if(!content.contains(getInformEventWith2Teams(index))) {
                        informEvent.put(i, getMapInformEventWith2Teams(index));
                        content += informEvent.get(i).get("team") + "," + informEvent.get(i).get("category") + ";";
                    }
                }
                else if (!keyword.verifyElementPresent(By.xpath(Locator.MATCH_SCHEDULE_LBL_TEAM_HOME.replace("index", index)))
                        && keyword.verifyElementPresent(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOURNAMENT.replace("index", index)))) {
                    if (!content.contains(getInformEventThan2Teams(index))) {
                        informEvent.put(i, getMapInformEventThan2Teams(index));
                        content += informEvent.get(i).get("tournament") + "," + informEvent.get(i).get("category") + ";";
                    }
                }
            }
            if(informEvent.get(informEvent.size() - 1).containsKey("team")) {
                if (arrDbInformEvents[arrDbInformEvents.length - 1].split(",")[0].equals(informEvent.get(informEvent.size() - 1).get("team"))) {
                    break;
                }
            }
            else if(arrDbInformEvents[arrDbInformEvents.length - 1].split(",")[0].equals(informEvent.get(informEvent.size() - 1).get("tournament"))) {
                break;
            }
            logger.info("arr Db Inform Events " + arrDbInformEvents);
            keyword.scrollFromElementTo(Locator.MATCH_SCHEDULE_BTN_CONTENTS,0, -10);
        }
        return informEvent;
    }
    public void checkInformEventLiveScore(Map<Integer, Map<String, String>> informEvent, String dbInform){
        logger.info("check Inform Event LiveScore");
        String[] arrDbInformEvents = dbInform.split(";");
        for (int i = 0; i < informEvent.size(); i++) {
            if(informEvent.get(i).containsKey("team")) {
                keyword.assertEqualData(informEvent.get(i).get("team"), arrDbInformEvents[i].split(",")[0]);
                keyword.assertEqualData(informEvent.get(i).get("category"), arrDbInformEvents[i].split(",")[1]);
            }
            else {
                keyword.assertEqualData(informEvent.get(i).get("tournament"), arrDbInformEvents[i].split(",")[0]);
                keyword.assertEqualData(informEvent.get(i).get("category"), arrDbInformEvents[i].split(",")[1]);
            }
        }
    }
    public void checkMatchScheduleScreen(){
        logger.info("check Profile Screen");
        keyword.untilJqueryIsDone(10L);
        homePage.verifyHomeScreen();
        keyword.assertEqual(Locator.INFORM_ON_LIVE_TV_LBL_TITLE, TITLE_MATCH_SCHEDULE);
        keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_BTN_TODAY);
        keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_BTN_CALENDAR);
        keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_BTN_CATEGORY);
        keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_BTN_ALL);
        keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_BTN_TAB_CATEGORY);
        if(keyword.verifyElementPresent(Locator.MATCH_SCHEDULE_LBL_EMPTY_MATCH)){
            keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_LBL_TITLE_EMPTY_MATCH);
        }
        else {
            keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_LBL_LIST_MATCH);
            List<WebElement> listMatch =  getListMatchShow();
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_LBL_TIME_MATCH);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_LBL_NAME_TOURNAMENT);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_LBL_TEAM_HOME);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_LBL_TEAM_AWAY);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_IMG_TEAM_HOME);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_IMG_TEAM_AWAY);
            checkDisplayElementsInAMatchSchedule(listMatch.size(), Locator.MATCH_SCHEDULE_LBL_RESULT);
        }
    }
    public List<WebElement> getListMatchShow(){
        return keyword.getListElement(By.xpath(Locator.MATCH_SCHEDULE_LBL_MATCH));
    }
    public void checkDisplayElementsInAMatchSchedule(int numberMatch, String elements){
        logger.info("check Display Elements In A Match Schedule");
        for (int i = 0; i < numberMatch; i++) {
            String element = elements.replace("index", String.valueOf(i + 1));
            keyword.verifyElementDisplay(By.xpath(element));
        }
    }
    public void checkDateSelectedInMidLine(String day){
        List<WebElement> date = keyword.getListElement(Locator.MATCH_SCHEDULE_LBL_LINE_DATE);
        for (int i = 0; i < date.size(); i++) {
            if(i == 3) {
                String dayVN = keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_DAY.replace("index", String.valueOf(i+1))));
                String dateMonth = keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_DATE_MONTH.replace("index", String.valueOf(i+1))));
                keyword.assertEqualData(dayVN, changeToDayVn(day.split("\n")[0]));
                keyword.assertEqualData(dateMonth, day.split("\n")[1]);
                break;
            }
        }
    }
    public void checkShowCurrentDateInLineCalender(){
        logger.info("check Display Line Calender");
        String day = getCurrentDateTime("E\ndd.MM");
        checkDateSelectedInMidLine(day);
    }
    public String selectARandomDayInDatePicker(){
        keyword.webDriverWaitForElementPresent(Locator.MATCH_SCHEDULE_CALENDER_LBL_VIEW_MONTH,10);
        keyword.click(By.xpath(Locator.MATCH_SCHEDULE_CALENDER_BTN_DATE.replace("day",String.valueOf(keyword.randomNumber(30)))));
        return keyword.getText(Locator.DATE_PICKER_LBL_DATE);
    }
    public void checkDateAfterSelectInDatePicker(String date){ // Tue, Jan 21
        logger.info("check Date After Select In Date Picker ");
        String dayVN = keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_DAY.replace("index", "4")));
        keyword.assertEqualData(dayVN, changeToDayVn(date.split(",")[0]));
    }
    public String changeToDayVn(String dayUs){
        String[] listDay = DAY_IN_US.split(",");
        String dayVn = "";
        for (int i = 0; i < 7; i++) {
            if(listDay[i].equals(dayUs)){
                dayVn = DAY_IN_VN.split(",")[i];
                break;
            }
        }
        logger.info("dayUs: " + dayUs + "   dayVn :" + dayVn);
        return dayVn;
    }
    public void tabToDayBtn(){
        keyword.click(Locator.MATCH_SCHEDULE_BTN_TODAY);
    }
    public void tabCalenderBtn(){
        keyword.click(Locator.MATCH_SCHEDULE_BTN_CALENDAR);
    }
    public void selectARandomDayInLineDate(String flag) {
        if (flag.equals("not today")) {
            String day = getCurrentDateTime("E\ndd.MM");
            while(true){
                String index = String.valueOf(keyword.randomNumber(7));
                if(!keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_DATE_MONTH.replace("index", index))).equals(day.split("\n")[1])){
                    keyword.click(By.xpath(Locator.MATCH_SCHEDULE_LBL_DATE_MONTH.replace("index", index)));
                    break;
                }
            }
        }
        else {
            keyword.click(By.xpath(Locator.MATCH_SCHEDULE_LBL_DAY.replace("index", String.valueOf(keyword.randomNumber(7)))));
        }
    }
    public void checkDisplayCurrentCalender() {
        String day = getCurrentDateTime("yyyy\nE, MMM dd");
        keyword.assertEqual(Locator.DATE_PICKER_LBL_YEAR, day.split("\n")[0]);
        keyword.assertEqual(Locator.DATE_PICKER_LBL_DATE, day.split("\n")[1]);
    }
    public void selectContentTab(){
        List<WebElement> weblist = keyword.getListElement(Locator.MATCH_SCHEDULE_BTN_TAB_CATEGORY);
        int randEle = keyword.randomNumber(weblist.size());
        weblist.get(randEle).click();
    }
    public String selectCategory(){
        List<WebElement> listMatch =  getListMatchShow();
        String rand = String.valueOf(keyword.randomNumber(listMatch.size()));
        String nameCategory = keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY.replace("index", rand)));
        keyword.click(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_CATEGORY.replace("index", rand)));
        return nameCategory;
    }
    public String selectTournament(){
        List<WebElement> listMatch =  getListMatchShow();
        String rand = String.valueOf(keyword.randomNumber(listMatch.size()));
        String nameTournament = keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOURNAMENT.replace("index", rand)));
        keyword.click(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOURNAMENT.replace("index", rand)));
        return nameTournament;
    }

    public String checkDisplayCategoryDetail() {
        keyword.webDriverWaitForElementPresent(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE,10);
        return keyword.getText(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE);
    }
    public String checkDisplayTournamentDetail() {
        keyword.webDriverWaitForElementPresent(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE,10);
        keyword.verifyElementDisplay(Locator.MATCH_SCHEDULE_DETAIL_BTN_FOLLOWING);
        return keyword.getText(Locator.MATCH_SCHEDULE_DETAIL_LBL_TITLE);
    }
    public void checkTimeMatch() {
        List<WebElement> listMatch =  getListMatchShow();
        String time = "";
        for (int i = 0; i < listMatch.size(); i++) {
            time += keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_TIME_MATCH.replace("index", String.valueOf(i+1)))) + ",";
        }
        System.out.println(time);
    }
    public void matchEventThan2Team() {
        StringBuilder inform = new StringBuilder();
        if(keyword.verifyElementPresent(Locator.MATCH_SCHEDULE_BTN_SHOW)){
            List<WebElement> listMatchThan2Team =  keyword.getListElement(Locator.MATCH_SCHEDULE_BTN_SHOW);
            for (int i = 0; i < listMatchThan2Team.size(); i++) {
                listMatchThan2Team.get(i).click();
                inform.append(keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOP1.replace("index", String.valueOf(i+1)))));
                inform.append(keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOP2.replace("index", String.valueOf(i+1)))));
                inform.append(keyword.getText(By.xpath(Locator.MATCH_SCHEDULE_LBL_NAME_TOP3.replace("index", String.valueOf(i+1)))));
            }
        }
    }





}

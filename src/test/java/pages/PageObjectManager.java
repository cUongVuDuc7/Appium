package pages;

import mySQL.MonGoDb;
import mySQL.MySQL;
import mySQL.PostGre;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;

public class PageObjectManager {
    private ForgotPasswordPage forgotPassWordPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private ProfilePage profilePage;
    private MatchSchedulePage matchSchedulePage;
    private CategoryPage categoryPage;
    private PostGre postGre;
    private MonGoDb monGoDb;
    private MySQL mySQL;

    public PostGre getPostGre() {
        return (postGre == null) ? postGre = new PostGre() : postGre;
    }
    public MonGoDb getMonGoDb() {
        return (monGoDb == null) ? monGoDb = new MonGoDb() : monGoDb;
    }
    public MySQL getMySQL() {
        return (mySQL == null) ? mySQL = new MySQL() : mySQL;
    }
    public HomePage getHomePage(){
        return (homePage == null) ? homePage = new HomePage() : homePage;
    }

    public ForgotPasswordPage getForgotPassWordPage() {
        return (forgotPassWordPage == null) ? forgotPassWordPage = new ForgotPasswordPage() : forgotPassWordPage;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
    }

    public ProfilePage getProfilePage() {
        return (profilePage == null) ? profilePage = new ProfilePage() : profilePage;
    }
    public MatchSchedulePage matchSchedulePage() {
        return (matchSchedulePage == null) ? matchSchedulePage = new MatchSchedulePage() : matchSchedulePage;
    }

    public CategoryPage getCategoryPage() {
        return (categoryPage == null) ? categoryPage = new CategoryPage() : categoryPage;
    }
}

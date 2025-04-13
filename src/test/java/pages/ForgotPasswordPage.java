package pages;

import base.BasePage;
import locator.Locator;
import static constant.Constant.*;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(){
        logger.info("constructor ForgotPass page");
    }
    public void showChangePassWordScreen(){
        keyword.webDriverWaitForElementPresent(Locator.FORGOT_PASSWORD_LBL_TITLE_FORGOT,10);
        keyword.verifyElementDisplay(Locator.CHANGE_PASSWORD_TXT_CURRENT);
        keyword.verifyElementDisplay(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN);
        keyword.verifyElementDisplay(Locator.LOGIN_BTN_CONTINUE);
    }
    public void clickForgot(){
        keyword.webDriverWaitForElementPresent(Locator.FORGOT_PASSWORD_BTN_FORGOT,10);
        keyword.click(Locator.FORGOT_PASSWORD_BTN_FORGOT);
    }
    public void inputNewPass(String pass){
        logger.info("input current password");
        keyword.clearTextAndSendKey(Locator.CHANGE_PASSWORD_TXT_NEW_PASS, pass);
    }
    public void reInputNewPass(String pass){
        logger.info("input current password");
        keyword.clearTextAndSendKey(Locator.CHANGE_PASSWORD_TXT_NEW_PASS_AGAIN, pass);
    }
    public void passWordLess6Char(){
        keyword.assertEqual(Locator.FORGOT_PASSWORD_LBL_ERROR_1,MESSAGE_ERROR_LESS_6_CHARACTER);
    }
    public void reInputPassWordLess6Char(){
        keyword.assertEqual(Locator.FORGOT_PASSWORD_LBL_ERROR_2,MESSAGE_ERROR_LESS_6_CHARACTER);
    }
    public void clickShowPass1(){
        keyword.click(Locator.FORGOT_PASSWORD_BTN_SHOW_PASS_1);
    }
    public void clickShowPass2(){
        keyword.click(Locator.FORGOT_PASSWORD_BTN_SHOW_PASS_2);
    }
    public void errorNotSamePassword(){
        keyword.assertEqual(Locator.FORGOT_PASSWORD_LBL_ERROR_SAME_PASS, FORGOT_PASS_ERROR_SAME_PASSWORD);
    }

}

package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.MyAccountPage;
import utils.TestNGListener;

import static utils.PropertiesReader.getProperty;

@Listeners(TestNGListener.class)

public class LoginTests extends AppManager {

    SoftAssert softAssert = new SoftAssert();


    @Test(groups = {"smoke", "regress", "user", "positive"})
    public void loginPositiveTest() {
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());

        homePage.clickBtnLogin();
        /*
        homePage.clickBtnGotIt();
        homePage.clickBtnLogin();
        homePage.clickBtnLoginWithEmail();
        homePage.clickInputFieldEmail();
        homePage.clickInputFieldPassword();
        homePage.typeFieldPassword(user);
        homePage.clickBtnLogin2();
        homePage.clickBtnGotIt();

        softAssert.assertTrue(homePage
                .validateUserName(), "vsaitprojekt");
        softAssert.assertAll();

         */
    }

    @Test
    public void loginNegative_AllFieldsEmpty_Test() {
        new HomePage(getDriver()).clickBtnLogin();
        MyAccountPage loginPage = new MyAccountPage(getDriver());
        loginPage.clickBtnLogin();
        Assert.assertEquals(loginPage.closeAlert(), "Wrong email or password");
    }

    @Test
    public void loginNegative_EmptyFieldEmail_Test() {
        User user = User.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        MyAccountPage loginPage = new MyAccountPage(getDriver());
        loginPage.clickBtnLogin();
        Assert.assertEquals(loginPage.closeAlert(), "Wrong email or password");
    }

    @Test
    public void loginNegative_EmptyFieldPassword_Test() {
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password("")
                .build();
        new HomePage(getDriver()).clickBtnLogin();
        MyAccountPage loginPage = new MyAccountPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        loginPage.clickBtnLogin();
        Assert.assertEquals(loginPage.closeAlert(), "Wrong email or password");
    }

    @Test
    public void loginNegative_WrongEmail_Test() {
        User user = User.builder()
                .username("asdert123@rty.com")
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        MyAccountPage loginPage = new MyAccountPage(getDriver());

        loginPage.clickBtnLogin();
        Assert.assertEquals(loginPage.closeAlert(), "Wrong email or password");
    }

    @Test
    public void loginNegative_WrongPassword_Test() {
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password("A123vbgt!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        MyAccountPage loginPage = new MyAccountPage(getDriver());

        loginPage.clickBtnLogin();
        Assert.assertEquals(loginPage.closeAlert(), "Wrong email or password");
    }
//    @Test
//    public void testMethod(){
//        new HomePage(getDriver()).method();
//    }
//
//    @Test
//    public void testAjaxMethod(){
//        new HomePage(getDriver()).ajaxMethod();
//    }
}

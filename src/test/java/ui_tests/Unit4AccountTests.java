package ui_tests;

import dto.User;
import manager.AppManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestNGListener;

import static utils.PropertiesReader.getProperty;

@Listeners(TestNGListener.class)

public class Unit4AccountTests extends AppManager {

    @Test(groups = {"smoke", "regress", "user", "positive"})
    public void loginPositiveTest() {

        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnGotIt();
        homePage.clickBtnLogin();
        homePage.clickBtnLoginWithEmail();
        homePage.clickInputFieldEmail();
        homePage.typeFieldEmail(user);
        homePage.clickInputFieldPassword();
        homePage.typeFieldPassword(user);
        homePage.clickBtnLoginInLoginForm();
        driver.navigate().refresh();
        Assert.assertEquals(homePage.closeAlert(), "Wrong email or password");

    }



}
package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MyAccountPage;

import static utils.PropertiesReader.getProperty;

public class Unit2PhotoTest extends AppManager {

    @Test
    public void displayNameUnderPhoto_Test() {
        HomePage homePage = loginAsDefaultUser();
        homePage.clickOptionMyAccount();

        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        String expectedUsername = getProperty("base.properties", "username");
        Assert.assertEquals(myAccountPage.getProfileDisplayName(), expectedUsername);
    }
}
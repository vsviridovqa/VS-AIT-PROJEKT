package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.MyAccountPage;

import java.time.LocalDate;

import static utils.PropertiesReader.getProperty;


public class Unit5DisplayInfoTest extends AppManager {

    @Test
    public void displayInfoUpdate_Test() throws InterruptedException {
        HomePage homePage = loginAsDefaultUser();
        homePage.clickOptionMyAccount();
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        myAccountPage.clickFieldNewDisplayName();
        String newDisplayName = String.valueOf(System.currentTimeMillis() / 1000);
        myAccountPage.typeFieldNewDisplayName(newDisplayName);
        myAccountPage.clickbtnUpdateInfo();
        Assert.assertEquals(myAccountPage.getProfileDisplayName(), newDisplayName);
        BasePage.pause(5);
        String expectedUsername = getProperty("base.properties", "username");
        myAccountPage.clickFieldNewDisplayName();
        myAccountPage.typeFieldNewDisplayName(expectedUsername);
        myAccountPage.clickbtnUpdateInfo();
    }
}

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

/*
27 loginPositiveTest()
51 loginNegative_AllFieldsEmpty_Test()
64 loginNegative_EmptyFieldEmail_Test()
81 loginNegative_EmptyFieldPassword_Test()
98 loginNegative_WrongEmail_Test()
116 loginNegative_WrongPassword_Test()
*/

public class LoginTests extends AppManager {

    @Test(groups = {"smoke", "regress", "user", "positive"})
    public void loginPositiveTest() {
        HomePage homePage = loginAsDefaultUser();
        homePage.clickOptionMyAccount();
        Assert.assertTrue(
                homePage.validateURL(),
                "URL страницы не отображается или не совпадает с ожидаемым"
        );
    }

    @Test(groups = {"smoke", "regress", "user"})
    public void loginNegative_AllFieldsEmpty_Test() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnGotIt();
        homePage.clickBtnLogin();
        homePage.clickBtnLoginWithEmail();
        homePage.clickInputFieldEmail();
        homePage.clickInputFieldPassword();
        homePage.clickBtnLoginInLoginForm();
        Assert.assertEquals(homePage.getEmailErrorText(), "Email cannot be blank");
        Assert.assertEquals(homePage.getPasswordErrorText(), "Make sure you enter a password.");
    }

    @Test
    public void loginNegative_EmptyFieldEmail_Test() {
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnGotIt();
        homePage.clickBtnLogin();
        homePage.clickBtnLoginWithEmail();
        homePage.clickInputFieldEmail();
        homePage.clickInputFieldPassword();
        homePage.typeFieldPassword(user);
        homePage.clickBtnLoginInLoginForm();
        Assert.assertEquals(homePage.getEmailErrorText(), "Email cannot be blank");
    }

    @Test
    public void loginNegative_EmptyFieldPassword_Test() {
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
        homePage.clickBtnLoginInLoginForm();
        Assert.assertEquals(homePage.getPasswordErrorText(), "Make sure you enter a password.");
    }

    @Test
    public void loginNegative_WrongEmail_Test() {
        User user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnGotIt();
        homePage.clickBtnLogin();
        homePage.clickBtnLoginWithEmail();
        homePage.clickInputFieldEmail();
        driver.findElement(By.cssSelector("input[id^='input_input_emailInput']"))
                .sendKeys("wrong@test.com");
        homePage.typeFieldPassword(user);
        homePage.clickBtnLoginInLoginForm();
        Assert.assertEquals(homePage.getEmailStrongErrorText(), "This email doesn't match any account. Try again.");
    }

    @Test
    public void loginNegative_WrongPassword_Test() {
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
        driver.findElement(By.cssSelector("input[id^='input_input_passwordInput']"))
                .sendKeys("wrongPassword");
        homePage.clickBtnLoginInLoginForm();
        Assert.assertEquals(homePage.getPasswordStrongErrorText(), "Wrong email or password");
    }

}
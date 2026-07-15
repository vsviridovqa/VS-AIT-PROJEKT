package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

// https://www.my-ait.com/
public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "button[data-testid='handle-button']")
    WebElement btnLogIn;

    @FindBy(css = "[data-testid='switchToEmailLink'] button")
    WebElement btnLoginWithEmail;

    @FindBy(css = "input[id^='input_input_emailInput']")
    WebElement inputFieldEmail;

    @FindBy(css = "input[id^='input_input_passwordInput']")
    WebElement inputFieldPassword;

    @FindBy(css = "button[data-testid='buttonElement']")
    WebElement btnLogIn2;

    @FindBy(css = "//*[text()='Got it!']")
    WebElement btnGotIt;

    @FindBy(xpath = "//div[@data-testid='handle-button']//div[contains(@class, 'FoC6OJ') and contains(text(), 'vsaitprojekt')]")
    WebElement userName;


    public void clickBtnLogin(){
        btnLogIn.click();
    }

    public void clickBtnLoginWithEmail(){
        btnLoginWithEmail.click();
    }

    public void clickInputFieldEmail(){
        inputFieldEmail.click();
    }

    public void clickInputFieldPassword(){
        inputFieldPassword.click();
    }

    public void clickBtnLogin2(){
        btnLogIn2.click();
    }

    public void clickBtnGotIt(){
        btnGotIt.click();
    }

    public void typeFieldEmail(User user) {
        inputFieldEmail.sendKeys(user.getUsername());
    }

    public void typeFieldPassword(User user) {
        inputFieldPassword.sendKeys(user.getPassword());
    }


    public boolean validateUserName() {
        return isTextInElementPresent(userName, "vsaitprojekt");
    }




    public void method(){
        WebElement login = driver.findElement(By
                .xpath("//a[text()='LOGIN']"));
        WebElement inputEmail = driver.findElement(By
                .xpath("//input[@name='email']"));
        login.click();
        inputEmail.sendKeys("rtyfug@hju.cr");
    }

    public void ajaxMethod(){
        btnLogIn.click();
        // inputEmail.sendKeys("etryfg@adse.vbn");
    }
}

package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@name='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text()='Login']")
    WebElement btnLogin;
    @FindBy(xpath = "//button[text()='Registration']")
    WebElement btnRegistration;



    public void clickBtnLogin(){
        btnLogin.click();
    }

    public void clickBtnRegistration(){
        btnRegistration.click();
    }
}

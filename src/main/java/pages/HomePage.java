package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class HomePage extends BasePage {

    private By btnGotItBy = By.id("cst-cookies-submit");

    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }


    @FindBy(css = "button[data-testid='handle-button']")
    WebElement btnLogIn;

    @FindBy(css = "[data-testid='handle-button']")
    WebElement btnHandleButton;

    @FindBy(css = "[data-testid='switchToEmailLink'] button")
    WebElement btnLoginWithEmail;

    @FindBy(css = "input[id^='input_input_emailInput']")
    WebElement inputFieldEmail;

    @FindBy(css = "input[id^='input_input_passwordInput']")
    WebElement inputFieldPassword;

    @FindBy(css = "[data-testid='submit'] button")
    WebElement btnLoginInLoginForm;

    // @FindBy(id = "cst-cookies-submit")
    // WebElement btnGotIt;

    @FindBy(xpath = "//*[@data-testid='custom-menu']//*[text()='My Account']")
    WebElement optionMyAccount;

    @FindBy(xpath = "//*[text()='Email cannot be blank']")
    WebElement emailErrorText;

    @FindBy(xpath = "//*[contains(text(), \"This email doesn't match any account. Try again.\")]")
    WebElement emailStrongErrorText;

    @FindBy(xpath = "//*[text()='Make sure you enter a password.']")
    WebElement passwordErrorText;

    @FindBy(xpath = "//*[text()='Wrong email or password']")
    WebElement passwordStrongErrorText;

    @FindBy(css = "a[data-testid='linkElement-2'][href*='/account/my-groups']")
    WebElement linkMyGroups;

    public void clickLinkMyGroups() {
        clickWait(linkMyGroups);
    }

    public void clickBtnLogin() {
        clickWait(btnLogIn);
    }

    public void clickBtnLoginWithEmail() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(btnLoginWithEmail))
                    .click();
        } catch (Exception e) {
            logger.warn("Обычный клик не прошёл (перехвачен другим элементом), кликаем через JS");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnLoginWithEmail);
        }
    }

    public void clickOptionMyAccount() {
        clickWait(optionMyAccount);
    }

    public void clickInputFieldEmail() {
        clickWait(inputFieldEmail);
    }

    public void clickInputFieldPassword() {
        clickWait(inputFieldPassword);
    }

    public void clickBtnLoginInLoginForm() {
        btnLoginInLoginForm.click();
    }

    public void clickBtnGotIt() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(btnGotItBy))
                    .click();
        } catch (Exception ignored) {
        }
    }


    public void clickBtnHandleButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.pjvsBR")));
    }

    public void typeFieldEmail(User user) {
        inputFieldEmail.sendKeys(user.getUsername());
    }

    public void typeFieldPassword(User user) {
        inputFieldPassword.sendKeys(user.getPassword());
    }

    public boolean validateURL() {
        try {
            String expectedUrl = PropertiesReader.getProperty("base.properties", "myaccount");
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            logger.error("Page source at failure: {}", driver.getPageSource());
            return false;
        }
    }

    public boolean validateMyGroupsURL() {
        try {
            String expectedUrl = PropertiesReader.getProperty("base.properties", "mygroups");
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            logger.error("Page source at failure: {}", driver.getPageSource());
            return false;
        }
    }

    public String getEmailErrorText() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(emailErrorText))
                .getText();
    }

    public String getEmailStrongErrorText() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(emailStrongErrorText))
                .getText();
    }

    public String getPasswordErrorText() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(passwordErrorText))
                .getText();
    }

    public String getPasswordStrongErrorText() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(passwordStrongErrorText))
                .getText();
    }


}

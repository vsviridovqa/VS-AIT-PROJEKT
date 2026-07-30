package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(css = "[data-hook='MyAccount-saveAccountButton']")
    private WebElement btnUpdateInfo;

    @FindBy(xpath = "//div[@data-hook='ProfileCard-memberName']//h1")
    WebElement profileDisplayName;

    @FindBy(css = "#display-name-id")
    private WebElement fieldNewDisplayName;

    @FindBy(css = "button[data-hook='clear-button']")
    private WebElement fieldNewDisplayNameClear;

    public void clickbtnUpdateInfo() {
        clickWait(btnUpdateInfo);
        pause(2000);
    }

    public void clickFieldNewDisplayName() {
        clickWait(fieldNewDisplayName);
    }

    // public void clickFieldNewDisplayNameClear() {
    //    clickWait(fieldNewDisplayNameClear);
   // }

    public void typeFieldNewDisplayName(String text) {
        fieldNewDisplayName.click();
        fieldNewDisplayName.sendKeys(Keys.CONTROL + "a");
        fieldNewDisplayName.sendKeys(Keys.BACK_SPACE);
        fieldNewDisplayName.sendKeys(text);

        // 4. Нажимаем TAB, чтобы снять фокус с поля (blur event).
        // Именно это заставляет Wix обновить State и активировать кнопку "Update Info"
        fieldNewDisplayName.sendKeys(Keys.TAB);
    }


    public String getProfileDisplayName() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(profileDisplayName))
                .getText();
    }

}
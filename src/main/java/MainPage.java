import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {


    private final WebDriver driver;
    public static final String MAIN_PAGE_TITLE = "LMT elektroniskā darbalaika uzskaite – EDLUS";
    public static final String FUNKCIJAS_LINK = "//*[@id=\"menu-biznesam-1\"]/li[4]/a";
    public static final String PIETEIKTIES_KONSULTACIJAI_BTN = "//a[@id='kas-ir-edlus-pieteikties']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getMainPageTitle() {
        return MAIN_PAGE_TITLE;
    }

    public void clickOnFunkcijasLink() {
        WebElement funkcijasLink = waitForElementByXPath(FUNKCIJAS_LINK);
        funkcijasLink.click();
    }

    public void clickOnPieteiktiesKonsultacijaiButton() {
        WebElement pieteiktiesKonsultacijaiButton = waitForElementByXPath(PIETEIKTIES_KONSULTACIJAI_BTN);
        pieteiktiesKonsultacijaiButton.click();
    }

    public WebElement PieteiktiesBtn() {
        return waitForElementByXPath(PIETEIKTIES_KONSULTACIJAI_BTN);
    }

    public WebElement waitForElementByXPath(String xpath) {
        By elementLocator = By.xpath(xpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

}


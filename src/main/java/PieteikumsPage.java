import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class PieteikumsPage {

    private final WebDriver driver;

    public static final String PIETEIKUMS_PAGE_TITLE = "LMT | Biznesa pakalpojuma pieteikums";
    public static final String COMPANY_INPUT = "//input[@id='companyId2']";
    public static final String NAME_INPUT = "//input[@id='nameId2']";
    public static final String SURNAME_INPUT = "//input[@id='surnameId2']";
    public static final String PHONE_INPUT = "//input[@id='phoneId2']";
    public static final String EMAIL_INPUT = "//input[@id='emailId2']";
    public static final String COMMENT_INPUT = "//textarea[@id='commentId2']";
    public static final String PIEKRITU_CHECKBOX = "//input[@id='checkbox-atruna']";
    public static final String PIETEIKTIES_BTN = "//a[@id='popupBtn2']";


    public PieteikumsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPieteikumsPageTitle() {
        return PIETEIKUMS_PAGE_TITLE;
    }

    public void setRandomCompanyName() {
        WebElement companyInput = waitForElementByXPath(COMPANY_INPUT);
        String newCompany = generateRandomText(15);
        companyInput.sendKeys(newCompany);
    }

    public void setRandomName() {
        WebElement nameInput = waitForElementByXPath(NAME_INPUT);
        String newName = generateRandomText(10);
        nameInput.sendKeys(newName);
    }

    public void setRandomSurname() {
        WebElement surnameInput = waitForElementByXPath(SURNAME_INPUT);
        String newSurname = generateRandomText(15);
        surnameInput.sendKeys(newSurname);
    }

    public void setRandomPhone() {
        WebElement phoneInput = waitForElementByXPath(PHONE_INPUT);
        String newPhone = generateRandomPhone();
        phoneInput.sendKeys(newPhone);
    }

    public void setRandomEmail() {
        WebElement emailInput = waitForElementByXPath(EMAIL_INPUT);
        String newEmail = generateRandomEmail();
        emailInput.sendKeys(newEmail);
    }

    public void setRandomComment() {
        WebElement commentInput = waitForElementByXPath(COMMENT_INPUT);
        String newComment = generateRandomString();
        commentInput.sendKeys(newComment);
    }

    public void clickOnPiekrituCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PIEKRITU_CHECKBOX)));
        checkbox.click();
    }

    public void clickOnPieteiktiesBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pieteiktiesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PIETEIKTIES_BTN)));
        pieteiktiesBtn.click();
    }

    public void fillPieteikumaFormInputs() {
        setRandomCompanyName();
        setRandomName();
        setRandomSurname();
        setRandomPhone();
        setRandomEmail();
        setRandomComment();
        //clickOnPiekrituCheckbox();
        //clickOnPieteiktiesBtn();
    }

    public WebElement waitForElementByXPath(String xpath) {
        By elementLocator = By.xpath(xpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomText = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }
        return randomText.toString();
    }

    private String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomText = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }
        return randomText.toString();
    }

    private String generateRandomPhone() {
        String characters = "0123456789";
        StringBuilder randomText = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }
        return "2" + randomText;
    }

    private String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomText = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            int index = random.nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }
        return randomText + "@gmail.com";
    }
}

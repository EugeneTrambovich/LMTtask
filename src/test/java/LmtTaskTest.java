import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

import org.testng.annotations.*;

public class LmtTaskTest {

    private WebDriver driver;
    private MainPage mainPage;
    private FunkcijasPage funkcijasPage;
    private PieteikumsPage pieteikumsPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        funkcijasPage = new FunkcijasPage(driver);
        pieteikumsPage = new PieteikumsPage(driver);
        driver.get("https://edlus.lmt.lv/");
        driver.manage().window().maximize();
        acceptCookies();
    }

    @Test(priority = 5)
    public void lmtScenarioTask() throws InterruptedException {

        //2) Verify the web page title/
        assertCurrentPageTitle(mainPage.getMainPageTitle());

        //3) Click on the “Funkcijas” hyperlink
        mainPage.clickOnFunkcijasLink();
        assertCurrentPageTitle(funkcijasPage.getFunkcijasPageTitle());

        //4) Navigate back to the original web page
        driver.navigate().back();
        driver.navigate().refresh();
        assertCurrentPageTitle(mainPage.getMainPageTitle());

        //5) Verify if the “Pieteikties konsultācijai” button is displayed
        scrollIntoView(mainPage.PieteiktiesBtn());
        Assert.assertTrue(mainPage.PieteiktiesBtn().isDisplayed(), "Pieteikties button is not displayed.");
        Thread.sleep(1000);

        //6) Based on visibility of the “Pieteikties konsultācijai” button, click on it
        mainPage.clickOnPieteiktiesKonsultacijaiButton();

        //7) Fill the form with test data
        scrollIntoView(mainPage.PieteiktiesBtn());
        mainPage.clickOnPieteiktiesKonsultacijaiButton();
        switchToTabWithTitle(pieteikumsPage.getPieteikumsPageTitle());
        Thread.sleep(1000);
        pieteikumsPage.fillPieteikumaFormInputs();
    }

    @Test(priority = 1)
    public void verifyPageTitleTest() {
        assertCurrentPageTitle(mainPage.getMainPageTitle());
    }

    @Test(priority = 2)
    public void funkcijasLinkFunctionalityTest() {
        mainPage.clickOnFunkcijasLink();
        assertCurrentPageTitle(funkcijasPage.getFunkcijasPageTitle());
        driver.navigate().back();
        // seems to be a bug, funkcijas title remain the same on return without refresh
        driver.navigate().refresh();
        assertCurrentPageTitle(mainPage.getMainPageTitle());
    }

    @Test(priority = 3)
    public void verifyPieteiktiesBtnFunctionalityTest() throws InterruptedException {
        Thread.sleep(1000);
        scrollIntoView(mainPage.PieteiktiesBtn());
        Assert.assertTrue(mainPage.PieteiktiesBtn().isDisplayed(), "Pieteikties button is not displayed.");
        mainPage.clickOnPieteiktiesKonsultacijaiButton();
        switchToTabWithTitle(pieteikumsPage.getPieteikumsPageTitle());
        assertCurrentPageTitle(pieteikumsPage.getPieteikumsPageTitle());
    }

    @Test(priority = 4)
    public void fillPieteikumsFormTest() {
        //Flaky test, need to improve
        scrollIntoView(mainPage.PieteiktiesBtn());
        mainPage.clickOnPieteiktiesKonsultacijaiButton();
        switchToTabWithTitle(pieteikumsPage.getPieteikumsPageTitle());
        pieteikumsPage.fillPieteikumaFormInputs();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public void acceptCookies() {
        By elementLocator = By.xpath("//button[@data-cookie-approve='all']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        element.click();
    }

    private void assertCurrentPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title is not as expected");
    }

    private void switchToTabWithTitle(String targetTitle) {
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(targetTitle)) {
                break;
            }
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}


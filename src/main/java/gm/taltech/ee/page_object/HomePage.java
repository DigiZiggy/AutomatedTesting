package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class HomePage {
    private WebDriver driver;
    private By header = By.tagName("header");
    private By title = By.cssSelector("div#u1766");
    private By enterOISDropdownLink = By.partialLinkText("ÕIS");
    private By enterIntranetDropdownLink = By.partialLinkText("Siseveeb");
    private By enterMoodleDropdownLink = By.partialLinkText("Moodle");
    private By mainLogo = By.className("main-logo");
    private By goToStudentPageLink = By.linkText("Tudeng");
    private By goToEmployeeSearchLink = By.linkText("Otsi töötajat");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        return driver.findElement(mainLogo).isDisplayed();
    }

    public void clickGoToStudentPageLink() {
        driver.findElement(goToStudentPageLink).click();
    }

    public void clickGoToEmployeeSearchLink() {
        driver.findElement(goToEmployeeSearchLink).click();
    }

    public void clickGoToOISPageDropdownLink() {
        driver.findElement(enterOISDropdownLink).click();
    }

    public void clickGoToMoodlePageDropdownLink() {
        driver.findElement(enterMoodleDropdownLink).click();
    }

    public void clickGoToIntranetPageDropdownLink() {
        driver.findElement(enterIntranetDropdownLink).click();
    }

    public void goToSecondWindowOpened() {
        String firstPageWindowHandle;
        String secondPageWindowHandle = null;
        firstPageWindowHandle = driver.getWindowHandle();

        // Store both window handles
        Set<String> testPageWindowHandle = driver.getWindowHandles();

        for (String windowHandle : testPageWindowHandle) {
            if (!firstPageWindowHandle.equals(windowHandle)) {
                secondPageWindowHandle = windowHandle;
            }
        }

        driver.switchTo().window(secondPageWindowHandle);
    }

    public void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}

package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class HomePage {
    private WebDriver driver;
    private By mainLogo = By.className("main-logo");
    private By goToStudentPageLink = By.linkText("Tudeng");
    private By goToEmployeeSearchLink = By.linkText("Otsi töötajat");

    private Actions builder;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        builder = new Actions(driver);
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
        WebElement clickElement = driver.findElement(By.xpath("//*[@id=\"dropdownHeader\"]/div/div[3]/ul/li/ul/li[1]/a"));
        builder.moveToElement(clickElement).click().perform();
    }

    public void clickGoToMoodlePageDropdownLink() {
        WebElement clickElement = driver.findElement(By.xpath("//*[@id=\"dropdownHeader\"]/div/div[3]/ul/li/ul/li[2]/a"));
        builder.moveToElement(clickElement).click().perform();
    }

    public void clickGoToIntranetPageDropdownLink() {
        WebElement clickElement = driver.findElement(By.xpath("//*[@id=\"dropdownHeader\"]/div/div[3]/ul/li/ul/li[3]/a"));
        builder.moveToElement(clickElement).click().perform();
    }

    public void hoverOnEnterIntoDropdown() {
        WebElement hoverElement = driver.findElement(By.cssSelector("div[class='login languages']"));
        builder.moveToElement(hoverElement).perform();
    }
}

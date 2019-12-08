package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchEmployeePage {

    private WebDriver driver;
    private By inputNameField = By.cssSelector("input[name='q']:nth-of-type(1)");
    private By submitButton = By.xpath("//input[@type='submit']");
    private By employeeDetailsTable = By.className("pt");

    public SearchEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmployeeName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputNameField));

        inputField.sendKeys(name);
    }

    public boolean isCorrectEmailDisplayed() {
        return driver.findElement(employeeDetailsTable).findElement(By.linkText("german.mumma@taltech.ee")).isDisplayed();
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }
}

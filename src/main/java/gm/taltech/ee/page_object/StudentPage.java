package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudentPage {
    private WebDriver driver;
    private By goToStudyInfoPageLink = By.linkText("Õppeinfo");


    public StudentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGoToStudyInfoLink() {
        driver.findElement(goToStudyInfoPageLink).click();
    }

}

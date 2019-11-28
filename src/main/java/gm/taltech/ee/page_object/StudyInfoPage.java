package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudyInfoPage {
    private WebDriver driver;
    private By mainTitle = By.tagName("h2");


    public StudyInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEducationInfoDisplayed() {
        return driver.findElement(mainTitle).getText().equals("Kvaliteetne haridus");
    }
}

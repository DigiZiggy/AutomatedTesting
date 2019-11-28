package gm.taltech.ee.page_object;

import org.openqa.selenium.WebDriver;

public class MoodlePage {

    private WebDriver driver;

    public MoodlePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals("https://moodle.taltech.ee/login/index.php");
    }
}

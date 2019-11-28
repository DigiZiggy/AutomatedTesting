package gm.taltech.ee.page_object;

import org.openqa.selenium.WebDriver;

public class IntranetPage {

    private WebDriver driver;

    public IntranetPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals("https://auth.ttu.ee/login/et/portal");
    }
}

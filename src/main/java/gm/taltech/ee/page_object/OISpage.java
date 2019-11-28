package gm.taltech.ee.page_object;

import org.openqa.selenium.WebDriver;

public class OISpage {

    private WebDriver driver;

    public OISpage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals("https://ois2.ttu.ee/uusois/uus_ois2.tud_leht");
    }
}

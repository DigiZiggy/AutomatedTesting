package gm.taltech.ee.ui;

import gm.taltech.ee.page_object.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TalTechHomepageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void set_up_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void open_driver() {
        driver.get("https://www.ttu.ee/");
    }

    @Test
    public void can_go_to_home_page() {
        assertThat(homePage.isAt(), is(true));
    }

    @Test
    public void should_be_able_to_navigate_to_study_info_page_from_homepage() {
        StudentPage studentPage = new StudentPage(driver);
        StudyInfoPage studyInfoPage = new StudyInfoPage(driver);

        homePage.clickGoToStudentPageLink();
        studentPage.clickGoToStudyInfoLink();

        assertThat(studyInfoPage.isEducationInfoDisplayed(), is(true));
    }

    @Test
    public void should_display_correct_email_address_under_employee_details() {
        SearchEmployeePage searchEmployeePage = new SearchEmployeePage(driver);

        homePage.clickGoToEmployeeSearchLink();
        searchEmployeePage.enterEmployeeName("German Mumma");
        searchEmployeePage.clickSubmit();

        assertThat(searchEmployeePage.isCorrectEmailDisplayed(), is(true));
    }

    @Test
    public void should_be_able_to_navigate_to_OIS_page_from_homepage() {
        OISpage oisPage = new OISpage(driver);

        homePage.clickGoToOISPageDropdownLink();  //OPENS WRONG LINK ON PAGE
        driver.navigate().refresh();
        homePage.waitForLoad(driver);
        homePage.goToSecondWindowOpened();

        assertThat(oisPage.isDisplayed(), is(true));
    }

    @Test
    public void should_be_able_to_navigate_to_Moodle_page_from_homepage() {
        MoodlePage moodlePage = new MoodlePage(driver);

        homePage.clickGoToMoodlePageDropdownLink();  //OPENS WRONG LINK ON PAGE
        driver.navigate().refresh();
        homePage.waitForLoad(driver);
        homePage.goToSecondWindowOpened();

        assertThat(moodlePage.isDisplayed(), is(true));
    }

    @Test
    public void should_be_able_to_navigate_to_Intranet_page_from_homepage() {
        IntranetPage intranetPage = new IntranetPage(driver);

        homePage.clickGoToIntranetPageDropdownLink();  //OPENS WRONG LINK ON PAGE
        driver.navigate().refresh();
        homePage.waitForLoad(driver);
        homePage.goToSecondWindowOpened();

        assertThat(intranetPage.isDisplayed(), is(true));
    }

    @AfterClass
    public void close_driver() {
        if (driver != null) {
            driver.close();
        }
    }
}

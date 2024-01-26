package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

@Epic("Registration Test Epic")
@Feature("Fill in the registration form Feature")
public class RegistrationTest {
    public static ChromeOptions options;
    public static WebDriver driver;
    public static RegistrationPage registrationPage;
    public static Modal modal;
    public static String picturePath = "C:\\Users\\Asus\\OneDrive\\Рабочий стол\\Tests\\RegistrationTest\\src\\imgs\\pr3.jpg";


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);

        registrationPage = new RegistrationPage(driver);
        modal = new Modal(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("registration-page"));
    }

    @Test
    @Story("User tries to register the system")
    @Description("Validation of entered data")
    public void registrationTest() {
        registrationPage.inputFirstName(ConfProperties.getProperty("first-name"));
        registrationPage.inputLastName(ConfProperties.getProperty("last-name"));
        registrationPage.inputMail(ConfProperties.getProperty("email"));
        registrationPage.selectGender();
        registrationPage.inputMobile(ConfProperties.getProperty("mobile"));
        registrationPage.selectDateOfBirth();
        registrationPage.selectSubjects("a");
        registrationPage.selectHobbies();
        registrationPage.selectPicture(picturePath);
        registrationPage.enterCurrentAddress(ConfProperties.getProperty("current-address"));
        registrationPage.selectStateAndCity();
        registrationPage.submit();

        Assertions.assertEquals(modal.getModalHeader(), ConfProperties.getProperty("modal-header"));

        Assertions.assertEquals(modal.getStudentName(),
                ConfProperties.getProperty("first-name") + " " + ConfProperties.getProperty("last-name"));
        Assertions.assertEquals(modal.getStudentEmail(), ConfProperties.getProperty("email"));
        Assertions.assertEquals(modal.getGender(), ConfProperties.getProperty("gender"));
        Assertions.assertEquals(modal.getMobile(), ConfProperties.getProperty("mobile"));
        Assertions.assertEquals(modal.getDateOfBirth(), "08 January,2024");
        Assertions.assertEquals(modal.getSubjects(), ConfProperties.getProperty("subjects"));
        Assertions.assertEquals(modal.getHobbies(), ConfProperties.getProperty("hobbies"));
        Assertions.assertEquals(modal.getPicture(), ConfProperties.getProperty("picture"));
        Assertions.assertEquals(modal.getAddress(), ConfProperties.getProperty("current-address"));
        Assertions.assertEquals(modal.getStateAndCity(), ConfProperties.getProperty("state-and-city"));
    }

    @AfterEach
    public void quitDriver() {
        driver.close();
        driver.quit();
    }
}

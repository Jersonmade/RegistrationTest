package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    public static WebDriver driver;
    public static RegistrationPage registrationPage;
    public static Modal modal;
    private String picturePath = "C:\\Users\\Asus\\OneDrive\\Рабочий стол\\Tests\\RegistrationTest\\src\\imgs\\pr3.jpg";


    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "C:\\tools\\geckodriver-v0.34.0-win64\\geckodriver.exe");

        driver = new FirefoxDriver();
        registrationPage = new RegistrationPage(driver);
        modal = new Modal(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get(ConfProperties.getProperty("registrationPage"));
    }

    @Test
    public void registrationTest() {
        registrationPage.inputFirstName(ConfProperties.getProperty("firstName"));
        registrationPage.inputLastName(ConfProperties.getProperty("lastName"));
        registrationPage.inputMail(ConfProperties.getProperty("email"));
        registrationPage.selectGender();
        registrationPage.inputMobile(ConfProperties.getProperty("mobile"));
        registrationPage.selectDateOfBirth();
        registrationPage.selectSubjects("a");
        registrationPage.selectHobbies();
        registrationPage.selectPicture(picturePath);
        registrationPage.enterCurrentAddress(ConfProperties.getProperty("currentAddress"));
        registrationPage.selectStateAndCity();
        registrationPage.submit();

        Assertions.assertEquals(modal.getModalHeader(), ConfProperties.getProperty("modalHeader"));
        Assertions.assertEquals(modal.getStudentName(),
                ConfProperties.getProperty("firstName") + " " + ConfProperties.getProperty("lastName"));
        Assertions.assertEquals(modal.getStudentEmail(), ConfProperties.getProperty("email"));
        Assertions.assertEquals(modal.getGender(), ConfProperties.getProperty("gender"));
        Assertions.assertEquals(modal.getMobile(), ConfProperties.getProperty("mobile"));
        Assertions.assertEquals(modal.getDateOfBirth(), "08 January,2024");
        Assertions.assertEquals(modal.getSubjects(), ConfProperties.getProperty("subjects"));
        Assertions.assertEquals(modal.getHobbies(), ConfProperties.getProperty("hobbies"));
        Assertions.assertEquals(modal.getPicture(), ConfProperties.getProperty("picture"));
        Assertions.assertEquals(modal.getAddress(), ConfProperties.getProperty("currentAddress"));
        Assertions.assertEquals(modal.getStateAndCity(), ConfProperties.getProperty("stateAndCity"));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}

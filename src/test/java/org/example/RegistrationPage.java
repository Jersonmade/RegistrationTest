package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "input#firstName")
    private WebElement firstNameField;
    @FindBy(id = "lastName")
    private WebElement lastNameField;
    @FindBy(css = "input#userEmail")
    private WebElement mailField;
    @FindBy(xpath = "//input[@value='Male']")
    private WebElement genderRadio;
    @FindBy(id = "userNumber")
    private WebElement mobileField;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;
    @FindBy(xpath = "//div[contains(@aria-label, 'January 8th, 2024')]")
    private WebElement dateOfBirthBtn;
    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;
    @FindBy(id = "hobbies-checkbox-1")
    private WebElement sportsHobby;
    @FindBy(css = "input#hobbies-checkbox-3")
    private WebElement musicHobby;
    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private WebElement currentAddressTextArea;
    @FindBy(xpath = "//input[@id='uploadPicture']")
    private WebElement uploadFileBtn;
    @FindBy(id = "state")
    private WebElement stateDropdown;
    @FindBy(id = "city")
    private WebElement cityDropdown;
    @FindBy(id = "submit")
    private WebElement submitBtn;
    public void inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void  inputMail(String mail) {
        mailField.sendKeys(mail);
    }

    public void selectGender() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", genderRadio);
    }

    public void inputMobile(String mobile) {
        mobileField.sendKeys(mobile);
    }

    public void selectDateOfBirth() {
        int seconds = 10;

        dateOfBirthInput.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds((long) seconds));
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthBtn)).click();
    }

    public void selectSubjects(String str) {
        wait = new WebDriverWait(driver, Duration.ofSeconds((long)10));
        subjectsInput.sendKeys(str);
        WebElement dropdownOption = wait
                .until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'subjects-auto-complete__option')]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownOption).click().build().perform();
    }

    public void selectHobbies() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", sportsHobby);
        js.executeScript("arguments[0].click();", musicHobby);
    }

    public void selectPicture(String path) {
        uploadFileBtn.sendKeys(path);
    }

    public void enterCurrentAddress(String currentAddress) {
        currentAddressTextArea.sendKeys(currentAddress);
    }

    public void selectStateAndCity() {
        wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", stateDropdown);

        stateDropdown.click();

        WebElement optionNCR = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='state']//div[text()='NCR']")));
        optionNCR.click();

        wait.until(ExpectedConditions.visibilityOf(cityDropdown));

        wait.until(ExpectedConditions.elementToBeClickable(cityDropdown));

        cityDropdown.click();
        WebElement optionDelhi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='city']//div[text()='Delhi']")));
        optionDelhi.click();
    }

    public void submit() {
        submitBtn.click();
    }
}

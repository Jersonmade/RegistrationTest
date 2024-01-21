package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Modal {
    public WebDriver driver;

    public Modal(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='modal-header']")
    private WebElement modalHeader;
    @FindBy(xpath = "//td[text()='Student Name']/following-sibling::td")
    private WebElement studentName;
    @FindBy(xpath = "//td[text()='Student Email']/following-sibling::td")
    private WebElement studentEmail;
    @FindBy(xpath = "//td[text()='Gender']//following-sibling::td")
    private WebElement gender;
    @FindBy(xpath = "//td[text()='Mobile']//following-sibling::td")
    private WebElement mobile;
    @FindBy(xpath = "//td[text()='Date of Birth']//following-sibling::td")
    private WebElement dateOfBirth;
    @FindBy(xpath = "//td[text()='Subjects']//following-sibling::td")
    private WebElement subjects;
    @FindBy(xpath = "//td[text()='Hobbies']//following-sibling::td")
    private WebElement hobbies;
    @FindBy(xpath = "//td[text()='Picture']//following-sibling::td")
    private WebElement picture;
    @FindBy(xpath = "//td[text()='Address']//following-sibling::td")
    private WebElement address;
    @FindBy(xpath = "//td[text()='State and City']//following-sibling::td")
    private WebElement stateAndCity;

    public String getModalHeader() {
        return modalHeader.getText();
    }

    public String getStudentName() {
        return studentName.getText();
    }

    public String getStudentEmail() {
        return studentEmail.getText();
    }

    public String getGender() {
        return gender.getText();
    }

    public String getMobile() {
        return mobile.getText();
    }

    public String getDateOfBirth() {
        return dateOfBirth.getText();
    }

    public String getSubjects() {
        return subjects.getText();
    }

    public String getHobbies() {
        return hobbies.getText();
    }

    public String getPicture() {
        return picture.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getStateAndCity() {
        return stateAndCity.getText();
    }
}

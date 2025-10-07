package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stepDefinitions.Hooks;


import java.time.Duration;

public class P02_Signup {

    private WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));

    // Page Elements using methods
    public WebElement firstName() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
    }

    public WebElement lastName() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
    }

    public WebElement email() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"email\"]"));
    }

    public WebElement password() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"password\"]"));
    }

    public WebElement confirmPassword() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"confirmPassword\"]"));
    }

    public WebElement studentPhone() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"phoneNumber\"]"));
    }

    public WebElement parentPhone() {
        return Hooks.driver.findElement(By.xpath("//*[@id=\"parentPhoneNumber\"]"));
    }

    public WebElement academicLevelDropdown() {
        return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/form/div[8]/select"));
    }

    public WebElement signUpButton() {
        return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/form/button"));
    }

    public WebElement signUpHeader() {
        return Hooks.driver.findElement(By.xpath("/html/body/nav[2]/ul[2]/li[1]/a"));
    }

//    public boolean isSignUpPageDisplayed() {
//        try {
//            return wait.until(ExpectedConditions.visibilityOf(signUpHeader())).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstName()));
        firstName().click();
        firstName().clear();
        firstName().sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastName()));
        lastName().click();
        lastName().clear();
        lastName().sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(email()));
        email().click();
        email().clear();
        email().sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(password()));
        password().click();
        password().clear();
        password().sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOf(confirmPassword()));
        confirmPassword().click();
        confirmPassword().clear();
        confirmPassword().sendKeys(confirmPassword);
    }

    public void enterStudentPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(studentPhone()));
        studentPhone().click();
        studentPhone().clear();
        studentPhone().sendKeys(phoneNumber);
    }

    public void enterParentPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(parentPhone()));
        parentPhone().click();
        parentPhone().clear();
        parentPhone().sendKeys(phoneNumber);
    }

    public void selectAcademicLevel(String level) {
        wait.until(ExpectedConditions.visibilityOf(academicLevelDropdown()));
        Select select = new Select(academicLevelDropdown());
        try {
            select.selectByValue(level);
        } catch (Exception e) {
            try {
                select.selectByVisibleText(level);
            } catch (Exception ex) {
                select.selectByIndex(Integer.parseInt(level) - 1);
            }
        }
    }

    public void clickSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton()));
        signUpButton().click();
    }

    public String getFirstNameValue() {
        return firstName().getAttribute("value");
    }

    public String getLastNameValue() {
        return lastName().getAttribute("value");
    }

    public String getEmailValue() {
        return email().getAttribute("value");
    }

    public String getPasswordValue() {
        return password().getAttribute("value");
    }

    public String getConfirmPasswordValue() {
        return confirmPassword().getAttribute("value");
    }

    public String getStudentPhoneValue() {
        return studentPhone().getAttribute("value");
    }

    public String getParentPhoneValue() {
        return parentPhone().getAttribute("value");
    }

    public String getSelectedAcademicLevel() {
        Select select = new Select(academicLevelDropdown());
        return select.getFirstSelectedOption().getText();
    }

    public void fillSignUpForm(String firstName, String lastName, String email,
                               String password, String confirmPassword,
                               String studentPhone, String parentPhone, String academicLevel) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        enterStudentPhoneNumber(studentPhone);
        enterParentPhoneNumber(parentPhone);
        selectAcademicLevel(academicLevel);
    }
}
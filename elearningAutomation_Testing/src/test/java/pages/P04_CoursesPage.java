package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

public class P04_CoursesPage {
    public final String PAGE_URL = "http://34.66.168.124:8080/courses";

    public WebElement coursePageTitle(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/h1"));}

    public WebElement TeacherNameDropdown(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[1]/button"));}

    public WebElement SubjectsDropdown(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[2]/button"));}

    public WebElement SemesterDropdown(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[3]/button"));}

    public WebElement LanguageDropdown(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[4]/button"));}

    public WebElement AcademicYearDropdown(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[5]/button"));}

    public WebElement SearchButton(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[6]/button[1]"));}

    public WebElement ResetButton(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[1]/div/div/div/div[6]/button[2]"));}

}

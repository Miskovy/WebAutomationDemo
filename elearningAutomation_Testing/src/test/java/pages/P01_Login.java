package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepDefinitions.Hooks;

public class P01_Login {
    public WebElement signin_btn(){return Hooks.driver.findElement(By.xpath("/html/body/nav[2]/ul[2]/li[2]/a"));}
    public WebElement email_txt(){return Hooks.driver.findElement(By.xpath("//*[@id=\"email\"]"));}
    public WebElement password_txt(){return Hooks.driver.findElement(By.xpath("//*[@id=\"password\"]"));}
    public WebElement login_button(){return Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/form/div[3]/button"));}
}

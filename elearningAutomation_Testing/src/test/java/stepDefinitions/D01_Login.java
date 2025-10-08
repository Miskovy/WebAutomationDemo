package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.P01_Login;

public class D01_Login {
    P01_Login loginpage = new P01_Login();
    @When("the user is in the login page")
    public void user_in_the_login(){
        Assert.assertTrue(Hooks.driver.findElement(By.xpath("/html/body/nav[2]/div/h3")).isDisplayed());
    }
    @And("the user enters the email {string} and password {string}")
    public void user_enters_credentials(String email , String password) throws InterruptedException {
        loginpage.signin_btn().click();
        Thread.sleep(5000);
        Assert.assertTrue(Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/h2")).isDisplayed());
        loginpage.email_txt().click();
        loginpage.email_txt().sendKeys(email);
        loginpage.password_txt().click();
        loginpage.password_txt().sendKeys(password);
        loginpage.login_button().click();
    }
    @Then("error message should appear")
    public void assert_error_message(){
        WebElement errorMessage = Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/form/div[2]/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
        System.out.println(errorMessage.getText());
    }
}

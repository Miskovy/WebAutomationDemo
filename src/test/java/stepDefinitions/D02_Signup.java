package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.P02_Signup;

import java.util.Map;

public class D02_Signup {
P02_Signup signup = new P02_Signup();
    @Given("I am on the sign up page")
    public void iAmOnTheSignUpPage() throws InterruptedException {
        Hooks.driver.findElement(By.xpath("/html/body/nav[2]/ul[2]/li[1]/a")).click();
        Thread.sleep(5000);
        Assert.assertTrue(Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/h2")).isDisplayed(),
                "Sign Up page should be displayed");
    }

    @When("I enter {string} as first name")
    public void iEnterAsFirstName(String firstName) {
        signup.enterFirstName(firstName);
    }

    @When("I enter {string} as last name")
    public void iEnterAsLastName(String lastName) {
        signup.enterLastName(lastName);
    }

    @When("I enter {string} as email")
    public void iEnterAsEmail(String email) {
        signup.enterEmail(email);
    }

    @When("I enter {string} as password")
    public void iEnterAsPassword(String password) {
        signup.enterPassword(password);
    }

    @When("I enter {string} as confirm password")
    public void iEnterAsConfirmPassword(String confirmPassword) {
        signup.enterConfirmPassword(confirmPassword);
    }

    @When("I enter {string} as student phone number")
    public void iEnterAsStudentPhoneNumber(String phoneNumber) {
        signup.enterStudentPhoneNumber(phoneNumber);
    }

    @When("I enter {string} as parent phone number")
    public void iEnterAsParentPhoneNumber(String phoneNumber) {
        signup.enterParentPhoneNumber(phoneNumber);
    }

    @When("I select {string} as student academic level")
    public void iSelectAsStudentAcademicLevel(String level) {
        signup.selectAcademicLevel(level);
    }

    @When("I fill the following sign up details:")
    public void iFillTheFollowingSignUpDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        if (data.containsKey("First Name")) {
            signup.enterFirstName(data.get("First Name"));
        }
        if (data.containsKey("Last Name")) {
            signup.enterLastName(data.get("Last Name"));
        }
        if (data.containsKey("Email")) {
            signup.enterEmail(data.get("Email"));
        }
        if (data.containsKey("Password")) {
            signup.enterPassword(data.get("Password"));
        }
        if (data.containsKey("Confirm Password")) {
            signup.enterConfirmPassword(data.get("Confirm Password"));
        }
        if (data.containsKey("Student Phone Number")) {
            signup.enterStudentPhoneNumber(data.get("Student Phone Number"));
        }
        if (data.containsKey("Parent Phone Number")) {
            signup.enterParentPhoneNumber(data.get("Parent Phone Number"));
        }
        if (data.containsKey("Student Academic Level")) {
            signup.selectAcademicLevel(data.get("Student Academic Level"));
        }
    }

    @Then("all fields should be filled correctly")
    public void allFieldsShouldBeFilledCorrectly() {
        Assert.assertFalse(signup.getFirstNameValue().isEmpty(),
                "First name should not be empty");
        Assert.assertFalse(signup.getLastNameValue().isEmpty(),
                "Last name should not be empty");
        Assert.assertFalse(signup.getEmailValue().isEmpty(),
                "Email should not be empty");
        Assert.assertFalse(signup.getStudentPhoneValue().isEmpty(),
                "Student phone should not be empty");
        Assert.assertFalse(signup.getParentPhoneValue().isEmpty(),
                "Parent phone should not be empty");
    }
    @Then("the user presses signup button")
    public void signupasStudent(){
        Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/form/button")).click();
    }
//    @Then("a Success Message should be displayed")
//    public void assert_SuccessfulSignup(){
//        Assert.assertTrue(Hooks.driver.findElement(By.xpath("")).isDisplayed());
//    }
}

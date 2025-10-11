package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.P01_Login;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class D01_Login {
    P01_Login login_page = new P01_Login();
    @When("the user is in the login page")
    public void user_in_the_login(){
        Assert.assertTrue(Hooks.driver.findElement(By.xpath("/html/body/nav[2]/div/h3")).isDisplayed());
    }
    @And("the user enters the email {string} and password {string} expecting status code {int}")
    public void user_enters_credentials_and_validates_api(String email, String password, int expectedStatusCode) {
        login_page.signin_btn().click();

        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
        By loginHeader = By.xpath("/html/body/main/section/div/div[2]/h2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));

        login_page.email_txt().sendKeys(email);
        login_page.password_txt().sendKeys(password);
        login_page.login_button().click();

        String endpoint = "/api/Auth/login";

        Wait<Map<String, ResponseDetails>> apiWait = new FluentWait<>(Hooks.capturedResponses)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500));

        try {
            apiWait.until(responses -> {
                Optional<ResponseDetails> foundResponse = responses.entrySet().stream()
                        .filter(entry -> entry.getKey().contains(endpoint))
                        .map(Map.Entry::getValue)
                        .findFirst();

                // Check if the response is found and has the correct status
                if (foundResponse.isPresent() && foundResponse.get().status() == expectedStatusCode) {
                    System.out.println("SUCCESS: Captured API call to '" + endpoint + "' with status " + expectedStatusCode);
                    System.out.println("Response Body: " + foundResponse.get().body());
                    return true; // Exit the wait, success!
                }
                return false; // Keep waiting
            });
        } catch (Exception e) {
            Assert.fail("Failed to capture API call for '" + endpoint + "' with expected status " + expectedStatusCode + ". Captured URLs: " + Hooks.capturedResponses.keySet());
        }
    }
    @Then("error message should appear")
    public void assert_error_message(){
        WebElement errorMessage = Hooks.driver.findElement(By.xpath("/html/body/main/section/div/div[2]/form/div[2]/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
        System.out.println(errorMessage.getText());
    }
    @Then("the API call to {string} should have status code {int}")
    public void the_api_call_to_should_have_status_code(String endpoint, int expectedStatusCode) {
        // Find the response from the map that contains the desired endpoint
        Optional<ResponseDetails> foundResponse = Hooks.capturedResponses.entrySet().stream()
                .filter(entry -> entry.getKey().contains(endpoint))
                .map(Map.Entry::getValue)
                .findFirst();

        // Assert that a response for that endpoint was actually captured
        Assert.assertTrue(foundResponse.isPresent(), "No API call was captured for endpoint containing: " + endpoint);

        // Get the actual details
        ResponseDetails details = foundResponse.get();
        int actualStatusCode = details.status();

        // Assert the status code is correct
        Assert.assertEquals(actualStatusCode,expectedStatusCode ,"Status code mismatch for endpoint: " + endpoint);

        System.out.println("Successfully validated endpoint: " + endpoint);
        System.out.println("Status Code: " + actualStatusCode);
        System.out.println("Response Body: " + details.body());
    }
}

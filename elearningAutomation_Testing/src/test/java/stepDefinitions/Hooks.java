package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v139.network.Network; // Check for your browser version
import org.openqa.selenium.devtools.v139.network.model.ResourceType;
import org.openqa.selenium.devtools.v139.network.model.Response;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// ADDED: A simple record to hold the details of a captured response.
// This makes it easier to store and retrieve the data.
record ResponseDetails(int status, String body) {}

public class Hooks {
    public static ChromeDriver driver;
    public static DevTools devTools;

    // MODIFIED: Replaced single variables with a thread-safe Map to store all captured responses.
    public static Map<String, ResponseDetails> capturedResponses = new ConcurrentHashMap<>();

    @Before
    public static void OpenBrowser() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver();

        devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty()));

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            if (responseReceived.getType().equals(ResourceType.XHR) || responseReceived.getType().equals(ResourceType.FETCH)) {
                Response response = responseReceived.getResponse();
                String url = response.getUrl();
                int status = response.getStatus();
                String body = ""; // Default to empty string

                // If there's no body, it will return empty without an error.
                try {
                    body = devTools.send(Network.getResponseBody(responseReceived.getRequestId())).getBody();
                } catch (Exception e) {
                    // This will now only catch actual errors, not just empty bodies.
                    body = "Could not retrieve response body: " + e.getMessage();
                }

                // Store the details in our map.
                capturedResponses.put(url, new ResponseDetails(status, body));
            }
        });


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://34.66.168.124:8080/");
    }

    @After
    public static void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        if (devTools != null) {
            devTools.close();
        }
        driver.quit();
    }
}
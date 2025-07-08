package io.github.densudas;

import static org.testng.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Objects;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Example test class demonstrating Selenium capabilities
 */
public class SeleniumFeaturesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Setup WebDriverManager to handle driver binaries
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options for headless execution
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Initialize the WebDriver
        driver = new ChromeDriver(options);

        // Initialize WebDriverWait with 10 seconds timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBasicNavigation() {
        // Navigate to a website
        driver.get("https://www.selenium.dev/");

        // Verify the title
        assertTrue(Objects.requireNonNull(driver.getTitle()).contains("Selenium"), "Title should contain 'Selenium'");

        // Find and click on the Documentation link
        WebElement docsLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Documentation"))
        );
        docsLink.click();

        // Verify navigation to the documentation page
        wait.until(ExpectedConditions.urlContains("/documentation"));
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/documentation"),
                "URL should contain '/documentation'");
    }

    @Test
    public void testFormInteraction() {
        // Navigate to the Selenium search page
        driver.get("https://www.selenium.dev/documentation/");

        // Find the search input field and enter a search term
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input.search-input"))
        );
        searchBox.sendKeys("webdriver");

        // Wait for search results to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".search-results")
        ));

        // Verify search results contain the search term
        WebElement searchResults = driver.findElement(By.cssSelector(".search-results"));
        assertTrue(searchResults.isDisplayed(), "Search results should be displayed");

        // Click on the first search result
        WebElement firstResult = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".search-results .search-result:first-child a")
                )
        );
        firstResult.click();

        // Verify navigation to the result page
        wait.until(ExpectedConditions.titleContains("WebDriver"));
    }

    @Test
    public void testJavaScriptExecution() {
        // Navigate to the Selenium homepage
        driver.get("https://www.selenium.dev/");

        // Execute JavaScript to get the page title
        String title = (String) ((ChromeDriver) driver).executeScript("return document.title");
        assertTrue(Objects.requireNonNull(title).contains("Selenium"), "Title should contain 'Selenium'");

        // Execute JavaScript to scroll to the bottom of the page
        ((ChromeDriver) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Find an element at the bottom of the page
        WebElement footer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer"))
        );

        // Verify the footer is visible
        assertTrue(footer.isDisplayed(), "Footer should be visible after scrolling");
    }
}

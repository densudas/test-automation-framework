package io.github.densudas.selenium.pages.indexpage;

import io.github.densudas.selenium.pages.BasePage;
import io.github.densudas.selenium.utils.DriverFactory;
import org.openqa.selenium.By;

import static io.github.densudas.selenium.utils.Utils.waitForElementToBeDisplayed;

public class HomePage extends BasePage<HomePage> {

    private static final String URL = "https://www.bbc.com/";

    public static HomePage navigateToPage() {
        DriverFactory.getDriver().get(URL);
        waitForElementToBeDisplayed(By.cssSelector("#orb-banner"));
        waitForElementToBeDisplayed(By.cssSelector("#page[role='main']"));
        return new HomePage();
    }

}

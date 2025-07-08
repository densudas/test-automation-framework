package io.github.densudas.selenium.pages.indexpage;

import static io.github.densudas.selenium.utils.Utils.waitForElementToBeDisplayed;

import io.github.densudas.selenium.controls.Link;
import io.github.densudas.selenium.controls.TextField;
import io.github.densudas.selenium.modals.PersonalDataModal;
import io.github.densudas.selenium.pages.BasePage;
import io.github.densudas.selenium.utils.DriverFactory;
import org.openqa.selenium.By;

public class HomePage extends BasePage<HomePage> {

    private static final String URL = "https://www.bbc.com/";

    public static HomePage navigateToPage() {
        DriverFactory.getDriver().get(URL);
        waitForElementToBeDisplayed(By.cssSelector("#orb-banner"));
        waitForElementToBeDisplayed(By.cssSelector("#page[role='main']"));
        return new HomePage();
    }

    public PersonalDataModal<HomePage> focusOnPersonalDataModal() {
        return new PersonalDataModal<>(this);
    }

    public HomePage clickSearch() throws Exception {
        Thread.sleep(1000);
        new Link(this, LinkLabel.SEARCH_BBC).findControl().click();
        return this;
    }

    public HomePage fillInSearchField(String text) throws Exception {
        Thread.sleep(1000);
        new TextField(this, TextFieldLabel.SEARCH).findControl().fillIn(text);
        return this;
    }

    @Override
    public String getLocation() {
        return this.getClass().getSimpleName();
    }
}

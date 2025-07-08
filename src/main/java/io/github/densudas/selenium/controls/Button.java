package io.github.densudas.selenium.controls;

import io.github.densudas.selenium.BaseActions;
import io.github.densudas.selenium.exceptions.ControlNotFoundException;
import io.github.densudas.selenium.exceptions.ControlNotInteractableException;
import io.github.densudas.selenium.exceptions.NoSuchSortException;
import io.github.densudas.selenium.utils.Utils;

public class Button extends BaseControl {

    public Button(Locatable pageObject, String name, int index) {
        super(pageObject, name, index);
        this.controlType = ControlType.BUTTON;
    }

    public Button(Locatable pageObject, String name) {
        super(pageObject, name);
        this.controlType = ControlType.BUTTON;
    }

    public Button(Locatable pageObject, LocatorName name) {
        super(pageObject, name);
        this.controlType = ControlType.BUTTON;
    }

    public Button(Locator locator) {
        super(locator);
        this.controlType = ControlType.BUTTON;
    }

    // TODO: check if it can be moved inside constructors
    public Actions findControl() throws Exception {
        // TODO: verify and update search mechanism
        findLocators();
        return new Actions(this);
    }

    public class Actions extends BaseActions {

        private Button control;

        Actions(Button control) {
            this.control = control;
        }

        public Button click() throws ControlNotInteractableException {
            if (webElement == null) throw new ControlNotFoundException(control);
            if (!webElement.isEnabled()) throw new ControlNotInteractableException(control);

            switch ((ControlSorts.Button) controlSort) {
                case BUTTON_1, BUTTON_2 -> webElement.click();
                case BUTTON_3 -> Utils.clickWithJS(webElement);
                default -> throw new NoSuchSortException(controlSort);
            }
            return control;
        }

        public boolean isDisplayed() {
            return switch ((ControlSorts.Button) controlSort) {
                case BUTTON_1, BUTTON_2 -> webElement != null && webElement.isDisplayed();
                default -> throw new NoSuchSortException(controlSort);
            };
        }

        public boolean isHidden() {
            return switch ((ControlSorts.Button) controlSort) {
                case BUTTON_1, BUTTON_2 -> webElement == null || !webElement.isDisplayed();
                default -> throw new NoSuchSortException(controlSort);
            };
        }
    }

}

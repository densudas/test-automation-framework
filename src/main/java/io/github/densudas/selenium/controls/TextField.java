package io.github.densudas.selenium.controls;

import io.github.densudas.selenium.BaseActions;
import io.github.densudas.selenium.exceptions.ControlNotFoundException;
import io.github.densudas.selenium.exceptions.ControlNotInteractableException;
import io.github.densudas.selenium.exceptions.NoSuchSortException;

public class TextField extends BaseControl {

    public TextField(Locatable pageObject, String name, int index) {
        super(pageObject, name, index);
        this.controlType = ControlType.TEXT_FIELD;
    }

    public TextField(Locatable pageObject, String name) {
        super(pageObject, name);
        this.controlType = ControlType.TEXT_FIELD;
    }

    public TextField(Locatable pageObject, LocatorName name) {
        super(pageObject, name);
        this.controlType = ControlType.TEXT_FIELD;
    }

    public TextField(Locator locator) {
        super(locator);
        this.controlType = ControlType.TEXT_FIELD;
    }

    // TODO: check if it can be moved inside constructors
    public Actions findControl() throws Exception {
        // TODO: verify and update search mechanism
        findLocators();
        return new Actions(this);
    }

    public class Actions extends BaseActions {

        private TextField control;

        Actions(TextField control) {
            this.control = control;
        }

        public TextField fillIn(String text) throws ControlNotInteractableException {

            if (webElement == null) throw new ControlNotFoundException(control);
            if (!webElement.isEnabled()) throw new ControlNotInteractableException(control);

            switch ((ControlSorts.TextField) controlSort) {
                case TEXT_FIELD_1 -> webElement.sendKeys(text);
                default -> throw new NoSuchSortException(controlSort);
            }
            return control;
        }

        public boolean isDisplayed() {
            return switch ((ControlSorts.TextField) controlSort) {
                case TEXT_FIELD_1 -> webElement != null && webElement.isDisplayed();
                default -> throw new NoSuchSortException(controlSort);
            };
        }

        public boolean isHidden() {
            return switch ((ControlSorts.TextField) controlSort) {
                case TEXT_FIELD_1 -> webElement == null || !webElement.isDisplayed();
                default -> throw new NoSuchSortException(controlSort);
            };
        }
    }
}

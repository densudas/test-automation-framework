package io.github.densudas.selenium.controls;

import io.github.densudas.selenium.BaseActions;
import io.github.densudas.selenium.exceptions.ControlNotFoundException;
import io.github.densudas.selenium.exceptions.ControlNotInteractableException;
import io.github.densudas.selenium.exceptions.NoSuchSortException;

public class Link extends BaseControl {

    public Link(Locatable pageObject, String name, int index) {
        super(pageObject, name, index);
        this.controlType = ControlType.LINK;
    }

    public Link(Locatable pageObject, String name) {
        super(pageObject, name);
        this.controlType = ControlType.LINK;
    }

    public Link(Locatable pageObject, LocatorName name) {
        super(pageObject, name);
        this.controlType = ControlType.LINK;
    }

    public Link(Locator locator) {
        super(locator);
        this.controlType = ControlType.LINK;
    }

    // TODO: check if it can be moved inside constructors
    public Actions findControl() throws Exception {
        // TODO: verify and update search mechanism
        findLocators();
        return new Actions(this);
    }


    public class Actions extends BaseActions {

        private Link control;

        Actions(Link control) {
            this.control = control;
        }

        public Link click() throws ControlNotInteractableException {
            if (webElement == null) throw new ControlNotFoundException(control);
            if (!webElement.isEnabled()) throw new ControlNotInteractableException(control);

            switch ((ControlSorts.Link) controlSort) {
                case LINK_1 -> webElement.click();
                default -> throw new NoSuchSortException(controlSort);
            }
            return control;
        }

        public boolean isDisplayed() {
            return switch ((ControlSorts.Link) controlSort) {
                case LINK_1 -> webElement != null && webElement.isDisplayed();
                default -> throw new NoSuchSortException(controlSort);
            };
        }

        public boolean isHidden() {
            return switch ((ControlSorts.Link) controlSort) {
                case LINK_1 -> webElement == null || !webElement.isDisplayed();
                default -> throw new NoSuchSortException(controlSort);
            };
        }
    }
}

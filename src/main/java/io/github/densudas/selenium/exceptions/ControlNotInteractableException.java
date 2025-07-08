package io.github.densudas.selenium.exceptions;

import io.github.densudas.selenium.controls.BaseControl;
import java.util.Optional;
import org.openqa.selenium.ElementNotInteractableException;

//TODO: Update Errors mechanism
public class ControlNotInteractableException extends RuntimeException {
    public ControlNotInteractableException(BaseControl control) {
        var message = new StringBuilder()
                .append(control.getControlType().getName())
                .append(" with name '")
                .append(control.getName())
                .append("' is not interactable.");

        throw Optional.ofNullable(control.getError())
                .map(e -> new ElementNotInteractableException(message.toString(), control.getError()))
                .orElse(new ElementNotInteractableException(message.toString()));
    }
}

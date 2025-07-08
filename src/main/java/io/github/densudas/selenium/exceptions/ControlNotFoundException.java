package io.github.densudas.selenium.exceptions;

import io.github.densudas.selenium.controls.BaseControl;
import java.util.Optional;
import org.openqa.selenium.NoSuchElementException;

//TODO: Update Errors mechanism
public class ControlNotFoundException extends RuntimeException {
    public ControlNotFoundException(BaseControl control) {
        var message = new StringBuilder()
                .append(control.getControlType().getName())
                .append(" with name '")
                .append(control.getName())
                .append("' is not found on page.");

        throw Optional.ofNullable(control.getError())
                .map(e -> new NoSuchElementException(message.toString(), control.getError()))
                .orElse(new NoSuchElementException(message.toString()));
    }
}

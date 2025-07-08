package io.github.densudas.selenium.exceptions;

import io.github.densudas.selenium.ControlSort;

public class NoSuchSortException extends IllegalArgumentException {

    public NoSuchSortException(ControlSort controlSort) {
        super("No such sort defined: " + controlSort);
    }
}

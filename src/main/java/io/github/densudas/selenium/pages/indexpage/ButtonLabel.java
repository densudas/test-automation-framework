package io.github.densudas.selenium.pages.indexpage;

import java.util.Locale;

public enum ButtonLabel implements TranslatableLocatorName {
    ;

    private final String name;

    ButtonLabel(String name) {
        this.name = name;
    }

    public String getName() {
        return getName(Locale.ENGLISH);
    }

    public String getName(Locale locale) {
        return name;
    }
}


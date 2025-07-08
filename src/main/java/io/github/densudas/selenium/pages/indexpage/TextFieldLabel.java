package io.github.densudas.selenium.pages.indexpage;

import java.util.Locale;

public enum TextFieldLabel implements TranslatableLocatorName {

    SEARCH("Search");

    private final String name;

    TextFieldLabel(String name) {
        this.name = name;
    }

    public String getName() {
        return getName(Locale.ENGLISH);
    }

    public String getName(Locale locale) {
        return name;
    }
}

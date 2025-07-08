package io.github.densudas.selenium.pages.indexpage;

import java.util.Locale;

public enum LinkLabel implements TranslatableLocatorName {

    SEARCH_BBC("Search BBC");

    private String name;

    LinkLabel(String name) {
        this.name = name;
    }

    public String getName() {
        return getName(Locale.ENGLISH);
    }

    public String getName(Locale locale) {
        return name;
    }
}

package io.github.densudas.selenium.modals;

import java.util.Locale;

public enum PersonalDataModalLabels implements TranslatableLocatorName {
    MANAGE_OPTIONS("Manage options"),
    CONSENT("Consent");

    private final String name;

    PersonalDataModalLabels(String name) {
        this.name = name;
    }

    public String getName() {
        return getName(Locale.ENGLISH);
    }

    public String getName(Locale locale) {
        return name;
    }
}

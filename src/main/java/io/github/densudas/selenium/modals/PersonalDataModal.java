package io.github.densudas.selenium.modals;

import io.github.densudas.selenium.controls.Button;

public class PersonalDataModal<T> implements Locatable {

    private final T parentPage;

    public PersonalDataModal(T parentPage) {
        this.parentPage = parentPage;
    }

    public T clickConsentButton() throws Exception {
        new Button(this, PersonalDataModalLabels.CONSENT).findControl().click();
        return parentPage;
    }

    public PersonalDataModal<T> click() throws Exception {
        new Button(this, PersonalDataModalLabels.CONSENT).findControl().click();
        return this;
    }

    @Override
    public String getLocation() {
        return ((Locatable) parentPage).getLocation() + "." + this.getClass().getSimpleName();
    }
}

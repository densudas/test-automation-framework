package io.github.densudas.selenium.components;

public abstract class LoadableComponent<T extends LoadableComponent<T>> {

    protected LoadableComponent() {
        waitToLoad();
    }

    abstract T waitToLoad();
}

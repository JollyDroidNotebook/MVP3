package ru.jollydroid.mvp1;

import android.app.Application;

public class DemoApplication extends Application {

    private ApplicationComponent component;

    private ApplicationComponent createComponent() {
        return DaggerApplicationComponent
                .builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public ApplicationComponent component() {
        if (component == null) {
            synchronized (this) {
                if (component == null) {
                    component = createComponent();
                }
            }
        }

        return component;
    }

    // TODO нужно перенести этот метод в ветку с тестами
    public void setComponent(ApplicationComponent component) {
        this.component = component;
    }
}


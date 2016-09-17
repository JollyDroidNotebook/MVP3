package ru.jollydroid.mvp1;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tse on 08/09/16.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        MainModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);

    MainPresenter mainPresenter();
}

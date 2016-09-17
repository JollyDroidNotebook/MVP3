package ru.jollydroid.mvp1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tse on 17/09/16.
 */
@Module
public class MainModule {
    @Provides
    @Singleton
    MainPresenter providesMainPresenter(ClickCounterModel model) {
        return new MainPresenterImpl(model);
    }
}

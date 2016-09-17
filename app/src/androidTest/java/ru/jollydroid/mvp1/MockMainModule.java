package ru.jollydroid.mvp1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by tse on 17/09/16.
 */
@Module
public class MockMainModule {
    @Provides
    @Singleton
    MainPresenter providesMainPresenter(ClickCounterModel model) {
        return mock(MainPresenter.class);
    }
}

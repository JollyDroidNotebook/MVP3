package ru.jollydroid.mvp1;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by tse on 12/08/16.
 */
public class MainPresenterImpl
    extends MvpBasePresenter<MainView>
    implements MainPresenter
{
    private final ClickCounterModel model;

    public MainPresenterImpl(ClickCounterModel model) {
        this.model = model;
    }

    @Override
    public void buttonPressed() {
        model.increaseCounter();

        if (isViewAttached()) {
            getView().showCounter(model.getCount());
        }
    }
}

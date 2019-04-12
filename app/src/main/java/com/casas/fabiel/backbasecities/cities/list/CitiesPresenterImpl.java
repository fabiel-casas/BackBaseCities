package com.casas.fabiel.backbasecities.cities.list;

import android.content.Context;
import android.os.Handler;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CitiesPresenterImpl implements Cities.Presenter {

    public static final int DELAY_MILLIS = 1000;
    private final WeakReference<Cities.View> view;
    private final CitiesModelImpl model;

    public CitiesPresenterImpl(Cities.View view, @NotNull Context context) {
        this.view = new WeakReference<>(view);
        model = new CitiesModelImpl(this, context);
    }

    @Override
    public void getCities() {
        new Handler().postDelayed(() -> model.getCities(), DELAY_MILLIS);
    }

    @Override
    public void updateCitesList(ArrayList<CityInfo> cityInfoList) {
        Cities.View cityView = view.get();
        if (cityView != null) {
            cityView.updateCitiesOnAdapter(cityInfoList);
        }
    }

    @Override
    public void filterBy(final String text) {
        new Handler().postDelayed(() -> model.filterBy(text), DELAY_MILLIS);
    }

    @Override
    public void clearFilter() {
        model.clearFilter();
    }
}

package com.casas.fabiel.backbasecities.cities.list;

import android.content.Context;
import android.os.Handler;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CitiesPresenterImpl implements Cities.Presenter {

    private final WeakReference<Cities.View> view;
    private final CitiesModelImpl model;

    public CitiesPresenterImpl(Cities.View view, @NotNull Context context) {
        this.view = new WeakReference<>(view);
        model = new CitiesModelImpl(this, context);
    }

    @Override
    public void getCities() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                model.getCities();
            }
        }, 1000);
    }

    @Override
    public void updateCitesList(ArrayList<CityInfo> cityInfoList) {
        Cities.View cityView = view.get();
        if (cityView != null) {
            cityView.updateCitiesOnAdapter(cityInfoList);
        }
    }
}

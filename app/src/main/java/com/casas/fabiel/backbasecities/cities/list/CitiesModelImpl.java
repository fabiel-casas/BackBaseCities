package com.casas.fabiel.backbasecities.cities.list;

import android.content.Context;
import com.casas.fabiel.backbasecities.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CitiesModelImpl implements Cities.Model {

    private final Cities.Presenter presenter;
    private final WeakReference<Context> context;
    private static final String FILE_NAME = "cities.json";

    public CitiesModelImpl(Cities.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getCities() {
        String cityListJson = new FileReader(FILE_NAME, context.get()).read();
        ArrayList<CityInfo> cityInfoList = new Gson().fromJson(cityListJson, new TypeToken<ArrayList<CityInfo>>(){}.getType());
        presenter.updateCitesList(cityInfoList);
    }
}

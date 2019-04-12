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
    private ArrayList<CityInfo> cityInfoList;

    public CitiesModelImpl(Cities.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getCities() {
        String cityListJson = new FileReader(FILE_NAME, context.get()).read();
        cityInfoList = new Gson().fromJson(cityListJson, new TypeToken<ArrayList<CityInfo>>(){}.getType());
        presenter.updateCitesList(cityInfoList);
    }

    @Override
    public void filterBy(final String filter) {
        ArrayList<CityInfo> itemList = new ArrayList<>();
        for (CityInfo city : cityInfoList) {
            if (city.getName().toLowerCase().startsWith(filter.toLowerCase())) {
                itemList.add(city);
            }
        }
        presenter.updateCitesList(itemList);
    }

    @Override
    public void clearFilter() {
        presenter.updateCitesList(cityInfoList);
    }
}

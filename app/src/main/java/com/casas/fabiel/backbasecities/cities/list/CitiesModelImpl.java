package com.casas.fabiel.backbasecities.cities.list;

import android.content.Context;
import com.casas.fabiel.backbasecities.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

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
        Collections.sort(cityInfoList, (cityInfoOne, cityInfoTwo) ->
                cityInfoTwo.getName().compareTo(cityInfoOne.getName()));
        presenter.updateCitesList(cityInfoList);
    }

    @Override
    public void filterBy(final String filter) {
        ArrayList<CityInfo> itemList = seekOnList(filter, cityInfoList);
        Collections.sort(itemList, (cityInfoOne, cityInfoTwo) ->
                cityInfoTwo.getName().compareTo(cityInfoOne.getName()));
        presenter.updateCitesList(itemList);
    }

    @NotNull
    public ArrayList<CityInfo> seekOnList(String filter, ArrayList<CityInfo> cityList) {
        ArrayList<CityInfo> itemList = new ArrayList<>();
        for (CityInfo city : cityList) {
            if (city.getName().toLowerCase().startsWith(filter.toLowerCase())) {
                itemList.add(city);
            }
        }
        return itemList;
    }

    @Override
    public void clearFilter() {
        presenter.updateCitesList(cityInfoList);
    }
}

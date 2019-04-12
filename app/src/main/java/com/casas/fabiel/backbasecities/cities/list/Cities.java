package com.casas.fabiel.backbasecities.cities.list;

import java.util.ArrayList;

public interface Cities {
    interface Model {
        void getCities();
        void filterBy(String text);
        void clearFilter();
    }

    interface Presenter {
        void getCities();
        void updateCitesList(ArrayList<CityInfo> cityInfoList);
        void filterBy(String text);
        void clearFilter();
    }

    interface View {
        void updateCitiesOnAdapter(ArrayList<CityInfo> cityInfoList);
    }
}

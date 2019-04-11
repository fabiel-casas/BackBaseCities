package com.casas.fabiel.backbasecities.cities.list;

import java.util.ArrayList;
import java.util.List;

public interface Cities {
    interface Model {
        void getCities();
    }

    interface Presenter {
        void getCities();
        void updateCitesList(ArrayList<CityInfo> cityInfoList);
    }

    interface View {
        void updateCitiesOnAdapter(List<CityInfo> cityInfoList);
    }
}

package com.casas.fabiel.backbasecities;

import com.casas.fabiel.backbasecities.cities.list.CityInfo;

public interface MasterListInteraction {

    interface OnSearchListener {
        void searchItem(String text);
    }

    interface OnCityFragmentInteractionListener {
        void onCityInteraction(CityInfo item);
    }
}

package com.casas.fabiel.backbasecities;

import com.casas.fabiel.backbasecities.cities.list.CityInfo;

public interface MasterListInteraction {

    interface OnSearchListener {
        void searchItem(String text);
        void searchClosed();
    }

    interface OnCityFragmentInteractionListener {
        void onCityInteraction(CityInfo item);
    }
}

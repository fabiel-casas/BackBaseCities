package com.casas.fabiel.backbasecities;

import android.content.Context;
import com.casas.fabiel.backbasecities.cities.list.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static com.casas.fabiel.backbasecities.CityInfoTest.*;
import static org.junit.Assert.assertEquals;

public class SeekerTest {

    private ArrayList<CityInfo> list;
    private CitiesModelImpl model;

    @Before
    public void init() {
        list = new ArrayList<>();
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(LAT);
        coordinates.setLon(LON);
        for (int i = 0; i < 10; i++) {
            CityInfo cityInfo = new CityInfo();
            cityInfo.setCoord(coordinates);
            cityInfo.setName("city".concat(String.valueOf(i)));
            cityInfo.set_id(ID.concat(String.valueOf(i)));
            cityInfo.setCountry(COLOMBIA);
            list.add(cityInfo);
        }
        CityInfo cityInfoBog = new CityInfo();
        cityInfoBog.setCoord(coordinates);
        cityInfoBog.setName(BOGOTA);
        cityInfoBog.set_id(ID);
        cityInfoBog.setCountry(COLOMBIA);
        list.add(cityInfoBog);
        CitiesPresenterImpl view = Mockito.mock(CitiesPresenterImpl.class);
        Context context = Mockito.mock(Context.class);
        model = new CitiesModelImpl(view, context);
    }

    @Test
    public void findBogota() {
        ArrayList<CityInfo> result = model.seekOnList("Bog", list);
        assertEquals(1, result.size());
    }

    @Test
    public void findCity() {
        ArrayList<CityInfo> result = model.seekOnList("cit", list);
        assertEquals(10, result.size());
    }

    @Test
    public void findEmpty() {
        ArrayList<CityInfo> result = model.seekOnList("col", list);
        assertEquals(0, result.size());
    }
}

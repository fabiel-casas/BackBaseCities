package com.casas.fabiel.backbasecities;

import com.casas.fabiel.backbasecities.cities.list.CityInfo;
import com.casas.fabiel.backbasecities.cities.list.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityInfoTest {

    public static final String COLOMBIA = "Colombia";
    public static final String BOGOTA = "Bogota";
    public static final String ID = "43234";
    public static final String LAT = "43.3234";
    public static final String LON = "123.3234";
    private CityInfo cityInfo;

    @Before
    public void init() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(LAT);
        coordinates.setLon(LON);
        cityInfo = new CityInfo();
        cityInfo.setCoord(coordinates);
        cityInfo.setName(BOGOTA);
        cityInfo.set_id(ID);
        cityInfo.setCountry(COLOMBIA);
    }

    @Test
    public void cityInfo() {
        assertEquals(cityInfo.get_id(), ID);
        assertEquals(cityInfo.getName(), BOGOTA);
        assertEquals(cityInfo.getCountry(), COLOMBIA);
    }

    @Test
    public void coordintesInfo() {
        assertEquals(cityInfo.getCoord().getLat(), LAT);
        assertEquals(cityInfo.getCoord().getLon(), LON);
        assertEquals(cityInfo.getCoord().getRealLat(), 43.3234d, 0.01d);
        assertEquals(cityInfo.getCoord().getRealLon(), 123.3234d, 0.01d);
    }
}

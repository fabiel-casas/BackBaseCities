package com.casas.fabiel.backbasecities.cities.list;

import android.os.Parcel;
import android.os.Parcelable;

public class Coordinates implements Parcelable {
    private String lon;
    private String lat;

    protected Coordinates(Parcel in) {
        lon = in.readString();
        lat = in.readString();
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    public String getLon() {
        return lon;
    }

    public Double getRealLon() {
        try {
            return Double.valueOf(lon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0d;
    }

    public Double getRealLat() {
        try {
            return Double.valueOf(lat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0d;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lon);
        parcel.writeString(lat);
    }
}

package com.casas.fabiel.backbasecities.cities.list;

import android.os.Parcel;
import android.os.Parcelable;

public class CityInfo implements Parcelable {
    private String country;
    private String name;
    private String _id;
    private Coordinates coord;

    public CityInfo() {
    }

    protected CityInfo(Parcel in) {
        country = in.readString();
        name = in.readString();
        _id = in.readString();
        coord = in.readParcelable(Coordinates.class.getClassLoader());
    }

    public static final Creator<CityInfo> CREATOR = new Creator<CityInfo>() {
        @Override
        public CityInfo createFromParcel(Parcel in) {
            return new CityInfo(in);
        }

        @Override
        public CityInfo[] newArray(int size) {
            return new CityInfo[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeString(name);
        parcel.writeString(_id);
        parcel.writeParcelable(coord, i);
    }
}

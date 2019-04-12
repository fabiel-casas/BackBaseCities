package com.casas.fabiel.backbasecities.cities.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.casas.fabiel.backbasecities.MasterListInteraction;
import com.casas.fabiel.backbasecities.R;

import java.util.ArrayList;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    public static final String COMMA = ", ";
    private ArrayList<CityInfo> cities;
    private final MasterListInteraction.OnCityFragmentInteractionListener listener;

    public CityRecyclerViewAdapter(ArrayList<CityInfo> items, MasterListInteraction.OnCityFragmentInteractionListener listener) {
        cities = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CityInfo cityInfo = cities.get(position);
        holder.itemInfo = cityInfo;
        holder.city.setText(cityInfo.getName().trim().concat(COMMA));
        holder.country.setText(cityInfo.getCountry().trim());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void updateItems(ArrayList<CityInfo> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView city;
        public final TextView country;
        public CityInfo itemInfo;

        public ViewHolder(View view) {
            super(view);
            city = (TextView) view.findViewById(R.id.city);
            country = (TextView) view.findViewById(R.id.country);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        listener.onCityInteraction(itemInfo);
                    }
                }
            });
        }
    }
}

package com.casas.fabiel.backbasecities.cities.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.casas.fabiel.backbasecities.R;
import com.casas.fabiel.backbasecities.cities.list.CityFragment.OnListFragmentInteractionListener;

import java.util.List;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    private final List<CityInfo> cities;
    private final OnListFragmentInteractionListener listener;

    public CityRecyclerViewAdapter(List<CityInfo> items, OnListFragmentInteractionListener listener) {
        cities = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CityInfo cityInfo = cities.get(position);
        holder.itemInfo = cityInfo;
        holder.city.setText(cityInfo.getName());
        holder.country.setText(cityInfo.getCountry());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView city;
        public final TextView country;
        public CityInfo itemInfo;

        public ViewHolder(View view) {
            super(view);
            city = (TextView) view.findViewById(R.id.item_number);
            country = (TextView) view.findViewById(R.id.content);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        listener.onListFragmentInteraction(itemInfo);
                    }
                }
            });
        }
    }
}

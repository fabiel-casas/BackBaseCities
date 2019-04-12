package com.casas.fabiel.backbasecities.cities.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.casas.fabiel.backbasecities.MasterListInteraction;
import com.casas.fabiel.backbasecities.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class CityFragment extends Fragment implements Cities.View, MasterListInteraction.OnSearchListener {

    private MasterListInteraction.OnCityFragmentInteractionListener listener;
    private CitiesPresenterImpl presenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CityRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CitiesPresenterImpl(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration itemDecor = new DividerItemDecoration(context, VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        adapter = new CityRecyclerViewAdapter(new ArrayList<CityInfo>(), listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCities();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MasterListInteraction.OnCityFragmentInteractionListener) {
            listener = (MasterListInteraction.OnCityFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void updateCitiesOnAdapter(ArrayList<CityInfo> cityInfoList) {
        adapter.updateItems(cityInfoList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void searchItem(@NotNull String text) {
        presenter.filterBy(text);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void searchClosed() {
        presenter.clearFilter();
        progressBar.setVisibility(View.GONE);
    }
}

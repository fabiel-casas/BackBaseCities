package com.casas.fabiel.backbasecities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.casas.fabiel.backbasecities.cities.list.CityFragment;
import com.casas.fabiel.backbasecities.cities.list.CityInfo;

public class MainActivity extends AppCompatActivity implements MasterListInteraction.OnCityFragmentInteractionListener {

    private MasterListInteraction.OnSearchListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CityFragment fragment = new CityFragment();
        listener = fragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(searListener());
        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener searListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listener.searchItem(s);
                return false;
            }
        };
    }

    @Override
    public void onCityInteraction(CityInfo item) {

    }
}

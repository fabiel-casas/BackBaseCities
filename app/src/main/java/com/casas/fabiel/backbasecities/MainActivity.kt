package com.casas.fabiel.backbasecities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.casas.fabiel.backbasecities.cities.list.CityFragment
import com.casas.fabiel.backbasecities.cities.list.CityInfo

class MainActivity : AppCompatActivity(), CityFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, CityFragment()).commit()
    }

    override fun onListFragmentInteraction(item: CityInfo?) {

    }
}

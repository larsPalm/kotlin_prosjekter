package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navBottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBottom.setOnNavigationItemSelectedListener(navListener)

        // I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            val dummy = ConverterFragment()
            dummy.bw = true
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                dummy
            ).commit()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        val weigthsFragment = ConverterFragment()
        weigthsFragment.bw = true
        val distFragment = ConverterFragment()
        distFragment.bd = true
        val fluidFragment = ConverterFragment()
        fluidFragment.bf = true

        when (item.itemId) {
            R.id.btnWeights -> selectedFragment = weigthsFragment
            R.id.btnDist -> selectedFragment = distFragment
            R.id.btnFluid -> selectedFragment = fluidFragment
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            selectedFragment!!
        ).commit()

        true
    }
}
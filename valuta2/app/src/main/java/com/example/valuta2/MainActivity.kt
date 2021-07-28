package com.example.valuta2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navBottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBottom.setOnNavigationItemSelectedListener(navListener)
        DataInterface(this).data
        // I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            val dummy = ConverterFragment(this)
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                dummy
            ).commit()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        val convertFragment = ConverterFragment(this)
        val imgFragment = ImgFragment(this)
        val compareFragment = CompareFragment(this)

        when (item.itemId) {
            R.id.btnConvert -> selectedFragment = convertFragment
            R.id.btnCompare -> selectedFragment = imgFragment
            R.id.btnCompMult -> selectedFragment = compareFragment
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            selectedFragment!!
        ).commit()

        true
    }
}
package com.example.valutakurs_csv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    public val parser:DataParser = DataParser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameList =mutableListOf("dkk.csv","dollar.csv","euro.csv","pund.csv","sek.csv")
        for((b, name) in nameList.withIndex()) {
            val fileText = applicationContext.assets.open(name).bufferedReader().use{
                it.readLines()
            }
            parser.parseFile(fileText,b)
            Toast.makeText(this,"ikke ferdig lastet inn",Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show()
        parser.lagNorge()
        val navBottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBottom.setOnNavigationItemSelectedListener(navListener)

        // I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                CalculateFragment()
            ).commit()
        }
    }
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        val calcFragment = CalculateFragment()
        val compChartFragment = ChartFragment()
        val devChartFragment = ChartFragment()
        devChartFragment.devCompare = true

        when (item.itemId) {
            R.id.nav_calculate -> selectedFragment = calcFragment
            R.id.nav_chart_comp -> selectedFragment = compChartFragment
            R.id.nav_chart_dev -> selectedFragment = devChartFragment
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            selectedFragment!!).commit()

        true
    }
}

package com.example.kotlinvalutaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    var spinnerFrom: Spinner? = null
    var spinnerTo: Spinner? = null
    var tw: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataInterface(this).data
        val aList: MutableList<String> = SharedPrefInterface(this).baseCur!!
        Log.d(TAG,aList.toString())
        val spinnerArrayAdapter = ArrayAdapter(
            this, R.layout.spinner_item, aList
        )
        Collections.sort(aList)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinnerTo = findViewById<View>(R.id.to_cur) as Spinner
        spinnerFrom = findViewById<View>(R.id.from_cur) as Spinner
        spinnerTo!!.adapter = spinnerArrayAdapter
        spinnerFrom!!.adapter = spinnerArrayAdapter
        tw = findViewById(R.id.twUt)
    }

    fun toImg(view: View) {
        val intent = Intent(this@MainActivity, ImgActivity::class.java)
        startActivity(intent)
    }
    fun convert(view: View) {
        val editText = findViewById<EditText>(R.id.ammount)
        val stringAmmount = editText.text.toString()
        if ("" != stringAmmount) {
            val value = stringAmmount.toDouble()
            val c1 = SharedPrefInterface(this).getValue(spinnerFrom!!.selectedItem.toString())
            val c2 = SharedPrefInterface(this).getValue(spinnerTo!!.selectedItem.toString())
            val newVal = c1 / c2 * value
            val from = spinnerFrom!!.selectedItem.toString()
            val to = spinnerTo!!.selectedItem.toString()
            tw!!.text = String.format("%.2f %s = %.2f %s", value, from, newVal, to)
        }
    }
}
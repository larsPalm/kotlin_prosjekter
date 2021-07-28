package com.example.valuta2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class ConverterFragment(var context2: Context) : Fragment(){
    var spinnerFrom: Spinner? = null
    var spinnerTo: Spinner? = null
    var tw: TextView? = null
    val TAG = "ConverterFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataInterface(context2).data
        var aList: MutableList<String> = mutableListOf<String>()
        try{
            aList = SharedPrefInterface(context2).baseCur!!
        }catch (e:Exception){
            Toast.makeText(context2,"list of currencies is empty at the moment",Toast.LENGTH_LONG).show()
        }
        Log.d(TAG,aList.toString())
        val spinnerArrayAdapter = ArrayAdapter(
            context2, R.layout.spinner_item, aList
        )
        Collections.sort(aList)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinnerTo = view.findViewById<View>(R.id.to_cur) as Spinner
        spinnerFrom = view.findViewById<View>(R.id.from_cur) as Spinner
        spinnerTo!!.adapter = spinnerArrayAdapter
        spinnerFrom!!.adapter = spinnerArrayAdapter
        tw = view.findViewById(R.id.twUt)
        val bt = view.findViewById<Button>(R.id.btnSubmit)
        bt.setOnClickListener{
            val editText = view.findViewById<EditText>(R.id.ammount)
            val stringAmmount = editText.text.toString()
            if ("" != stringAmmount) {
                val value = stringAmmount.toDouble()
                val c1 = SharedPrefInterface(context2).getValue(spinnerFrom!!.selectedItem.toString())
                val c2 = SharedPrefInterface(context2).getValue(spinnerTo!!.selectedItem.toString())
                val newVal = c1 / c2 * value
                val from = spinnerFrom!!.selectedItem.toString()
                val to = spinnerTo!!.selectedItem.toString()
                tw!!.text = String.format("%.2f %s = %.2f %s", value, from, newVal, to)
            }
        }
    }
}
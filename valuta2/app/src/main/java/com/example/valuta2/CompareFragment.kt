package com.example.valuta2

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog
import com.anurag.multiselectionspinner.MultiSpinner
import java.util.*


class CompareFragment (var context2: Context): Fragment(),
    MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener{
    var TAG = "CompareFragment"
    var spinnerBase: Spinner? = null
    var img: ImageView? = null
    val options = mutableSetOf<String>()
    var multiSpinner : MultiSpinner? = null
    private var viewModel: ViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_compare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val aList:MutableList<String> = SharedPrefInterface(context2).baseCur!!
        val spinnerArrayAdapter = ArrayAdapter(
            context2, R.layout.spinner_item, aList
        )
        Collections.sort(aList)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item2)
        spinnerBase = view.findViewById<View>(R.id.base_cur) as Spinner
        multiSpinner = view.findViewById<MultiSpinner>(R.id.spinnerMultiSpinner)
        img = view.findViewById<View>(R.id.img) as ImageView
        spinnerBase!!.adapter = spinnerArrayAdapter
        multiSpinner!!.setAdapterWithOutImage(context2,aList,this)
        multiSpinner!!.initMultiSpinner(context,multiSpinner)
        val bt = view.findViewById<Button>(R.id.btnSubmit)
        bt.setOnClickListener{
            val v1 = spinnerBase!!.selectedItem.toString()
            var others = ""
            for(elm in options){
                others+= elm+"_"
            }
            DataInterface(context2).getImgMult(v1, others)
            DataInterface(context2).storedImg2
            Log.d(TAG,SharedPrefInterface(context2).getImg2())
            try{
                val decodedString = Base64.decode(SharedPrefInterface(context2).getImg2(), Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                img!!.setImageBitmap(decodedByte)
            }catch(e:Exception){}
        }
    }
    override fun OnMultiSpinnerItemSelected(chosenItems: MutableList<String>?) {

        //This is where you get all your items selected from the Multi Selection Spinner :)
        for (i in chosenItems!!.indices){

            //Log.d(TAG,chosenItems[i])
            options.add(chosenItems[i])
        }
    }
}
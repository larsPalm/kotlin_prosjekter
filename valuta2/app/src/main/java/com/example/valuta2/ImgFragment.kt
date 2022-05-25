package com.example.valuta2

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

class ImgFragment(var context2: Context): Fragment(){
    var TAG = "ImgFragment"
    var spinnerFrom: Spinner? = null
    var spinnerTo: Spinner? = null
    var img: ImageView? = null
    private var viewModel: ViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_img, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val aList:MutableList<String> = SharedPrefInterface(context2).baseCur!!
        val spinnerArrayAdapter = ArrayAdapter(
            context2, R.layout.spinner_item, aList
        )
        Collections.sort(aList)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item2)
        spinnerTo = view.findViewById<View>(R.id.to_cur) as Spinner
        spinnerFrom = view.findViewById<View>(R.id.from_cur) as Spinner
        spinnerTo!!.adapter = spinnerArrayAdapter
        spinnerFrom!!.adapter = spinnerArrayAdapter
        img = view.findViewById<View>(R.id.img) as ImageView
        DataInterface(context2).storedImg
        val bt = view.findViewById<Button>(R.id.btnSubmit)
        bt.setOnClickListener{
            val v1 = spinnerFrom!!.selectedItem.toString()
            val v2 = spinnerTo!!.selectedItem.toString()
            DataInterface(context2).getImg(v1, v2)
            Log.d(TAG, SharedPrefInterface(context2).img!!)
            DataInterface(context2).storedImg
            try{
                val decodedString = Base64.decode(SharedPrefInterface(context2).img, Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                img!!.setImageBitmap(decodedByte)
            }catch(e:Exception){}
        }
    }
}

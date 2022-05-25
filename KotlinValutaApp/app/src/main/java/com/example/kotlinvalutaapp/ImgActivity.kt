package com.example.kotlinvalutaapp

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*


class ImgActivity : AppCompatActivity() {
    var TAG = "ImgActivity"
    var spinnerFrom: Spinner? = null
    var spinnerTo: Spinner? = null
    var activity: Activity? = null
    var img: ImageView? = null
    private var viewModel: ViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)
        val aList:MutableList<String> = SharedPrefInterface(this).baseCur!!
        val spinnerArrayAdapter = ArrayAdapter(
            this, R.layout.spinner_item, aList
        )
        Collections.sort(aList)
        activity = this
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item2)
        spinnerTo = findViewById<View>(R.id.to_cur) as Spinner
        spinnerFrom = findViewById<View>(R.id.from_cur) as Spinner
        spinnerTo!!.adapter = spinnerArrayAdapter
        spinnerFrom!!.adapter = spinnerArrayAdapter
        img = findViewById<View>(R.id.img) as ImageView
        viewModel = ViewModelProvider(this)[ImgViewModel::class.java]
        val imgObserver: Observer<String> =
            Observer { imgString ->
                try{
                    val decodedString = Base64.decode(imgString, Base64.DEFAULT)
                    val decodedByte =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    img!!.setImageBitmap(decodedByte)
                }catch(e:Exception){}
            }
        (viewModel as ImgViewModel).currentImg.observe(this, imgObserver)
        DataInterface(this).storedImg
    }

    fun toMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun fetchImg(view: View) {
        val v1 = spinnerFrom!!.selectedItem.toString()
        val v2 = spinnerTo!!.selectedItem.toString()
        DataInterface(this).getImg(v1, v2)
        DataInterface(this).storedImg
        try{
            val decodedString = Base64.decode(SharedPrefInterface(this).img, Base64.DEFAULT)
            val decodedByte =
                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            img!!.setImageBitmap(decodedByte)
        }catch(e:Exception){}
    }
}
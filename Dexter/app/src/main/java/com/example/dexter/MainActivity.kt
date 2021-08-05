package com.example.dexter

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var name: TextView? = null
    private var type: TextView? = null
    private var vekt: TextView? = null
    private var attribut: TextView? = null
    private var noImg: TextView? = null
    private var img: ImageView? = null
    private var number: TextView? = null
    private var valg:EditText? = null
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bt = findViewById<Button>(R.id.btnGetPokemon)
        bt.setBackgroundColor(Color.WHITE)
        var bt2 = findViewById<Button>(R.id.btnRefresh)
        bt2.setBackgroundColor(Color.WHITE);
        name = findViewById(R.id.pName)
        type = findViewById(R.id.pType)
        vekt = findViewById(R.id.pVekt)
        attribut = findViewById(R.id.pAtt)
        noImg = findViewById(R.id.noImg)
        number = findViewById(R.id.number)
        img = findViewById(R.id.img)
        valg = findViewById(R.id.pokemonText)
        valg!!.setImeOptions(EditorInfo.IME_ACTION_DONE)

    }

    fun getPokemon(view: View) {
        val numberName = valg!!.text.toString()
        if(numberName.equals("")==false){
            DataInterface(this).getInformation(numberName)
        }
        if(SharedPrefInterface(this).getStoredMsg().equals("")){
            name!!.setText("name: "+SharedPrefInterface(this).getStoredName())
            type!!.setText("type(s): "+SharedPrefInterface(this).getStoredType())
            attribut!!.setText("attributes: "+SharedPrefInterface(this).getStoredAttr())
            vekt!!.setText("vekt: "+SharedPrefInterface(this).getStoredVekt().toString())
            number!!.setText("number: "+SharedPrefInterface(this).getStoredNumber().toString())
            try{
                val decodedString = Base64.decode(SharedPrefInterface(this).getStoredImg(), Base64.DEFAULT)
                val decodedByte =
                                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                img!!.setImageBitmap(decodedByte)
            }catch(e:Exception){
                noImg!!.setText("no image available")
            }
        }
        else{
            noImg!!.setText("some error occured")
        }
        closeSoftKeyboard(this, valg!!)
        
    }

    fun refresh(view: View) {
        if(SharedPrefInterface(this).getStoredMsg().equals("")){
            name!!.setText("name: "+SharedPrefInterface(this).getStoredName())
            type!!.setText("type(s): "+SharedPrefInterface(this).getStoredType())
            attribut!!.setText("attributes: "+SharedPrefInterface(this).getStoredAttr())
            vekt!!.setText("vekt: "+SharedPrefInterface(this).getStoredVekt().toString())
            number!!.setText("number: "+SharedPrefInterface(this).getStoredNumber().toString())
            try{
                val decodedString = Base64.decode(SharedPrefInterface(this).getStoredImg(), Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                img!!.setImageBitmap(decodedByte)
            }catch(e:Exception){
               noImg!!.setText("no image available")
            }
        }
        else{
           noImg!!.setText("some error occured")
        }
    }

    private fun closeSoftKeyboard(context: Context, v: View) {
            val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            iMm.hideSoftInputFromWindow(v.windowToken, 0)
            v.clearFocus()
        }
}
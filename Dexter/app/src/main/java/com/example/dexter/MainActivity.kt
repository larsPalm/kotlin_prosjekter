package com.example.dexter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
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
    private var imgLayout:LinearLayout? = null
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bt = findViewById<Button>(R.id.btnGetPokemon)
        bt.setBackgroundColor(Color.WHITE)
        var bt2 = findViewById<Button>(R.id.btnRefresh)
        bt2.setBackgroundColor(Color.WHITE);
        var btn3 = findViewById<Button>(R.id.btnGoToAttack)
        btn3.setBackgroundColor(Color.WHITE);
        var btn4 = findViewById<Button>(R.id.btnSearch1PlusNumber)
        btn4.setBackgroundColor(Color.WHITE);
        var btn5 = findViewById<Button>(R.id.btnSearch10PlusNumber)
        btn5.setBackgroundColor(Color.WHITE);
        var btn6 = findViewById<Button>(R.id.btnSearch10MinusNumber)
        btn6.setBackgroundColor(Color.WHITE);
        var btn7 = findViewById<Button>(R.id.btnSearch1MinusNumber)
        btn7.setBackgroundColor(Color.WHITE);
        name = findViewById(R.id.pName)
        type = findViewById(R.id.pType)
        vekt = findViewById(R.id.pVekt)
        attribut = findViewById(R.id.pAtt)
        noImg = findViewById(R.id.noImg)
        number = findViewById(R.id.number)
        img = findViewById(R.id.img)
        valg = findViewById(R.id.pokemonText)
        valg!!.setImeOptions(EditorInfo.IME_ACTION_DONE)
        imgLayout = findViewById(R.id.imgLayout)
        imgLayout!!.visibility = View.INVISIBLE

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
                noImg!!.setText("")
                imgLayout!!.visibility = View.VISIBLE
            }catch(e:Exception){
                name!!.setText("")
                type!!.setText("")
                attribut!!.setText("")
                vekt!!.setText("")
                number!!.setText("")
                noImg!!.setText("no image available")
                imgLayout!!.visibility = View.INVISIBLE
            }
        }
        else{
            name!!.setText("")
            type!!.setText("")
            attribut!!.setText("")
            vekt!!.setText("")
            number!!.setText("")
            noImg!!.setText("some error occured")
            imgLayout!!.visibility = View.INVISIBLE
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
                noImg!!.setText("")
                imgLayout!!.visibility = View.VISIBLE
            }catch(e:Exception){
                name!!.setText("")
                type!!.setText("")
                attribut!!.setText("")
                vekt!!.setText("")
                number!!.setText("")
                noImg!!.setText("no image available")
                imgLayout!!.visibility = View.INVISIBLE
            }
        }
        else{
            name!!.setText("")
            type!!.setText("")
            attribut!!.setText("")
            vekt!!.setText("")
            number!!.setText("")
           noImg!!.setText("some error occured")
            imgLayout!!.visibility = View.INVISIBLE

        }
    }

    private fun closeSoftKeyboard(context: Context, v: View) {
            val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            iMm.hideSoftInputFromWindow(v.windowToken, 0)
            v.clearFocus()
        }

    fun goToAttack(view: View) {
        val intent = Intent(this, AttackActivity::class.java)
        startActivity(intent)
    }

    fun search10PlusNumber(view: View) {
        var numberName = SharedPrefInterface(this).getStoredNumber()
        val msg = SharedPrefInterface(this).getStoredMsg()
        var newNumber = 1
        if(msg.equals("")){
            try{
                newNumber = numberName!!
            }catch (e:Exception){newNumber = 1}
            Log.d(TAG, newNumber.toString());
            if(newNumber in 1..887){
                newNumber += 10
                DataInterface(this).getInformation(newNumber.toString())
            } else{
                DataInterface(this).getInformation(newNumber.toString())
            }
        }else{
            DataInterface(this).getInformation(newNumber.toString())
        }
        refresh(view)
    }
    fun search10MinusNumber(view: View) {
        var numberName = SharedPrefInterface(this).getStoredNumber()
        val msg = SharedPrefInterface(this).getStoredMsg()
        var newNumber = 1
        if(msg.equals("")){
            try{
                newNumber = numberName!!
            }catch (e:Exception){newNumber = 1}
            Log.d(TAG, newNumber.toString());
            if(newNumber in 11..897){
                newNumber -= 10
                DataInterface(this).getInformation(newNumber.toString())
            } else{
                DataInterface(this).getInformation(newNumber.toString())
            }
        }else{
            DataInterface(this).getInformation(newNumber.toString())
        }
        refresh(view)
    }
    fun search1PlusNumber(view: View) {
        var numberName = SharedPrefInterface(this).getStoredNumber()
        val msg = SharedPrefInterface(this).getStoredMsg()
        var newNumber = 1
        if(msg.equals("")){
            try{
                newNumber = numberName!!
            }catch (e:Exception){newNumber = 1}
            Log.d(TAG, newNumber.toString());
            if(newNumber in 1..896){
                newNumber += 1
                DataInterface(this).getInformation(newNumber.toString())
            } else{
                DataInterface(this).getInformation(newNumber.toString())
            }
        }else{
            DataInterface(this).getInformation(newNumber.toString())
        }
        refresh(view)
    }
    fun search1MinusNumber(view: View) {
        var numberName = SharedPrefInterface(this).getStoredNumber()
        val msg = SharedPrefInterface(this).getStoredMsg()
        var newNumber = 1
        Log.d(TAG,"-----"+msg)
        Log.d(TAG,"......."+msg.equals("").toString())
        if(msg.equals("")){
            try{
                newNumber = numberName!!
            }catch (e:Exception){newNumber = 1}
            Log.d(TAG, newNumber.toString());
            if(newNumber in 2..897){
                newNumber -= 1
                DataInterface(this).getInformation(newNumber.toString())
            } else{
                DataInterface(this).getInformation(newNumber.toString())
            }
        }else{
            DataInterface(this).getInformation(newNumber.toString())
        }
        refresh(view)
    }
}
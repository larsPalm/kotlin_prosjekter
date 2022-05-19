package com.example.dexter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class AttackActivity : AppCompatActivity() {
    private var valg: EditText? = null
    private var name: TextView? = null
    private var type: TextView? = null
    private var power: TextView? = null
    private var acc: TextView? = null
    private var dc: TextView? = null
    private var count: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attack)
        var bt = findViewById<Button>(R.id.btnGetAttack)
        bt.setBackgroundColor(Color.WHITE)
        var bt2 = findViewById<Button>(R.id.btnRefreshAttack)
        bt2.setBackgroundColor(Color.WHITE);
        var bt3 = findViewById<Button>(R.id.btnGoToPokemon)
        bt3.setBackgroundColor(Color.WHITE);
        valg = findViewById(R.id.attackText)
        valg!!.setImeOptions(EditorInfo.IME_ACTION_DONE)
        name = findViewById(R.id.aName)
        type = findViewById(R.id.aType)
        power = findViewById(R.id.aPower)
        acc = findViewById(R.id.aAcc)
        dc = findViewById(R.id.aDc)
        count = findViewById(R.id.aCount)
    }

    fun getAttack(view: View) {
        val navn = valg!!.text.toString()
        if(navn.equals("")==false){
            DataInterface(this).getAttack(navn)
        }
        if(SharedPrefInterface(this).getStoredAttackMsg().equals("success")){
            name!!.setText("Name: "+SharedPrefInterface(this).getStoredAttackName())
            type!!.setText("Type: "+SharedPrefInterface(this).getStoredAttackType())
            power!!.setText("Power: "+SharedPrefInterface(this).getStoredAttackPower())
            acc!!.setText("Accuracy: "+SharedPrefInterface(this).getStoredAttackAcc())
            dc!!.setText("Damage class: "+SharedPrefInterface(this).getStoredAttackDc())
            count!!.setText("PP: "+SharedPrefInterface(this).getStoredAttackCount())
        }else{
            name!!.setText("An error occurred")
            type!!.setText("")
            power!!.setText("")
            acc!!.setText("")
            dc!!.setText("")
            count!!.setText("")
        }
        closeSoftKeyboard(this, valg!!)
    }
    fun refreshAttack(view: View) {
        if(SharedPrefInterface(this).getStoredAttackMsg().equals("success")){
            name!!.setText("Name: "+SharedPrefInterface(this).getStoredAttackName())
            type!!.setText("Type: "+SharedPrefInterface(this).getStoredAttackType())
            power!!.setText("Power: "+SharedPrefInterface(this).getStoredAttackPower())
            acc!!.setText("Accuracy: "+SharedPrefInterface(this).getStoredAttackAcc())
            dc!!.setText("Damage class: "+SharedPrefInterface(this).getStoredAttackDc())
            count!!.setText("PP: "+SharedPrefInterface(this).getStoredAttackCount())
        }else{
            name!!.setText("An error occurred")
            type!!.setText("")
            power!!.setText("")
            acc!!.setText("")
            dc!!.setText("")
            count!!.setText("")
        }
    }
    fun goToPokemon(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun closeSoftKeyboard(context: Context, v: View) {
        val iMm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
    }
}
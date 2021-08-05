package com.example.dexter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONObject

class SharedPrefInterface(var context: Context) {
    var sharedPref: SharedPreferences
    init {
        sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_user_data),
            Context.MODE_PRIVATE
        )
    }
    fun storeData(data:JSONObject){
        try{
            val editor = sharedPref.edit()
            editor.putString("name", data.getString("name"))
            editor.putInt("number", data.getInt("number"))
            editor.putInt("vekt", data.getInt("vekt"))
            editor.putString("img", data.getString("img"))
            editor.putString("type", data.getString("type(s)"))
            editor.putString("attributes", data.getString("attributes"))
            editor.putString("msg", "")
            editor.apply()
            Log.d("SharedPref","valid")
        }catch (e:Exception){
            val editor = sharedPref.edit()
            editor.putString("name", "")
            editor.putInt("number", 0)
            editor.putInt("vekt", 0)
            editor.putString("img", "")
            editor.putString("attributes", "")
            editor.putString("msg", data.getString("msg"))
            editor.apply()
            Log.d("SharedPref","invalid")
        }
    }
    fun getStoredImg():String?{
        return sharedPref.getString("img", "")
    }

    fun getStoredName():String?{
        return sharedPref.getString("name", "")
    }

    fun getStoredVekt():Int?{
        return sharedPref.getInt("vekt", 0)
    }

    fun getStoredNumber():Int?{
        return sharedPref.getInt("number", 0)
    }

    fun getStoredAttr():String?{
        return sharedPref.getString("attributes", "")
    }

    fun getStoredType():String?{
        return sharedPref.getString("type", "")
    }
    fun getStoredMsg():String?{
        return sharedPref.getString("msg", "")
    }
}
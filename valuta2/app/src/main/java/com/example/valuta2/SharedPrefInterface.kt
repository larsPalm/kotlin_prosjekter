package com.example.valuta2

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

/*
* kode fra kotlinValutaApp-prosjektet
*/
class SharedPrefInterface(var context: Context) {
    var sharedPref: SharedPreferences
    fun putData(data: String, date: String) {
        try {
            Log.d(TAG, data)
            val parser = JsonParser()
            val element = parser.parse(data)
            val obj = element.asJsonObject
            val entries = obj.entrySet()
            var key = ""
            for ((key1) in entries) {
                Log.d(TAG, key1!!)
                key = key1
            }
            Log.d(TAG,key)
            val jsonObj = JSONObject(data)
            var hm: HashMap<String, Double> = HashMap()
            hm= Gson().fromJson(
                jsonObj[key].toString(),
                hm.javaClass)
            val base_cur: MutableSet<String> = HashSet()
            hm.forEach { (k: String, v: Double?) ->
                base_cur.add(
                    k
                )
            }
            hm.forEach { (k: String, v: Double) ->
                putValue(
                    k,
                    v
                )
            }
            putBaseCurs(base_cur)
            putDate(date)
        } catch (e: Exception) {
        }
    }

    fun putDate(date: String) {
        val editor = sharedPref.edit()
        Log.d(TAG, "insert:$date")
        editor.putString("dato", date)
        editor.apply()
    }

    private fun putValue(key: String, value: Double) {
        val editor = sharedPref.edit()
        editor.putString(key, value.toString())
        editor.apply()
    }

    private fun putBaseCurs(names: Set<String>) {
        val editor = sharedPref.edit()
        editor.putStringSet("baseCur", names)
        editor.apply()
    }

    fun storeImg(imgString: String?) {
        val editor = sharedPref.edit()
        editor.putString("img", imgString)
        editor.apply()
    }

    val date: String?
        get() = sharedPref.getString("dato", "")

    fun getValue(key: String?): Double {
        return sharedPref.getString(key, "0")!!.toDouble()
    }

    val baseCur: MutableList<String>?
        get() = sharedPref.getStringSet("baseCur", null)!!.toMutableList()
    val img: String?
        get() = sharedPref.getString("img", "0")

    fun getImg2():String{
        return sharedPref!!.getString("img2", "0").toString()
    }

    fun storeImg2(imgString: String?) {
        val editor = sharedPref.edit()
        editor.putString("img2", imgString)
        editor.apply()
    }

    companion object {
        private const val TAG = "SharedPrefInterface"
    }

    init {
        sharedPref = context.getSharedPreferences(
            context.getString(R.string.preference_user_data),
            Context.MODE_PRIVATE
        )
    }
}
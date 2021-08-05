package com.example.dexter

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import java.util.*

class DataInterface(var context: Context) {
    var si: ServerInterface
    var spi: SharedPrefInterface
    companion object {
        private const val TAG = "DataInterface"
        var livedata = MutableLiveData<String>()
    }
    init {
        si = ServerInterface(
            String.format(
                Locale.getDefault(),
                "http://%s:%s/",
                context.resources.getString(R.string.server_ip),
                context.resources.getString(R.string.server_port)
            ), context
        )
        spi = SharedPrefInterface(context)
    }
    fun getInformation(end:String){
        val dataCallback = object : Callback {
            override fun callback(data: Any?) {
                if (data is String) {
                    Log.d("Data",data.toString())
                    val obj = JSONObject(data as String?)
                    spi.storeData(obj)
                } else {
                    Toast.makeText(context, "Invalid response from server", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        si.volleyGet("getAPokemon/$end", dataCallback)
    }
}
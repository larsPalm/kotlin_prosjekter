package com.example.kotlinvalutaapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DataInterface(var context: Context) {
    var si: ServerInterface
    var spi: SharedPrefInterface

    //checks if the response from the server is empty or not
    val data: Unit
        get() {
            Log.d(TAG, SharedPrefInterface(context).date!!)
            val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val now = LocalDateTime.now()
            val date = dtf.format(now)
            Log.d(TAG, date)
            spi.putDate("")
            val value: String = SharedPrefInterface(context).date!!
            val compare = value.compareTo(date)
            val compare2 = date.compareTo(date)
            val compare3 = "2021-07-10".compareTo(date)
            Log.d(TAG, compare.toString())
            Log.d(TAG, compare2.toString())
            Log.d(TAG, compare3.toString())
            val oldDate: String = SharedPrefInterface(context).date!!
            val currentDate = dtf.format(now)
            Log.d(TAG, "$oldDate   $currentDate")
            Log.d(TAG, "---" + (oldDate.compareTo(currentDate) < 0).toString())
            if (oldDate.compareTo(currentDate) < 0) {
                val lrCallback = object : Callback {
                    override fun callback(data: Any?) {
                        Log.d(TAG, data.toString())
                        if (data == null) {
                            Log.d(TAG, "is null")
                            return
                        }
                        if (data is String) {
                            //checks if the response from the server is empty or not
                            if (data.toString() == "") {
                                Log.d(
                                    TAG,
                                    "return was empty, data length =" + data.toString().length + ", end"
                                )
                                Log.d(
                                    TAG,
                                    "return was empty, data =$data, end"
                                )
                            } else {
                                try {
                                    spi.putData(data, currentDate)
                                    spi.putDate(currentDate)
                                } catch (t: Throwable) {}
                            }
                        }
                    }
                }
                si.volleyGet("latestValues/",lrCallback)
            }
        }

    fun getImg(from: String, to: String) {
        Log.d(TAG, "from_cur $from,to_cur:$to")
        val imgCallback = object : Callback {
            override fun callback(data: Any?) {
                if (data is String) {
                    spi.storeImg(data as String?)
                } else {
                    Toast.makeText(context, "Invalid response from server", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        si.volleyGet("compareImg2/$from/$to", imgCallback)
    }

    val storedImg: Unit
        get() {
            livedata.setValue(spi.img)
        }

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
            ),
            context
        )
        spi = SharedPrefInterface(context)
    }
}
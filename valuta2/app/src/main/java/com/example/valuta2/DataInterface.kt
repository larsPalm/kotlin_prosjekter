package com.example.valuta2

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/*kode tatt fra kotlinValutaApp*/
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
            val oldDate: String = SharedPrefInterface(context).date!!
            val currentDate = dtf.format(now)
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

    fun getImgMult(base: String, others: String) {
        Log.d(TAG, "from_cur $base,to_cur:$others")
        val imgCallback = object : Callback {
            override fun callback(data: Any?) {
                if (data is String) {
                    spi.storeImg2(data as String?)
                } else {
                    Toast.makeText(context, "Invalid response from server", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        si.volleyGet("comparMultMobil/$base/$others", imgCallback)
    }

    val storedImg: Unit
        get() {
            livedata.setValue(spi.img)
        }

    val storedImg2: Unit
        get() {
            livedata2.setValue(spi.getImg2())
        }

    companion object {
        private const val TAG = "DataInterface"
        var livedata = MutableLiveData<String>()
        var livedata2 = MutableLiveData<String>()

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
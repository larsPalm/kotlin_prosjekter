package com.example.kotlinvalutaapp

import android.content.Context
import android.location.GnssAntennaInfo
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class ServerInterface(private val baseUrl: String, private val context: Context) {
    fun volleyGet(endPoint: String, success: Callback) {
        val url = baseUrl + endPoint
        val queue: RequestQueue = Volley.newRequestQueue(context)

        val sr = StringRequest(Request.Method.GET, url,
            {
                response -> success.callback(response)
            },
            {
                error -> Log.d(TAG, "Got error when querying on: " + url + " error: " + error.toString())
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show() })
        queue.add(sr)
    }

    companion object {
        private const val TAG = "ServerInterface"
    }
}
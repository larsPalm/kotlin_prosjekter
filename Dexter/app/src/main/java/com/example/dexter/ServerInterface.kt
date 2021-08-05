package com.example.dexter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ServerInterface(private val baseUrl: String, private val context: Context) {
    companion object {
        private const val TAG = "ServerInterface"
    }

    fun volleyGet(endPoint: String, success: Callback) {
        val url = baseUrl + endPoint
        val queue: RequestQueue = Volley.newRequestQueue(context)

        val sr = StringRequest(
            Request.Method.GET, url,
            Response.Listener {
                    response -> success.callback(response)
            },
            Response.ErrorListener{
                    error -> Log.d(TAG, "Got error when querying on: " + url + " error: " + error.toString())
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show() })
        queue.add(sr)
    }
}
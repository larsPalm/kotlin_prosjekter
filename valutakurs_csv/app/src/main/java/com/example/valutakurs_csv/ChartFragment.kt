package com.example.valutakurs_csv

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.InputStream

class ChartFragment : Fragment() {
    var devCompare:Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner1 = view.findViewById<Spinner>(R.id.spinner_choise)
        if(devCompare) {
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.developmentAll,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner1.adapter = adapter
            }
        }
        else{
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.compareCurrency,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner1.adapter = adapter
            }
        }
        val btnSubmit = view.findViewById<Button>(R.id.btnChart)
        btnSubmit.setOnClickListener {
            val cur1 = spinner1.getSelectedItem().toString()
            if(devCompare){
                showDevCom(cur1)
            }
            else{
                showCompare(cur1)
            }
        }
    }
    private fun showDevCom(s:String){
        val  assetManager = getActivity()!!.getAssets()
        var image = view!!.findViewById<ImageView>(R.id.image)
        if(s == "dev"){
            val iss = assetManager.open("utvikling.png");
            val  bitmap:Bitmap = BitmapFactory.decodeStream(iss)
            image.setImageBitmap(bitmap)
        }
        else{
            val iss = assetManager.open("alleValutaer.png");
            val  bitmap:Bitmap = BitmapFactory.decodeStream(iss)
            image.setImageBitmap(bitmap)
        }
    }
    private fun showCompare(s:String){
        val  assetManager = getActivity()!!.getAssets()
        var image = view!!.findViewById<ImageView>(R.id.image)
        val iss = assetManager.open("%s.png".format(s));
        val  bitmap:Bitmap = BitmapFactory.decodeStream(iss)
        image.setImageBitmap(bitmap)
    }
}
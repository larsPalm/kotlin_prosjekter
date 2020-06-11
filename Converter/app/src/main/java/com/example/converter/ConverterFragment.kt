package com.example.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_converter.*

class ConverterFragment:Fragment(){
    var bw = false
    var bf = false
    var bd = false
    val fluids = mutableMapOf<String,Double>("liter" to 1.0,"cup" to 0.236,"gallon(US)" to 3.785,"gallon(UK)" to 4.54609)
    val weights = mutableMapOf<String,Double>("kilos" to 1.0,"pound" to 0.4535,"stones" to 6.35,"tons" to 1000.0)
    val distances = mutableMapOf<String,Double>("meter" to 1.0, "yard" to 0.9144,"miles" to 1609.344,"kilometers" to 1000.0,"mil" to 10000.0)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner1 = view.findViewById<Spinner>(R.id.spinner1)
        val spinner2 = view.findViewById<Spinner>(R.id.spinner2)
        if(bw){
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.weight,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner1.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.weight,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner2.adapter = adapter
            }
        }
        if(bd){
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.distance,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner1.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.distance,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner2.adapter = adapter
            }
        }
        if(bf){
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.fluid,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner1.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                getActivity(),
                R.array.fluid,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner2.adapter = adapter
            }
        }
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val cur1 = spinner1.getSelectedItem().toString()
            val cur2 = spinner2.getSelectedItem().toString()
            if(bw){
                convertWeights(cur1,cur2)
            }
            if(bd){
                convertDistance(cur1,cur2)
            }
            if(bf){
                convertFluid(cur1,cur2)
            }
        }
    }
    fun convertWeights(s1:String,s2:String){
        if(txtValue.text.toString() =="" ||(java.lang.Double.parseDouble(txtValue.text.toString()) in 0.0..999999.0)==false){
            utTekst.text = ""
            Toast.makeText(getActivity(),"input between 0- 999 999", Toast.LENGTH_SHORT).show()
            return
        }
        val curVal1 = weights.get(s1)!!
        val curVal2 = weights.get(s2)!!
        val inn = (java.lang.Double.parseDouble(txtValue.text.toString()))
        val ut = inn*(curVal1/curVal2)
        utTekst.text ="%.2f $s1 %.2f $s2".format(inn,ut)
        txtValue.setText("")
    }
    fun convertDistance(s1:String,s2:String){
        if(txtValue.text.toString() =="" ||(java.lang.Double.parseDouble(txtValue.text.toString()) in 0.0..999999.0)==false){
            utTekst.text = ""
            Toast.makeText(getActivity(),"input between 0- 999 999", Toast.LENGTH_SHORT).show()
            return
        }
        val curVal1 = distances.get(s1)!!
        val curVal2 = distances.get(s2)!!
        val inn = (java.lang.Double.parseDouble(txtValue.text.toString()))
        val ut = inn*(curVal1/curVal2)
        utTekst.text ="%.2f $s1 %.2f $s2".format(inn,ut)
        txtValue.setText("")
    }
    fun convertFluid(s1:String,s2:String){
        if(txtValue.text.toString() =="" ||(java.lang.Double.parseDouble(txtValue.text.toString()) in 0.0..999999.0)==false){
            utTekst.text = ""
            Toast.makeText(getActivity(),"input between 0- 999 999", Toast.LENGTH_SHORT).show()
            return
        }
        val curVal1 = fluids.get(s1)!!
        val curVal2 = fluids.get(s2)!!
        val inn = (java.lang.Double.parseDouble(txtValue.text.toString()))
        val ut = inn*(curVal1/curVal2)
        utTekst.text ="%.2f $s1 %.2f $s2".format(inn,ut)
        txtValue.setText("")
    }
}
package com.example.valutakurs_csv

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_calculate.*

/*import android.widget.AdapterView.OnItemSelectedListener
import android.view.View.OnClickListener*/

class CalculateFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ma = (activity as MainActivity).parser.getNames()
        val spinner1 = view.findViewById<Spinner>(R.id.spinner_cur1)
        ArrayAdapter.createFromResource(
            getActivity(),
            R.array.currency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner1.adapter = adapter
        }
        val spinner2: Spinner = view.findViewById(R.id.spinner_cur2)
        ArrayAdapter.createFromResource(
            getActivity(),
            R.array.currency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }
        val btnExchange = view.findViewById<Button>(R.id.btnSubmit)
        btnExchange.setOnClickListener {
            val cur1 = spinner1.getSelectedItem().toString()
            val cur2 = spinner2.getSelectedItem().toString()
            val na = ma.toString()
            //utTekst.text = "$na"
            exchange(cur1,cur2)
        }
    }

    private fun exchange(s1:String,s2:String){
        if(txtValue.text.toString() =="" ||(java.lang.Double.parseDouble(txtValue.text.toString()) in 0.0..999999.0)==false){
            utTekst.text = ""
            Toast.makeText(getActivity(),"input between 0- 999 999",Toast.LENGTH_SHORT).show()
            return
        }
        val ma = (activity as MainActivity).parser.getKurs()
        val curVal1 = ma.get(s1)!!
        val curVal2 = ma.get(s2)!!
        val inn = (java.lang.Double.parseDouble(txtValue.text.toString()))
        val ut = inn*(curVal1/curVal2)
        utTekst.text ="%.2f $s1 %.2f $s2".format(inn,ut)
        txtValue.setText("")
    }
}
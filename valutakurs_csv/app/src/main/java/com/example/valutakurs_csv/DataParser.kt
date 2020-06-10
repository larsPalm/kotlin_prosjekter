package com.example.valutakurs_csv

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.ArrayList
import android.os.Bundle

class DataParser {
    val name = mutableListOf<String>()
    val datoer = mutableListOf<String>()
    val kurser = hashMapOf<String, Double>()
    val utviklingKurser = hashMapOf<String,ArrayList<Double>>()

    fun parseFile(text:List<String>,b:Int): Void?{
        val name_idx:Int = 2
        val unit_multi_idx:Int = 11
        val time_idx:Int = 14
        val val_idx:Int = 15
        var teller:Int = 0
        val textE = text.toMutableList()
        textE.removeAt(0)
        var valName = ""
        val utvikling = ArrayList<Double>()
        for(line in textE){
            val tokens = line.split(";")
            if(teller == 0){
                name.add(tokens[name_idx])
                valName = tokens[name_idx]
            }
            var verdi = tokens[val_idx].toDouble()
            if(tokens[unit_multi_idx] == "Hundreds"){verdi = verdi/100}
            if(b == 0){datoer.add(tokens[time_idx])}
            utvikling.add(verdi)
            teller++
        }
        kurser.put(valName,utvikling.get(utvikling.size-1))
        utviklingKurser.put(valName,utvikling)
        return null
    }
    fun lagNorge():Void?{
        kurser.put("NOK",1.0)
        val utviklingNorge = ArrayList<Double>()
        for(x in datoer){utviklingNorge.add(1.0)}
        utviklingKurser.put("NOK",utviklingNorge)
        return null
    }

    public fun getNames():MutableList<String>{
        return name
    }

    public fun getKurs():HashMap<String, Double>{
        return kurser
    }

    public fun getUtvikling():HashMap<String,ArrayList<Double>>{
        return utviklingKurser
    }
    fun getDates():MutableList<String>{
        return datoer
    }
    fun makeCombinations(): MutableList<String>{
        val l =mutableListOf<String>()
        for(s1 in name) {
            for(s2 in name){
                if(s1 != s2){
                    l.add("%s_vs_%s.png".format(s1,s2))
                }
            }
        }
        return l
    }

}
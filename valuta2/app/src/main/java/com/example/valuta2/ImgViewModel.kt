package com.example.valuta2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class ImgViewModel(application: Application?) : AndroidViewModel(application!!) {
    val currentImg: LiveData<String>
        get() = DataInterface.livedata
}
package com.jsb.monoprueba.ui.home.fragments.procesos

import androidx.lifecycle.LiveData
import com.jsb.monoprueba.model.Ciudad


interface ProcesosListener {
    fun onSuccess(resultResponse: LiveData<ArrayList<Ciudad>>)
    fun onFailure(message:String)

}
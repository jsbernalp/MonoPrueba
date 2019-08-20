package com.jsb.monoprueba.ui.main

import androidx.lifecycle.LiveData
import com.jsb.monoprueba.model.Usuario

interface MainListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>, usuario: Usuario)
    fun onFailure(message:String)
}
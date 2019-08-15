package com.jsb.monoprueba.ui.main

import androidx.lifecycle.LiveData

interface MainListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message:String)
}
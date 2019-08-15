package com.jsb.monoprueba.ui.home.fragments.notificaciones

import androidx.lifecycle.LiveData
import com.jsb.monoprueba.model.Ciudad

interface NotificacionesListener {
    fun onStarted()
    fun onSuccess(resultNotifica: LiveData<ArrayList<Ciudad>>)
    fun onFailure(message:String)
}
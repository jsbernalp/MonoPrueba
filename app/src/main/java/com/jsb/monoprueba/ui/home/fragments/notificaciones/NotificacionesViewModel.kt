package com.jsb.monoprueba.ui.home.fragments.notificaciones

import androidx.lifecycle.ViewModel
import com.jsb.monoprueba.data.repositories.NotificacionRepository


class NotificacionesViewModel: ViewModel() {

    var notificacionesListener: NotificacionesListener? = null


    fun LoadCities(){
        var resultNotifica= NotificacionRepository().getNotificacion()
        notificacionesListener?.onSuccess(resultNotifica)
    }

}
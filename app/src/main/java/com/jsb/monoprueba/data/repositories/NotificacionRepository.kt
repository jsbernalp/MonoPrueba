package com.jsb.monoprueba.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsb.monoprueba.data.network.CityApi
import com.jsb.monoprueba.model.Ciudad
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotificacionRepository @Inject constructor() {

    fun getNotificacion(): LiveData<ArrayList<Ciudad>> {

        var resultNotificacion = MutableLiveData<ArrayList<Ciudad>>()

        CityApi().getCiudades()
            .enqueue(object : Callback<ArrayList<Ciudad>>{
                override fun onFailure(call: Call<ArrayList<Ciudad>>, t: Throwable) {
                    t.message.toString()
                }

                override fun onResponse(call: Call<ArrayList<Ciudad>>, response: Response<ArrayList<Ciudad>>) {
                    if (response.isSuccessful){
                        resultNotificacion.value = response.body()
                    }else{
                        response.errorBody()?.toString()
                    }
                }
            })
        return resultNotificacion
    }
}
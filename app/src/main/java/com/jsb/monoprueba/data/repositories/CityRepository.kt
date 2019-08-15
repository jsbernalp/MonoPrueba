package com.jsb.monoprueba.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsb.monoprueba.data.network.CityApi
import com.jsb.monoprueba.model.Ciudad
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityRepository {

    fun getCities(): LiveData<ArrayList<Ciudad>> {


        var resultResponse = MutableLiveData<ArrayList<Ciudad>>()

        CityApi().getCiudades()
            .enqueue(object: Callback<ArrayList<Ciudad>>{
                override fun onFailure(call: Call<ArrayList<Ciudad>>, t: Throwable) {
                    t.message.toString()
                }

                override fun onResponse(call: Call<ArrayList<Ciudad>>, response: Response<ArrayList<Ciudad>>) {
                    if (response.isSuccessful) {
                        resultResponse.value = response.body()

                    }else{
                       response.errorBody()?.toString()
                    }
                }
            })
        return resultResponse
    }
}



package com.jsb.monoprueba.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jsb.monoprueba.data.network.MonoApi
import com.jsb.monoprueba.model.LoginResult
import com.jsb.monoprueba.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun userLogin(usuario: Usuario):LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        MonoApi().addUsuario(usuario)
            .enqueue(object : Callback<LoginResult>{
                override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                    if (response.isSuccessful){
                        loginResponse.value = response.body()?.Msg.toString()
                    }else{
                        loginResponse.value = response.errorBody()?.toString()
                    }

                }
            })
        return loginResponse
    }
}
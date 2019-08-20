package com.jsb.monoprueba.data.network

import com.jsb.monoprueba.model.LoginResult
import com.jsb.monoprueba.model.Usuario
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MonoApi {

    @POST("api/Usuarios")
    fun addUsuario(@Body body: Usuario): Call<LoginResult>


    companion object{
        const val BASE_URL = "http://pruebasperu.monolegal.co/"
        operator fun invoke(): MonoApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MonoApi::class.java)
        }
    }
}


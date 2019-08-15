package com.jsb.monoprueba.data.network

import com.jsb.monoprueba.model.Ciudad
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CityApi {

    @GET("/api/ciudades")
    fun getCiudades(): Call<ArrayList<Ciudad>>


    companion object{
        const val BASE_URL1 = "http://pruebas.monolegal.co"
        operator fun invoke(): CityApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CityApi::class.java)
        }
    }
}
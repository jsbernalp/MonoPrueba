package com.jsb.monoprueba.ui.home.fragments.procesos

import androidx.lifecycle.ViewModel
import com.jsb.monoprueba.data.db.AppDatabase
import com.jsb.monoprueba.data.repositories.CityRepository
import com.jsb.monoprueba.model.Ciudad

class ProcesosViewModel: ViewModel() {

    var procesosListener: ProcesosListener? = null
    lateinit var  db: AppDatabase
    var lstCities:ArrayList<Ciudad> = ArrayList<Ciudad>()


    fun LoadData(){

            var resultResponse = CityRepository().getCities()
            procesosListener?.onSuccess(resultResponse)

        }







    }

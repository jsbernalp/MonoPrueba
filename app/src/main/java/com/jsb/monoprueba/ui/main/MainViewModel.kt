package com.jsb.monoprueba.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import com.jsb.monoprueba.data.repositories.UserRepository
import com.jsb.monoprueba.model.Usuario

class MainViewModel : ViewModel(){

    var Nombres:String? = null
    var Email:String? = null
    var Password:String? = null

    var mainListener: MainListener? = null

    fun onRegisterButtonClick(view: View){

        mainListener?.onStarted()

        if (Nombres.isNullOrEmpty() || Email.isNullOrEmpty() || Password.isNullOrEmpty()){
            mainListener?.onFailure("invalid email or name or password")
            return
        }

        val usuario = Usuario()
        usuario.Email = Email.toString()
        usuario.Nombres = Nombres.toString()
        usuario.Password = Password.toString()

       val loginResponse = UserRepository().userLogin(usuario!!)
        mainListener?.onSuccess(loginResponse)

    }
}
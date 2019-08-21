package com.jsb.monoprueba.ui.main

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.ViewModel

import com.jsb.monoprueba.data.repositories.UserRepository
import com.jsb.monoprueba.model.Usuario

import javax.inject.Inject

class MainViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {


    var Nombres:String? = null
    var Email:String? = null
    var Password:String? = null
    var mainListener: MainListener? = null
    var checkBox:Boolean = false



    fun onRegisterButtonClick(view: View){



            if (Nombres.isNullOrEmpty() || Email.isNullOrEmpty() || Password.isNullOrEmpty()){
                mainListener?.onFailure("invalid email or name or password")
                return
            }else{
                var usuario = Usuario()
                usuario.Email = Email.toString()
                usuario.Nombres = Nombres.toString()
                usuario.Password = Password.toString()

                val loginResponse = UserRepository().userLogin(usuario)
                mainListener?.onSuccess(loginResponse,usuario)
            }

    }
}


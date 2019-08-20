package com.jsb.monoprueba.ui.main


import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.jsb.monoprueba.R
import com.jsb.monoprueba.data.db.AppDatabase
import com.jsb.monoprueba.databinding.ActivityMainBinding
import com.jsb.monoprueba.di.main.DaggerMainComponent
import com.jsb.monoprueba.factory.DaggerViewModelFactory
import com.jsb.monoprueba.model.Usuario
import com.jsb.monoprueba.ui.home.HomeActivity
import com.jsb.monoprueba.util.hide
import com.jsb.monoprueba.util.show
import com.jsb.monoprueba.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainListener {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.create().inject(this)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)

        binding.viewmodel = mainViewModel
        mainViewModel.mainListener = this

    }

   override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(
        loginResponse: LiveData<String>,
        usuario: Usuario
    ) {
        if (checkbox.isChecked){
            loginResponse.observe(this, Observer {
                if (it.isNullOrEmpty()){
                    toast("Comprueba tu Conexion a Internet")
                }else{
                    toast(it)
                    intentOn()
                }
            })
        }else{
            toast("Debe Aceptar los terminos y Condiciones")
        }
    }

    override fun onFailure(message: String) {
        toast(message)
    }

    fun intentOn(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}





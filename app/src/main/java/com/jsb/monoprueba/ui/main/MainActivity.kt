package com.jsb.monoprueba.ui.main

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jsb.monoprueba.R
import com.jsb.monoprueba.databinding.ActivityMainBinding
import com.jsb.monoprueba.ui.home.HomeActivity
import com.jsb.monoprueba.util.hide
import com.jsb.monoprueba.util.show
import com.jsb.monoprueba.util.toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.mainListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        progress_bar.hide()
        loginResponse.observe(this, Observer {
            //toast(it)
        })
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }


}

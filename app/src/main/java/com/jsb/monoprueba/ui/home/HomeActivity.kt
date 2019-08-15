package com.jsb.monoprueba.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsb.monoprueba.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar.setTitle("Pestañas")
        setSupportActionBar(toolbar)

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)

    }
}
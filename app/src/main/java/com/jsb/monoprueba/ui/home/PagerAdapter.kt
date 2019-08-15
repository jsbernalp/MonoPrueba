package com.jsb.monoprueba.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jsb.monoprueba.ui.home.fragments.notificaciones.FragmentNotificaciones
import com.jsb.monoprueba.ui.home.fragments.procesos.FragmentProcesos


class PagerAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FragmentProcesos()
            }else ->{

                return FragmentNotificaciones()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Procesos"
            else -> {return "Notificaciones"
            }
        }
    }
}
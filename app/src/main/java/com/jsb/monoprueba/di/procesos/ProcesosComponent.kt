package com.jsb.monoprueba.di.procesos

import com.jsb.monoprueba.factory.ViewModelFactoryModule
import com.jsb.monoprueba.ui.home.fragments.procesos.FragmentProcesos

import dagger.Component

@Component(modules=[ViewModelFactoryModule::class, ProcesosModule::class])
interface ProcesosComponent{
    fun inject(fragmentProcesos: FragmentProcesos)
}
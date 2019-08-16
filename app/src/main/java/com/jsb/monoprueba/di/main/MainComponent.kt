package com.jsb.monoprueba.di.main

import com.jsb.monoprueba.factory.ViewModelFactoryModule
import com.jsb.monoprueba.ui.main.MainActivity

import dagger.Component


@Component(modules=[ViewModelFactoryModule::class, MainModule::class])
interface MainComponent{
    fun inject(activity: MainActivity)
}
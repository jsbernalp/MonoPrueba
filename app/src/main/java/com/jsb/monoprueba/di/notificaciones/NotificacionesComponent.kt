package com.jsb.monoprueba.di.notificaciones

import com.jsb.monoprueba.factory.ViewModelFactoryModule
import com.jsb.monoprueba.ui.home.fragments.notificaciones.FragmentNotificaciones

import dagger.Component

@Component(modules=[ViewModelFactoryModule::class, NotificacionesModule::class])
interface NotificacionesComponent{
    fun inject(fragmentNotificaciones: FragmentNotificaciones)
}
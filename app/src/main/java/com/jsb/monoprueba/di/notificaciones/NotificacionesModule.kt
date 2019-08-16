package com.jsb.monoprueba.di.notificaciones

import androidx.lifecycle.ViewModel
import com.jsb.monoprueba.factory.ViewModelKey
import com.jsb.monoprueba.ui.home.fragments.notificaciones.NotificacionesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NotificacionesModule
{
    @Binds
    @IntoMap
    @ViewModelKey(NotificacionesViewModel::class)
    abstract fun  bindViewModelFactory1(notificacionesViewModel: NotificacionesViewModel): ViewModel
}
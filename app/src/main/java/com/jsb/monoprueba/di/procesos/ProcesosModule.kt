package com.jsb.monoprueba.di.procesos

import androidx.lifecycle.ViewModel
import com.jsb.monoprueba.factory.ViewModelKey
import com.jsb.monoprueba.ui.home.fragments.procesos.ProcesosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProcesosModule
{
    @Binds
    @IntoMap
    @ViewModelKey(ProcesosViewModel::class)
    abstract fun  bindViewModelFactory(procesosViewModel: ProcesosViewModel): ViewModel
}
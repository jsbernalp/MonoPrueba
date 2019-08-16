package com.jsb.monoprueba.di.main

import androidx.lifecycle.ViewModel
import com.jsb.monoprueba.factory.ViewModelKey
import com.jsb.monoprueba.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule
{
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun  bindViewModelFactory(mainViewModel: MainViewModel): ViewModel
}
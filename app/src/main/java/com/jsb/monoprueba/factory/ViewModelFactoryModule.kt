package com.jsb.monoprueba.factory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(daggaerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}
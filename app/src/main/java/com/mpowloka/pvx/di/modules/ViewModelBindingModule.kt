package com.mpowloka.pvx.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpowloka.pvx.MainViewModel
import com.mpowloka.pvx.di.DaggerViewModelFactory
import com.mpowloka.pvx.di.ViewModelKey
import com.mpowloka.pvx.localizations.LocalizationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {

    @Binds
    internal abstract fun bindFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocalizationsViewModel::class)
    abstract fun localizationsViewModule(viewModel: LocalizationsViewModel): ViewModel

}
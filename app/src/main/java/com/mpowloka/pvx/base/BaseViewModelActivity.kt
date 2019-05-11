package com.mpowloka.pvx.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseViewModelActivity<T : ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getViewModelClass(): Class<T>

    val viewModel: T by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }

    fun <T> autoDisposable(): AutoDisposeConverter<T> {
        return AutoDispose
            .autoDisposable<T>(
                AndroidLifecycleScopeProvider.from(this)
            )
    }

}
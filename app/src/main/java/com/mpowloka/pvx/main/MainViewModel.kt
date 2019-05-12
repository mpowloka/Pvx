package com.mpowloka.pvx.main

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val actionBarConfigurationSubject = BehaviorSubject.create<ActionBarConfiguration>()
    val actionBarConfiguration: Observable<ActionBarConfiguration> = actionBarConfigurationSubject as Observable<ActionBarConfiguration>

    fun setActionBarConfiguration(configuration: ActionBarConfiguration) {
        actionBarConfigurationSubject.onNext(configuration)
    }

}
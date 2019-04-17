package com.example.mvvm_example.viewmodel

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import com.example.mvvm_example.BR

open class ObservableViewModel(app: Application) : AndroidViewModel(app), Observable {

    @delegate:Transient
    private val mCallbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.remove(callback)
    }

    fun notifyChange() {
        mCallbacks.notifyChange(this, BR._all)
    }
}
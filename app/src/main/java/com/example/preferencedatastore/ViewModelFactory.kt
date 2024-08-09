package com.example.preferencedatastore

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SampleViewModel::class.java)){
            return SampleViewModel(dataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}
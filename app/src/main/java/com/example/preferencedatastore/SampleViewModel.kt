package com.example.preferencedatastore

import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SampleViewModel(private val dataStore: DataStore<Preferences>): ViewModel() {

     fun save(key: Int,value: String){
         viewModelScope.launch {
             val dataKey = stringPreferencesKey(key.toString())
             dataStore.edit {
                 it[dataKey] = value
             }
         }
    }

    suspend fun read(key: Int): String?{
        val dataKey = stringPreferencesKey(key.toString())

        val preferences = dataStore.data.first()
        return preferences[dataKey]
    }

    fun loadAll(): Flow<List<Pair<String,String>>> {
        return dataStore.data.map { preferences ->
                preferences.asMap().map { entry ->
                    entry.key.name to (entry.value as? String?: "No Data")
                }
        }
    }

}
package com.example.preferencedatastore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.preferencesDataStore
import com.example.preferencedatastore.screens.MainScreen
import com.example.preferencedatastore.ui.theme.PreferenceDataStoreTheme

class MainActivity : ComponentActivity() {

    private val Context.dataStore by preferencesDataStore(name = "Sample")

    private val vm: SampleViewModel by viewModels {
        ViewModelFactory(dataStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        actionBar?.hide()

//        val splashScreen = installSplashScreen()


        setContent {
            PreferenceDataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(vm)
                }
            }
        }
    }
}


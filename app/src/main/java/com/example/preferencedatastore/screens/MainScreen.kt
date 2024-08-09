package com.example.preferencedatastore.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.preferencedatastore.SampleViewModel

@Composable
fun MainScreen(viewModel: SampleViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "myApp") {
        composable(
            "myapp"
        ) {
            MyAppScreen(viewModel,navController)
        }
        composable(
            "lazyList"
        ) {
            DisplayFirstData(viewModel)

        }


    }

}


package com.example.preferencedatastore.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.text.trimmedLength
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.preferencedatastore.SampleViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppScreen(viewModel: SampleViewModel, navController: NavHostController) {

    val TAG = "gat"
    var keyValue by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    var dataValue by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    var keyInput by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            ) {
            OutlinedTextField(value =keyValue, onValueChange = {
                keyValue = it.trimEnd()
            }, placeholder = { Text(text = "Enter a Integer", color = Color.Gray.copy(0.5f))})
            OutlinedTextField(value =dataValue, onValueChange = {
                dataValue = it.trimEnd()
                Log.d(TAG, "$keyValue $dataValue ha ")

            })

            Button(onClick = {
                if(keyValue.isNotEmpty()){
                    try{
                        viewModel.save(keyValue.toInt(),dataValue)
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    }catch (e: NumberFormatException){
                        Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value =keyInput , onValueChange = {
                keyInput = it.trimEnd()
            })

            Button(onClick = {
                viewModel.viewModelScope.launch{
                    try {
                        val data = viewModel.read(keyInput.toInt())
                        Toast.makeText(context, data ?: "No Value found", Toast.LENGTH_SHORT).show()
                    }catch (e: NumberFormatException){
                        Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                    }

                }
            }) {
                Text(text = "Read")
            }

            Button(onClick = {
                navController.navigate("lazyList")
            }) {
                Text(text = "Load All Data")
            }

        }

    }

}





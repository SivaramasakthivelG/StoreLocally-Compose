package com.example.preferencedatastore.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.preferencedatastore.SampleViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun DisplayFirstData(viewModel: SampleViewModel) {

    val dataList by viewModel.loadAll().collectAsState(initial = emptyList())

    LazyColumn {
        items(dataList) { item ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                text = "${item.first}: ${item.second}",
                fontStyle = FontStyle.Italic
            )
        }
    }


}
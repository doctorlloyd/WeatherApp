package com.lloyd.weatherapp.widgets.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loader() {
    Box(modifier = Modifier.fillMaxSize().padding(0.dp).background(color = Color.LightGray)){
        Column(modifier = Modifier.fillMaxSize().padding(16.dp).align(Alignment.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            CircularProgressIndicator(color = Color.White)
        }
    }
}
package com.lloyd.weatherapp.widgets.loader

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Loader() { CircularProgressIndicator(modifier = Modifier.fillMaxSize(), color = Color.White) }
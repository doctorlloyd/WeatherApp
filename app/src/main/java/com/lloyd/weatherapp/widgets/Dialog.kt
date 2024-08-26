package com.lloyd.weatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

@Composable
fun Dialog(openDialog: MutableState<Boolean>, dialogTitle : String, headColor: Color, dialogContent : @Composable () -> Unit) {
    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = true }) {
            Box(modifier = Modifier.background(color = Color.White, shape = MaterialTheme.shapes.large)) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 12.dp)) {
                    Text(text = dialogTitle, modifier = Modifier.wrapContentHeight(), style = TextStyle(color = headColor, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp), textAlign = TextAlign.Center)
                    dialogContent()
                }
            }
        }
    }
}

@Composable
fun UserAlertDialog(message: String, openDialog: MutableState<Boolean>) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = message, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), style = TextStyle(textAlign = TextAlign.Center, fontSize = 14.sp, fontWeight = FontWeight.Bold))
        Button(onClick = {
            openDialog.value = false
        }, modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, top = 4.dp), shape = MaterialTheme.shapes.large, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)) {
            Text(text = "Okay", modifier = Modifier.padding(8.dp), style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White))
        }
    }
}
package com.lloyd.weatherapp.widgets.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lloyd.weatherapp.models.forecast.Weather
import com.lloyd.weatherapp.widgets.Dialog
import com.lloyd.weatherapp.widgets.UserAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun CustomSearchViewRight(placeholder: String, search: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit, weather: (Weather) -> Unit) {
    val userNotFound = remember { mutableStateOf(false) }

    // Used for side effects on submit button
    val viewModelJob = Job()
    val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        OutlinedTextField(shape = MaterialTheme.shapes.large, singleLine = true, modifier = Modifier.height(50.dp).align(Alignment.CenterVertically).fillMaxWidth(),
            value = search, onValueChange = onValueChange, colors = TextFieldDefaults.textFieldColors(textColor = Color.Black, backgroundColor = Color.Transparent), placeholder = { Text(textAlign = TextAlign.Center, text = placeholder, style = TextStyle(fontSize = 14.sp)) }, trailingIcon = {
                IconButton(onClick = {
                    scope.launch {

                    }
                }){ Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = MaterialTheme.colors.primaryVariant) }
            })
    }

    // User was not found
    if (userNotFound.value) Dialog(openDialog = userNotFound, dialogTitle = "Item search", headColor = Color.Red) {
        UserAlertDialog(message = "Item was not found", openDialog = userNotFound)
    }
}
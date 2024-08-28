package com.lloyd.weatherapp.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lloyd.weatherapp.models.local.LocalWeather
import com.lloyd.weatherapp.utils.Constants.navScreens
import com.lloyd.weatherapp.utils.convertTemperature
import com.lloyd.weatherapp.utils.getWeatherIcon
import com.lloyd.weatherapp.widgets.navigation.WeatherAppNavScreens
import com.lloyd.weatherapp.widgets.search.CustomSearchViewRight
import kotlinx.coroutines.*


@Composable
fun SearchScreen(navController: NavController) {
    // Used for side effects on submit button
    val viewModelJob = Job()
    val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(1) }
    var searchText by remember { mutableStateOf("") }

    val searchScreenViewModel = hiltViewModel<SearchScreenViewModel>()
    var localWeatherList by remember{ mutableStateOf(ArrayList<LocalWeather>()) }

    scope.launch { localWeatherList = searchScreenViewModel.getListOfRecentlySearchedWeather() as ArrayList }

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.height(65.dp), containerColor = MaterialTheme.colors.surface, contentColor = MaterialTheme.colors.primaryVariant, tonalElevation = 2.dp) {
                navScreens.forEachIndexed { index, item ->
                    NavigationBarItem(selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(WeatherAppNavScreens.HomeScreen.name){ launchSingleTop = true }
                                1 -> {}
                            }
                        },
                        label = { Text(text = item.title, style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center, color = MaterialTheme.colors.secondary, fontWeight = if (index == selectedItemIndex) FontWeight.ExtraBold else FontWeight.Bold)) },
                        alwaysShowLabel = true, icon = { Icon(tint = if (index == selectedItemIndex) Color.White else MaterialTheme.colors.secondary, imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon, contentDescription = item.title) },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colors.secondary)
                    )
                }
            }
        }){
        Box(modifier = Modifier.fillMaxSize().padding(0.dp)){
            Column(modifier = Modifier.fillMaxSize()){
                CustomSearchViewRight(placeholder = "Search your favourite city.", search = searchText, modifier = Modifier.fillMaxWidth().background(color = Color.Transparent).padding(start = 16.dp, end = 16.dp, top = 8.dp), onValueChange = { text -> searchText = text }, weather = {
                    // Update local weather list
                    scope.launch { if (it.main?.temp!! > 0) localWeatherList = searchScreenViewModel.getListOfRecentlySearchedWeather() as ArrayList }
                })
                if(localWeatherList.isNotEmpty())
                    LazyColumn(modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp, top = 4.dp, bottom = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(localWeatherList.size) { i ->
                            Card(modifier = Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 2.dp), backgroundColor = Color.LightGray, border = BorderStroke(1.dp, MaterialTheme.colors.surface)){
                                Box(modifier = Modifier.fillMaxSize().padding(end = 8.dp, start = 10.dp)){
                                    Column(modifier = Modifier.align(Alignment.CenterStart).padding(8.dp)){
                                        Text(style = MaterialTheme.typography.h2, textAlign = TextAlign.Center, text = localWeatherList[i].city)
                                        Image(painter = painterResource(id = getWeatherIcon(localWeatherList[i].icon)), contentDescription = null)
                                    }
                                    Text(style = MaterialTheme.typography.h1, modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp), textAlign = TextAlign.Start, text = "${"%.0f".format(convertTemperature(localWeatherList[i].temp))}\u00B0")
                                }
                            }
                        }
                    }
            }
        }
    }
}
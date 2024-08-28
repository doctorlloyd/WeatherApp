package com.lloyd.weatherapp.utils.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.lloyd.weatherapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    h1 = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),letterSpacing = 0.sp, fontWeight = FontWeight.SemiBold, fontSize = 36.sp, lineHeight = 36.sp),
    h2 = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_bold)), letterSpacing = 0.sp, fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = 24.sp),
    h3 = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_black)),letterSpacing = 0.sp, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp, lineHeight = 28.sp),
)
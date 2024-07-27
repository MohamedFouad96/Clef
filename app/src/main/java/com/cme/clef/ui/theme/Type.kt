package com.cme.clef.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(


    titleLarge = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        fontSize = 24.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 18.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 16.sp,
    ),


    labelLarge = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = nunitoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),



    )
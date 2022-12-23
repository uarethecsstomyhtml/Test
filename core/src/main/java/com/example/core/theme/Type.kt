package com.example.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.R

val smallTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf)),
        fontSize = 18.sp
    )
)
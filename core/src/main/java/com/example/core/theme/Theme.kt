package com.example.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BaseColors.Purple80,
    secondary = BaseColors.PurpleGrey80,
    tertiary = BaseColors.Pink80,
    surface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = BaseColors.Purple40,
    secondary = BaseColors.PurpleGrey40,
    tertiary = BaseColors.Pink40,
    surface = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ExperienceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    val currentWindow = (view.context as? Activity)?.window ?: throw Exception("Not in an activity - unable to get Window reference")
    if (!view.isInEditMode) {
        SideEffect {
            currentWindow.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    // TODO(Add dimensions typography)
    val dimensions = smallDimensions
    // TODO(Add tablet typography)
    val typography = smallTypography
    // TODO(Add tablet shapes)
    val shapes = SmallShapes

    ProvideDimens(dimensions = dimensions) {
        MaterialTheme(
            shapes = shapes,
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }

}
package com.example.navigation.mainmenu

import androidx.annotation.StringRes
import com.example.core.R

sealed class MainMenuNavigation(
    val route: String,
    @StringRes val resourceId: Int
) {
    object Main : MainMenuNavigation(MAIN, R.string.bottom_navigation_bar_main)
    object Profile : MainMenuNavigation(PROFILE, R.string.bottom_navigation_bar_main)

    companion object {
        private const val MAIN = "com.example.navigation.main.Main"
        private const val PROFILE = "com.example.navigation.main.Profile"
    }
}
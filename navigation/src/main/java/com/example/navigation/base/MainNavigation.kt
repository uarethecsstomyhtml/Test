package com.example.navigation.base

sealed class MainNavigation(val route: String) {

    object Auth: MainNavigation(AUTH)

    object Main: MainNavigation(MAIN)

    companion object {
        private const val AUTH = "com.example.navigation.AUTH"
        private const val MAIN = "com.example.navigation.MAIN"
    }
}
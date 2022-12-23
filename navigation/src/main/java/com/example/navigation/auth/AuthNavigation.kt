package com.example.navigation.auth


sealed class AuthNavigation(val route: String) {

    object SignIn: AuthNavigation(SIGN_IN)

    object SignUp: AuthNavigation(SIGN_UP)

    companion object {
        private const val SIGN_IN = "com.example.navigation.auth.SIGN_IN"
        private const val SIGN_UP = "com.example.navigation.auth.SIGN_UP"
    }
}
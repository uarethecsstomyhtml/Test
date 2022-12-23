package com.example.navigation.auth

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.auth.signin.SignInScreen
import com.example.auth.signup.SignUpScreen
import com.example.navigation.base.MainNavigation


fun NavGraphBuilder.addAuthGraph(navController: NavController) {
    navigation(
        route = MainNavigation.Auth.route,
        startDestination = AuthNavigation.SignIn.route
    ) {
        composable(AuthNavigation.SignIn.route) {
            SignInScreen(
                onNavigateToMain = { navController.navigate(MainNavigation.Main.route) },
                onNavigateToSignUp = { navController.navigate(AuthNavigation.SignUp.route) }
            )
        }
        composable(AuthNavigation.SignUp.route) {
            SignUpScreen(
                onNavigateToMain = { navController.navigate(MainNavigation.Main.route) }
            )
        }
    }
}
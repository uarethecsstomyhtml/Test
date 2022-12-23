package com.example.navigation.base


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.auth.addAuthGraph
import com.example.navigation.mainmenu.MainNavigationHost

@Composable
fun MainNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainNavigation.Auth.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        addAuthGraph(navController = navController)
        composable(MainNavigation.Main.route) {
            MainNavigationHost()
        }
    }
}
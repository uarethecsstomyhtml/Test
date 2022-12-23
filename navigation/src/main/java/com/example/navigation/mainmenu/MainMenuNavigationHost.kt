package com.example.navigation.mainmenu

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.R
import com.example.core.components.image.BaseIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MainScreenBottomBar(navController = navController) },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MainMenuNavigation.Profile.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(MainMenuNavigation.Profile.route) {

                }
                composable(MainMenuNavigation.Main.route) {

                }
            }
        }
    )
}

@Composable
private fun MainScreenBottomBar(navController: NavHostController) {
    val screens = listOf(MainMenuNavigation.Main, MainMenuNavigation.Profile)
    NavigationBar(
        containerColor = Color(0xFF2F73DA)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { BaseIcon(drawable = R.drawable.ic_arrow_back) },
                label = { Text(stringResource(screen.resourceId)) }
            )
        }
    }
}

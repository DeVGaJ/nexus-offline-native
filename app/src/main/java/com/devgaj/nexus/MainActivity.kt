// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.devgaj.nexus.ui.navigation.Screen
import com.devgaj.nexus.ui.screens.games.GamesScreen
import com.devgaj.nexus.ui.screens.library.LibraryScreen
import com.devgaj.nexus.ui.screens.share.ShareScreen
import com.devgaj.nexus.ui.screens.wiki.WikiScreen
import com.devgaj.nexus.ui.theme.NexusOfflineTheme
import com.devgaj.nexus.ui.theme.PureBlack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexusOfflineTheme {
                val navController = rememberNavController()
                val screens = listOf(
                    Screen.Library,
                    Screen.Wiki,
                    Screen.Games,
                    Screen.Share
                )

                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = PureBlack,
                            contentColor = MaterialTheme.colorScheme.primary
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                NavigationBarItem(
                                    icon = { Icon(screen.icon, contentDescription = screen.title) },
                                    label = { Text(screen.title) },
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
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                                        indicatorColor = PureBlack
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Library.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Library.route) { LibraryScreen() }
                        composable(Screen.Wiki.route) { WikiScreen() }
                        composable(Screen.Games.route) { GamesScreen() }
                        composable(Screen.Share.route) { ShareScreen() }
                    }
                }
            }
        }
    }
}

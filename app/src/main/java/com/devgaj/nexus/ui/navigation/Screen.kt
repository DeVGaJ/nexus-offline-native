// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Library : Screen("library", "Library", Icons.Default.Book)
    object Wiki : Screen("wiki", "Wiki", Icons.Default.Info)
    object Games : Screen("games", "Arcade", Icons.Default.Games)
    object Share : Screen("share", "MeshLink", Icons.Default.Share)
}

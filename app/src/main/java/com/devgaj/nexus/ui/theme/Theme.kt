// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AccentColor,
    secondary = DarkGray,
    tertiary = TextSecondary,
    background = PureBlack,
    surface = DarkGray,
    onPrimary = PureBlack,
    onSecondary = TextPrimary,
    onTertiary = PureBlack,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
)

@Composable
fun NexusOfflineTheme(
    content: @Composable () -> Unit
) {
    // Ultra-Dark Mode is forced for battery saving
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

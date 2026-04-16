// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.screens.share

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devgaj.nexus.ui.theme.TextPrimary

@Composable
fun ShareScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "MeshLink: P2P APK & File Sharing", color = TextPrimary)
    }
}

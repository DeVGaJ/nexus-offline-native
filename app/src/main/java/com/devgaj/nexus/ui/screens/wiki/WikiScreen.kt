// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.screens.wiki

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devgaj.nexus.data.local.entities.LocalContent
import com.devgaj.nexus.ui.components.ContentCard

/**
 * WikiScreen displays a collection of knowledge resources.
 * Professional implementation using shared components and standard data models.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WikiScreen() {
    val context = LocalContext.current
    
    // In a professional app, this list would be fetched from a Repository/Room
    val wikiResources = listOf(
        LocalContent(
            title = "Project Gutenberg",
            description = "Over 70,000 free eBooks, including many classics in the public domain.",
            url = "https://www.gutenberg.org/",
            contentType = "LINK",
            category = "eBooks"
        ),
        LocalContent(
            title = "Archive.org",
            description = "A huge library of free books, movies, software, music, and more.",
            url = "https://archive.org/",
            contentType = "LINK",
            category = "Library"
        ),
        LocalContent(
            title = "MDN Web Docs",
            description = "Excellent resources for learning web development (HTML, CSS, JS).",
            url = "https://developer.mozilla.org/",
            contentType = "LINK",
            category = "Learning"
        ),
        LocalContent(
            title = "Khan Academy",
            description = "Free world-class education for anyone, anywhere.",
            url = "https://www.khanacademy.org/",
            contentType = "LINK",
            category = "Education"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("The Knowledge Vault", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Knowledge Resources",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            items(wikiResources) { resource ->
                ContentCard(
                    content = resource,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resource.url))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.ui.screens.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devgaj.nexus.data.local.entities.LocalContent

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen() {
    val context = LocalContext.current
    
    // Featured resources from public domains like Project Gutenberg and Archive.org
    val featuredBooks = listOf(
        LocalContent(
            title = "The Adventures of Sherlock Holmes",
            description = "Arthur Conan Doyle's famous detective stories.",
            contentType = "PDF",
            category = "Classic",
            isDownloaded = true,
            filePath = "https://www.gutenberg.org/files/1661/1661-h/1661-h.htm"
        ),
        LocalContent(
            title = "A Tale of Two Cities",
            description = "Charles Dickens' masterpiece set in London and Paris.",
            contentType = "EPUB",
            category = "Classic",
            isDownloaded = true,
            filePath = "https://www.gutenberg.org/ebooks/98.epub.images"
        ),
        LocalContent(
            title = "Meditations - Marcus Aurelius",
            description = "Stoic philosophy and personal reflections.",
            contentType = "PDF",
            category = "Philosophy",
            isDownloaded = true,
            filePath = "https://manybooks.net/titles/aureliusm1587715877-8.html"
        ),
        LocalContent(
            title = "Alice in Wonderland",
            description = "Lewis Carroll's fantasy world.",
            contentType = "PDF",
            category = "Children",
            isDownloaded = false,
            filePath = "https://www.gutenberg.org/files/11/11-h/11-h.htm"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("The Library", fontWeight = FontWeight.Bold) }
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
                    text = "Featured Offline Reads",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            items(featuredBooks) { content ->
                LibraryItemCard(content) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(content.filePath))
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun LibraryItemCard(content: LocalContent, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (content.contentType == "PDF") Icons.Default.PictureAsPdf else Icons.Default.Book,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = content.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = content.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (!content.isDownloaded) {
                    Text(text = "Available to Read Online", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                } else {
                    Text(text = "Offline Access Enabled", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

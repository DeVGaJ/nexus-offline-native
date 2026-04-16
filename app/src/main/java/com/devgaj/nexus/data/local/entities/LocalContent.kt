// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_content")
data class LocalContent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String = "",
    val url: String = "", // Source URL if applicable
    val filePath: String = "", // Local path if downloaded
    val contentType: String, // LINK, PDF, EPUB, MD, ZIP
    val category: String, // Library, Wiki, Arcade
    val lastAccessed: Long = System.currentTimeMillis(),
    val fileSize: Long = 0,
    val isDownloaded: Boolean = false
)

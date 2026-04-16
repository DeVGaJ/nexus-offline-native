// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.data.local.dao

import androidx.room.*
import com.devgaj.nexus.data.local.entities.LocalContent
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {
    @Query("SELECT * FROM local_content ORDER BY lastAccessed DESC")
    fun getAllContent(): Flow<List<LocalContent>>

    @Query("SELECT * FROM local_content WHERE category = :category")
    fun getContentByCategory(category: String): Flow<List<LocalContent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(content: LocalContent)

    @Delete
    suspend fun deleteContent(content: LocalContent)
}

// Developed by DeVGaJ - Offline Revolution.
package com.devgaj.nexus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devgaj.nexus.data.local.dao.ContentDao
import com.devgaj.nexus.data.local.entities.LocalContent

@Database(entities = [LocalContent::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}

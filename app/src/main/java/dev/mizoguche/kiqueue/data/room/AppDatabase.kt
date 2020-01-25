package dev.mizoguche.kiqueue.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PodcastTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun podcastDao(): PodcastDao
}
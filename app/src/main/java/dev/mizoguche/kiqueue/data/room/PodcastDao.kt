package dev.mizoguche.kiqueue.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PodcastDao {
    @Query("SELECT * FROM podcast")
    fun selectAll(): List<PodcastTable>

    @Query("SELECT * FROM podcast WHERE feed_url = :feedUrl")
    fun findByFeedUrl(feedUrl: String): PodcastTable?

    @Insert
    fun insertAll(vararg podcasts: PodcastTable)

    @Update
    fun updateAll(vararg podcasts: PodcastTable)

    @Delete
    fun delete(podcast: PodcastTable)
}
package dev.mizoguche.kiqueue.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PodcastDao {
    @Query("SELECT * FROM podcast")
    fun getAll(): List<Podcast>

    @Query("SELECT * FROM podcast WHERE feed_url = :feedUrl")
    fun findByFeedUrl(feedUrl: String): Podcast

    @Insert
    fun insertAll(vararg podcasts: Podcast)

    @Delete
    fun delete(podcast: Podcast)
}
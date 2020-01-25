package dev.mizoguche.kiqueue.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mizoguche.kiqueue.domain.Podcast

@Entity(tableName = "podcast")
data class PodcastTable(
    @PrimaryKey @ColumnInfo(name = "feed_url") val feedUrl: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)

fun Podcast.toPodcastTable(): PodcastTable = PodcastTable(
    feedUrl = this.feedUrl.value,
    title = this.title.value,
    imageUrl = this.imageUrl.value,
    description = this.description.value
)

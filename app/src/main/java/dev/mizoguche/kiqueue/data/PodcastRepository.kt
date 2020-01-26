package dev.mizoguche.kiqueue.data

import android.util.Log
import dev.mizoguche.kiqueue.data.room.PodcastDao
import dev.mizoguche.kiqueue.data.room.toPodcastTable
import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl
import dev.mizoguche.kiqueue.domain.Podcasts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PodcastRepository(
    private val xmlClient: XmlClient,
    private val podcastDao: PodcastDao,
    private val parser: PodcastXmlParser
) {
    suspend fun selectAll(): Podcasts {
        val podcasts = withContext(Dispatchers.Default) {
            podcastDao.selectAll().map { it.toPodcast() }
        }
        return Podcasts(podcasts)
    }

    suspend fun findBy(url: PodcastFeedUrl): Podcast {
        return withContext(Dispatchers.Default) {
            val response = xmlClient.Get(url.value)
            val podcast = parser.parse(response)
            val result = podcastDao.findByFeedUrl(podcast.feedUrl.value)
            if (result == null) {
                podcastDao.insertAll(podcast.toPodcastTable())
            } else {
                podcastDao.updateAll(podcast.toPodcastTable())
            }
            return@withContext podcast
        }
    }
}
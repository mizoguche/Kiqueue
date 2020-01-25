package dev.mizoguche.kiqueue.data

import dev.mizoguche.kiqueue.data.room.PodcastDao
import dev.mizoguche.kiqueue.data.room.toPodcastTable
import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl

class PodcastRepository(
    private val xmlClient: XmlClient,
    private val podcastDao: PodcastDao,
    private val parser: PodcastXmlParser
) {
    suspend fun findBy(url: PodcastFeedUrl): Podcast {
        val response = xmlClient.Get(url.value)
        val podcast = parser.parse(response)
        val result = podcastDao.findByFeedUrl(podcast.feedUrl.value)
        if (result == null) {
            podcastDao.insertAll(podcast.toPodcastTable())
        } else {
            podcastDao.updateAll(podcast.toPodcastTable())
        }
        return podcast
    }
}
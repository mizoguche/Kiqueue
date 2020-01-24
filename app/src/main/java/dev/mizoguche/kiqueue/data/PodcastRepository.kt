package dev.mizoguche.kiqueue.data

import dev.mizoguche.kiqueue.data.room.PodcastDao
import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl

class PodcastRepository(private val xmlClient: XmlClient, private val podcastDao: PodcastDao, private val parser: PodcastXmlParser) {
    suspend fun findBy(url: PodcastFeedUrl): Podcast {
        val response = xmlClient.Get(url.value)
        return parser.parse(response)
    }
}
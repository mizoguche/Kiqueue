package dev.mizoguche.kiqueue.data

import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl

class PodcastRepository(private val parser: PodcastXmlParser) {
    val xmlClient: XmlClient = XmlClient()

    suspend fun find(url: PodcastFeedUrl): Podcast {
        val response = xmlClient.Get(url.value)
        return parser.parse(response)
    }
}
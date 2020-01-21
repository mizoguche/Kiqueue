package dev.mizoguche.kiqueue.data

import dev.mizoguche.kiqueue.domain.Podcast

interface PodcastXmlParser {
    fun parse(podcastXml: String): Podcast
}
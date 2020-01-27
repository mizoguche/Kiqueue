package dev.mizoguche.kiqueue.domain

data class PodcastTitle(val value: String)
data class PodcastDescription(val value: String)
data class PodcastFeedUrl(val value: String)
data class PodcastImageUrl(val value: String)

data class Podcasts(private val value: List<Podcast>) : List<Podcast> by value

data class Podcast(
    val title: PodcastTitle,
    val description: PodcastDescription,
    val feedUrl: PodcastFeedUrl,
    val imageUrl: PodcastImageUrl
)
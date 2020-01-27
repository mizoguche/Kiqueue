package dev.mizoguche.kiqueue.domain

inline class PodcastTitle(val value: String)
inline class PodcastDescription(val value: String)
inline class PodcastFeedUrl(val value: String)
inline class PodcastImageUrl(val value: String)

data class Podcasts(private val value: List<Podcast>) : List<Podcast> by value

data class Podcast(
    val title: PodcastTitle,
    val description: PodcastDescription,
    val feedUrl: PodcastFeedUrl,
    val imageUrl: PodcastImageUrl
)
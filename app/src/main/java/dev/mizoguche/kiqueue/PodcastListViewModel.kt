package dev.mizoguche.kiqueue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.mizoguche.kiqueue.data.PodcastRepository
import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl
import dev.mizoguche.kiqueue.domain.Podcasts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PodcastListViewModel(private val podcastRepository: PodcastRepository) : ViewModel() {
    private val podcasts: MutableLiveData<Podcasts> by lazy {
        MutableLiveData<Podcasts>().also {
            GlobalScope.launch(Dispatchers.Main) {
                it.value = podcastRepository.selectAll()
            }
        }
    }

    fun getAllPodcasts(): LiveData<Podcasts> {
        return podcasts
    }

    suspend fun addPodcastFeed(feedUrl: PodcastFeedUrl): Podcast {
        val podcast = podcastRepository.findBy(feedUrl)
        val all =  podcastRepository.selectAll()
        GlobalScope.launch(Dispatchers.Main) {
            podcasts.value = all
        }
        return podcast
    }
}
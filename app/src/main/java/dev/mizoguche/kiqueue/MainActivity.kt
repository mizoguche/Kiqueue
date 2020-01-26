package dev.mizoguche.kiqueue

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import dev.mizoguche.kiqueue.domain.PodcastFeedUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val podcastListViewModel: PodcastListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)

            val feedUrlInput = layoutInflater.inflate(R.layout.dialog_add_feed_url, null)
            val dialog = builder
                .setTitle(R.string.dialog_title_add_feed_url)
                .setView(feedUrlInput)
                .setPositiveButton("Add") { _, _ ->
                    val url = feedUrlInput.findViewById<EditText>(R.id.feed_url).text
                    GlobalScope.launch {
                        val addedPodcast = podcastListViewModel.addPodcastFeed(PodcastFeedUrl(url.toString()))
                        Snackbar.make(view, "Added feed: ${addedPodcast.title.value}", Snackbar.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
            dialog.show()
        }

        Log.d(this::class.java.canonicalName, "start getAllPodcasts")
        val podcasts = podcastListViewModel.getAllPodcasts()
            podcasts.observe(this@MainActivity) {
            it.forEach { Log.d(this::class.java.canonicalName, "podcasts: $it") }
        }
        podcasts.value?.forEach  { Log.d(this::class.java.canonicalName, "podcasts: $it") }
    }
}

package dev.mizoguche.kiqueue

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
                    Snackbar.make(view, "Added feed: $url", Snackbar.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
            dialog.show()
        }
    }
}

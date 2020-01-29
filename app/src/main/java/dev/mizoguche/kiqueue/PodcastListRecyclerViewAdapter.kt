package dev.mizoguche.kiqueue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.mizoguche.kiqueue.PodcastListFragment.OnListFragmentInteractionListener
import dev.mizoguche.kiqueue.databinding.FragmentPodcastlistItemBinding
import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.Podcasts

class PodcastListRecyclerViewAdapter(
    private val values: LiveData<Podcasts>,
    private val listener: OnListFragmentInteractionListener?,
    private val fragment: Fragment
) : RecyclerView.Adapter<PodcastListRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Podcast
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentPodcastlistItemBinding.inflate(layoutInflater, parent, false)
        binding.lifecycleOwner = fragment
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.value?.get(position)
        holder.binding.setVariable(BR.podcast, item)
        holder.binding.root.setOnClickListener { listener?.onListFragmentInteraction(item) }
        holder.binding.executePendingBindings()
        if (item != null) {
            Glide.with(fragment)
                .load(item.imageUrl.value.replace("http://", "https://"))
                .into(holder.binding.image)
        }
    }

    override fun getItemCount(): Int = values.value?.size ?: 0

    inner class ViewHolder(val binding: FragmentPodcastlistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        override fun toString(): String {
            return "${super.toString()} '${binding.podcast?.title}'"
        }
    }
}

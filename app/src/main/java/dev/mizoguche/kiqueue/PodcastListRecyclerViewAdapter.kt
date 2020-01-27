package dev.mizoguche.kiqueue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import dev.mizoguche.kiqueue.PodcastListFragment.OnListFragmentInteractionListener
import dev.mizoguche.kiqueue.databinding.FragmentPodcastlistItemBinding
import dev.mizoguche.kiqueue.domain.Podcast
import dev.mizoguche.kiqueue.domain.Podcasts

class PodcastListRecyclerViewAdapter(
    private val values: LiveData<Podcasts>,
    private val listener: OnListFragmentInteractionListener?,
    private val lifecycleOwner: LifecycleOwner
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
        binding.lifecycleOwner = lifecycleOwner
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.value?.get(position)
        holder.binding.setVariable(BR.podcast, item)
        holder.binding.root.setOnClickListener { listener?.onListFragmentInteraction(item) }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = values.value?.size ?: 0

    inner class ViewHolder(val binding: FragmentPodcastlistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        override fun toString(): String {
            return "${super.toString()} '${binding.podcast?.title}'"
        }
    }
}

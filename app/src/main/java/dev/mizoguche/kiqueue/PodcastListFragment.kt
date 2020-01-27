package dev.mizoguche.kiqueue

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.mizoguche.kiqueue.domain.Podcast
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PodcastListFragment.OnListFragmentInteractionListener] interface.
 */
class PodcastListFragment : Fragment() {
    private val podcastListViewModel: PodcastListViewModel by viewModel()
    private lateinit var adapter: PodcastListRecyclerViewAdapter
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val podcasts = podcastListViewModel.getAllPodcasts()
        adapter = PodcastListRecyclerViewAdapter(podcasts, listener, this)
        podcasts.observe(this) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_podcastlist_list, container, false)
        if (view is RecyclerView) {
            view.adapter = adapter
            view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Podcast?)
    }
}

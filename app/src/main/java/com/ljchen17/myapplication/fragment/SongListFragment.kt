package com.ljchen17.myapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ljchen17.myapplication.R
import com.ljchen17.myapplication.SongListAdapter
import com.ljchen17.myapplication.data.model.SongDetails
import kotlinx.android.synthetic.main.fragment_song_list.*

/**
 * A simple [Fragment] subclass.
 */
class SongListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: SongListAdapter
    private var OnSongClickListener: OnSongClickListener? = null
    private var allSongs: MutableList<SongDetails> = ArrayList<SongDetails>()

    companion object {
        val TAG: String = SongListFragment::class.java.simpleName
        const val ARG_SONGLIST = "arg_songlist"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val songlist = args.getParcelableArrayList<SongDetails>(ARG_SONGLIST)
            if (songlist != null) {
                this.allSongs = songlist.toList() as MutableList<SongDetails>
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSongClickListener) {
            OnSongClickListener = context
        }
    }

    fun shuffleList() {
        var newSong = allSongs.shuffled()
        adapter.shuffleList(newSong as MutableList<SongDetails>)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(context)
        rvMusic.layoutManager = linearLayoutManager
        adapter = SongListAdapter(allSongs)

        adapter.onSongClickListener = { someSong: SongDetails ->
            OnSongClickListener?.onSongClicked(someSong)
        }

        rvMusic.adapter = adapter
        rvMusic.setHasFixedSize(true)
    }
}

interface OnSongClickListener{
    fun onSongClicked(song: SongDetails)
}

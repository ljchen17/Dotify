package com.ljchen17.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*
import kotlinx.android.synthetic.main.now_playing.view.*
import java.util.Collections.shuffle

class SongListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var adapter: SongListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        linearLayoutManager = LinearLayoutManager(this)
        rvMusic.layoutManager = linearLayoutManager


        val allSongs: List<Song> = SongDataProvider.getAllSongs()

        adapter = SongListAdapter(allSongs)

        shuffleSongs.setOnClickListener {
            val newSong = allSongs.shuffled()

            adapter.shuffleSongs(newSong)
        }

        rvMusic.adapter = adapter

        rvMusic.setHasFixedSize(true)
    }

}
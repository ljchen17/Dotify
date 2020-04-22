package com.ljchen17.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.ljchen17.myapplication.MainActivity.Companion.SONG_ARTIST
import com.ljchen17.myapplication.MainActivity.Companion.SONG_IMAGE
import com.ljchen17.myapplication.MainActivity.Companion.SONG_TITLE
import kotlinx.android.synthetic.main.activity_song_list.*
import kotlinx.android.synthetic.main.item_song.*
import kotlinx.android.synthetic.main.now_playing.view.*
import java.util.Collections.shuffle

class SongListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var adapter: SongListAdapter

    private lateinit var currentSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        linearLayoutManager = LinearLayoutManager(this)
        rvMusic.layoutManager = linearLayoutManager


        val allSongs: List<Song> = SongDataProvider.getAllSongs()

        adapter = SongListAdapter(allSongs)


        // Set on item Click for the adapter
        adapter.onSongClickListener = { someSong: Song ->
            songNowPlaying(someSong)
        }

        shuffleSongs.setOnClickListener {
            val newSong = allSongs.shuffled()
            adapter.shuffleSongs(newSong)
        }

        rvMusic.adapter = adapter

        rvMusic.setHasFixedSize(true)
    }

    fun songNowPlaying(song: Song) {
        currentSong = song
        val miniPlayer = findViewById<TextView>(R.id.miniPlayer)
        miniPlayer.text = "${song.title} - ${song.artist}"
    }

    fun enterMainPage(view: View) {
       // do intent stufff
       // currentSong.largeImageID
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(SONG_IMAGE, currentSong.largeImageID)
        intent.putExtra(SONG_TITLE, currentSong.title)
        intent.putExtra(SONG_ARTIST, currentSong.artist)
        startActivity(intent)
    }

}
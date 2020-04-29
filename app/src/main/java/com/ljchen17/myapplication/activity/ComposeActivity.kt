package com.ljchen17.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.ljchen17.myapplication.R
import com.ljchen17.myapplication.fragment.OnSongClickListener
import com.ljchen17.myapplication.fragment.NowPlayingFragment
import com.ljchen17.myapplication.fragment.SongListFragment

class ComposeActivity : AppCompatActivity(), OnSongClickListener {

    private var currentSong : Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_compose))


        val songListFragment = SongListFragment()

        val argumentBundle = Bundle().apply {

            val songList = SongDataProvider.getAllSongs()

            putParcelableArrayList(SongListFragment.ARG_SONGLIST, ArrayList(songList))
        }

        songListFragment.arguments = argumentBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment)
            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0

            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                var miniPlayerComponent = findViewById<ConstraintLayout>(R.id.miniPlayerComponent)
                miniPlayerComponent.visibility = View.VISIBLE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        val shuffleButton = findViewById<Button>(R.id.shuffleSongs)
        shuffleButton.setOnClickListener {
            songListFragment.shuffleSongs()
        }
    }

    private fun getSongDetailFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

    fun nowPlaying(view: View) {

        var miniPlayerComponent = findViewById<ConstraintLayout>(R.id.miniPlayerComponent)
        miniPlayerComponent.visibility = View.GONE

        var nowPlayingFragment = getSongDetailFragment()

        if (nowPlayingFragment == null) {
            nowPlayingFragment = NowPlayingFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(NowPlayingFragment.ARG_SONG, currentSong)
            }
            nowPlayingFragment.arguments = argumentBundle

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                .addToBackStack(NowPlayingFragment.TAG)
                .commit()
        } else {
            //nowPlayingFragment.updateSong(currentSong)
        }
    }

    override fun onSongClicked(song:Song) {

        val miniPlayer = findViewById<TextView>(R.id.miniPlayer)
        miniPlayer.text = "${song.title} - ${song.artist}"

        currentSong = song
    }
}
package com.ljchen17.myapplication.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ljchen17.myapplication.R
import com.ljchen17.myapplication.data.MusicApplication
import com.ljchen17.myapplication.data.model.SongDetails
import com.ljchen17.myapplication.fragment.NowPlayingFragment
import com.ljchen17.myapplication.fragment.OnSongClickListener
import com.ljchen17.myapplication.fragment.SongListFragment
import com.ljchen17.myapplication.fragment.SongListFragment.Companion.ARG_SONGLIST


class ComposeActivity : AppCompatActivity(), OnSongClickListener {

    private var currentSong : SongDetails? = null

    companion object {
        val STATE_SONG = "currentSong"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_compose))

        if (savedInstanceState != null) {

                with(savedInstanceState) {
                    currentSong = getParcelable(STATE_SONG)
                    setMiniDisplay()
                }

            val nowPlayingFragment = getSongDetailFragment()

            if (nowPlayingFragment != null) {

                var miniPlayerComponent = findViewById<ConstraintLayout>(R.id.miniPlayerComponent)
                miniPlayerComponent.visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(true)

            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)

            }

        } else {



            val apiManager = (application as MusicApplication).apiManager

            apiManager.getSongsList ({ allSongs ->

                val songList = allSongs.songs

                val argumentBundle = Bundle().apply {
                   putParcelableArrayList(ARG_SONGLIST, ArrayList(songList))
                }

                val songListFragment = SongListFragment()

                val shuffleButton = findViewById<Button>(R.id.shuffleSongs)
                shuffleButton.setOnClickListener {
                    songListFragment.shuffleList()
                }
                songListFragment.arguments = argumentBundle

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, songListFragment,SongListFragment.TAG)
                    .commit()

                supportActionBar?.setDisplayHomeAsUpEnabled(false)

            },
                {
                    Toast.makeText(this, "errror ", Toast.LENGTH_SHORT).show()
                })



        }

        var songListFragment = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment

        if (songListFragment!=null) {

             val shuffleButton = findViewById<Button>(R.id.shuffleSongs)
            shuffleButton.setOnClickListener {
                songListFragment.shuffleList()
            }
        }

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
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.run {
            putParcelable(STATE_SONG, currentSong)
        }
        super.onSaveInstanceState(outState)
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
            nowPlayingFragment.updateSong(currentSong)
        }
    }

    override fun onSongClicked(song:SongDetails) {

        currentSong = song
        setMiniDisplay()
    }

    fun setMiniDisplay() {

        if (currentSong != null) {
            val miniPlayer = findViewById<TextView>(R.id.miniPlayer)
            miniPlayer.text = "${currentSong!!.title} - ${currentSong!!.artist}"
        }
    }
}
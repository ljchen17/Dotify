package com.ljchen17.myapplication.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ljchen17.myapplication.R
import com.ljchen17.myapplication.data.MusicApplication
import com.ljchen17.myapplication.data.MusicManager
import com.ljchen17.myapplication.data.model.SongDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.now_playing.*
import kotlinx.android.synthetic.main.navigation.*

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    lateinit var musicManager: MusicManager

    var imageColorEdit = false
    private var song: SongDetails? = null

    companion object {

        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val ARG_SONG = "arg_song"
        const val PLAY_TIMES = "play_times"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                musicManager.playTimes = getInt(NowPlayingFragment.PLAY_TIMES)
                setPlayTimes()
            }
        }

        arguments?.let { args ->
            val song = args.getParcelable<SongDetails>(ARG_SONG)
            if (song != null) {
                this.song = song
            }
        }
    }

    fun updateSong(song: SongDetails?) {
        this.song = song
        updateSongViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onAttach(context: Context?) {

        super.onAttach(context)

        val musicApp = context?.applicationContext as MusicApplication

        musicManager = musicApp.musicManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSongViews()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.run {
            putInt(PLAY_TIMES, musicManager.playTimes)
        }
        super.onSaveInstanceState(outState)
    }

    private fun updateSongViews() {
        song?.let {
            playTimes?.text = "${musicManager.playTimes} plays"
            Picasso.get().load(song!!.largeImageURL).into(cover)

            cover.setOnLongClickListener {

                imageColorEdit = if (imageColorEdit) {
                    currentsong?.setTextColor(Color.BLACK)
                    artists.setTextColor(Color.BLACK)
                    playTimes.setTextColor(Color.GRAY)
                    false
                } else {
                    currentsong.setTextColor(Color.BLUE)
                    artists.setTextColor(Color.BLUE)
                    playTimes.setTextColor(Color.BLUE)
                    true
                }
                true
            }

            currentsong.text = song!!.title
            artists.text = song!!.artist

            play.setOnClickListener {
                musicManager.playTimes += 1
                playTimes.text = "${musicManager.playTimes} plays"
            }

            previous.setOnClickListener {
                val text = "Skipping to previous track"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
                toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
            }

            next.setOnClickListener {
                val text = "Skipping to next track"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
                toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
            }
        }
    }

    fun setPlayTimes() {
        playTimes?.text = "${musicManager.playTimes} plays"
    }
}

package com.ljchen17.myapplication.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ericchee.songdataprovider.Song

import com.ljchen17.myapplication.R
import kotlinx.android.synthetic.main.item_song.*
import kotlinx.android.synthetic.main.now_playing.*
import kotlinx.android.synthetic.main.user_modification.*
import kotlinx.android.synthetic.main.navigation.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    var randomNumber = Random.nextInt(1000, 10000)

    var editMode = false

    var imageColorEdit = false

    private var song: Song? = null

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName

        const val ARG_SONG = "arg_song"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val song = args.getParcelable<Song>(ARG_SONG)
            if (song != null) {
                this.song = song
            }
        }

    }

    /**fun updateSong(song: Song) {
        this.song = song
        updateSongViews()
    }**/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?){
        super.onViewCreated (view, savedInstanceState)

        playTimes?.text = "$randomNumber plays"

        cover?.setImageResource(song!!.largeImageID)

        cover.setOnLongClickListener {

            imageColorEdit = if (imageColorEdit) {
                currentsong?.setTextColor(Color.BLACK)
                artists.setTextColor(Color.BLACK)
                playTimes.setTextColor(Color.GRAY)
                userInputValue.setTextColor(Color.GRAY)
                false
            } else {
                currentsong.setTextColor(Color.BLUE)
                artists.setTextColor(Color.BLUE)
                playTimes.setTextColor(Color.BLUE)
                userInputValue.setTextColor(Color.BLUE)
                true
            }
            true
        }

        currentsong.text = song!!.title
        artists.text = song!!.artist

        changeUserName.setOnClickListener{

            if (editMode) {

                if (editUserName.text.toString() == "") {
                    val text = "Please type in a username"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                    toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                } else {
                    userInputValue.text = editUserName.text.toString()
                    userInputValue.visibility = View.VISIBLE
                    editUserName.visibility = View.INVISIBLE
                    changeUserName.text = "CHANGE USER"
                    editMode = false
                }

            } else {

                userInputValue.visibility = View.INVISIBLE
                editUserName.visibility = View.VISIBLE
                changeUserName.text = "APPLY"
                editMode = true

            }
        }

        play.setOnClickListener {
            randomNumber += 1
            playTimes.text = "$randomNumber plays"
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

        //()
    }}

    /***private fun updateSongViews() {
        song?.let {
            tvFrom.text = it.from
            tvContent.text = it.content
        }
}**/

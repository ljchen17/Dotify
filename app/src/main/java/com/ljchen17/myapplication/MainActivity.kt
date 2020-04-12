package com.ljchen17.myapplication

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.graphics.Color
import kotlinx.android.synthetic.main.navigation.*

class MainActivity : AppCompatActivity() {

    var randomNumber = Random.nextInt(1000, 10000)

    var editMode = false

    var imageColorEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playTimes = findViewById<TextView>(R.id.playTimes)
        playTimes.text = "$randomNumber plays"

        val imageView = findViewById<ImageView>(R.id.cover)
        imageView.setOnLongClickListener {
            val currentSong = findViewById<TextView>(R.id.currentsong)
            val artists = findViewById<TextView>(R.id.artists)
            val playTimes = findViewById<TextView>(R.id.playTimes)
            val userInputValue = findViewById<TextView>(R.id.userInputValue)

            if (imageColorEdit) {
                currentSong.setTextColor(Color.BLACK)
                artists.setTextColor(Color.BLACK)
                playTimes.setTextColor(Color.GRAY)
                userInputValue.setTextColor(Color.GRAY)
                imageColorEdit = false
            } else {
                currentSong.setTextColor(Color.BLUE)
                artists.setTextColor(Color.BLUE)
                playTimes.setTextColor(Color.BLUE)
                userInputValue.setTextColor(Color.BLUE)
                imageColorEdit = true
            }
            true
        }
    }


    fun changeUserName(view: View) {

        if (editMode) {

            val typeInUserName = findViewById<EditText>(R.id.editUserName)
            val userInputValue = findViewById<TextView>(R.id.userInputValue)

            if (typeInUserName.text.toString() == "") {
                val text = "Please type in a username"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
            } else {

                val changeButtonName = findViewById<Button>(R.id.changeUserName)
                userInputValue.text = typeInUserName.text.toString()
                userInputValue.visibility = View.VISIBLE
                typeInUserName.visibility = View.INVISIBLE
                changeButtonName.text = "CHANGE USER"
                editMode = false
            }


        } else {

            val typeInUserName = findViewById<EditText>(R.id.editUserName)
            val userInputValue = findViewById<TextView>(R.id.userInputValue)
            userInputValue.visibility = View.INVISIBLE
            typeInUserName.visibility = View.VISIBLE

            val changeButtonName = findViewById<Button>(R.id.changeUserName)
            changeButtonName.text = "APPLY"

            editMode = true

        }
    }

    fun clickPlay(view: View) {
        randomNumber += 1
        val playTimes = findViewById<TextView>(R.id.playTimes)
        playTimes.text = "$randomNumber plays"
    }

    fun clickPrevious(view: View) {
        val text = "Skipping to previous track"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
    }

    fun clickNext(view: View) {
        val text = "Skipping to next track"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
    }


}

package com.ljchen17.myapplication

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var randomNumber = Random.nextInt(1000, 10000)

    var editMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val playTimes = findViewById<TextView>(R.id.playTimes)
        playTimes.text = "$randomNumber plays"

    }


    fun changeUserName(view: View) {

        if (editMode) {

            val typeInUserName = findViewById<EditText>(R.id.editUserName)
            val userInputValue = findViewById<TextView>(R.id.userInputValue)
            userInputValue.text = typeInUserName.text.toString()
            userInputValue.visibility = View.VISIBLE
            typeInUserName.visibility = View.INVISIBLE

            val changeButtonName = findViewById<Button>(R.id.changeUserName)
            changeButtonName.text = "CHANGE USER"

            editMode = false

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

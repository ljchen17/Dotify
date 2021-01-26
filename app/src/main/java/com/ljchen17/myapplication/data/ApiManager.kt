package com.ljchen17.myapplication.data
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ljchen17.myapplication.data.model.AllSongs

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getSongsList(onSongsReady: (AllSongs) -> Unit, onError: (() -> Unit)? = null) {
        val musicURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"

        val request = StringRequest(
            Request.Method.GET, musicURL,
            { response ->
                // Success
                val gson = Gson()
                val allSongs = gson.fromJson(response, AllSongs::class.java )

                onSongsReady(allSongs)

            },
            {
                onError?.invoke()
            }
        )
        queue.add(request)
    }

}
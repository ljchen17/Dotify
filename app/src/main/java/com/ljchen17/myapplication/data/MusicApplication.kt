package com.ljchen17.myapplication.data

import android.app.Application

class MusicApplication: Application() {
    lateinit var musicManager: MusicManager
    lateinit var apiManager: ApiManager

    override fun onCreate () {
        super.onCreate()
        musicManager = MusicManager ()
        apiManager = ApiManager (this)
    }
}

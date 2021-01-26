package com.ljchen17.myapplication.data.model

data class AllSongs (
    val title: String,
    val numOfSongs: Int,
    val songs: List<SongDetails>
)

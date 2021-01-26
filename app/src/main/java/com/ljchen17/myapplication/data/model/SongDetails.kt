package com.ljchen17.myapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongDetails (
    val id: String,
    val title: String,
    val artist: String,
    val durationMillis: Long,
    val smallImageURL: String,
    val largeImageURL: String
):Parcelable
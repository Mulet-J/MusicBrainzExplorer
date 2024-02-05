package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Track(
    val number: String?,
    @SerializedName("artist-credit") val artistCredit: ArrayList<ArtistCredit>,
    val position: Int?,
    val title: String?,
    val recording: Recording?,
    val id: String?,
    val length: Int?,
) : Serializable
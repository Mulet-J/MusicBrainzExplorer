package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recording(
    @SerializedName("first-release-date") val firstReleaseDate: String?,
    val isrcs: ArrayList<String>?,
    @SerializedName("artist-credit") val artistCredit: ArrayList<ArtistCredit>?,
    val releases: ArrayList<Release>?,
    val disambiguation: String?,
    val length: Int?,
    val title: String?,
    val id: String?,
    val video: Boolean?,
) : Serializable
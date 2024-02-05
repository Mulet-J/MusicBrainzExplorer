package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Media(
    @SerializedName("track-count") val trackCount: Int?,
    val tracks: ArrayList<Track>,
    val title: String?,
    val position: Int?,
    @SerializedName("track-offset") val trackOffset: Int?,
    @SerializedName("format-id") val formatId: String?,
    val discs: ArrayList<Disc>,
    val format: String?,
) : Serializable
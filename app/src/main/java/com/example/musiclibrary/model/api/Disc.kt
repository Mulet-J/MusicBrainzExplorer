package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Disc(
    @SerializedName("offset-count") val offsetCount: Int?,
    val sectors: Int?,
    val id: String?,
    val offsets: ArrayList<Int>,
) : Serializable
package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArtistWithRecordings(
    val type: String,
    val ipis: List<String>,
    @SerializedName("sort-name") val sortName: String?,
    @SerializedName("end-area") val endArea: String?,
    val id: String,
    val recordings: List<Recording>
): Serializable

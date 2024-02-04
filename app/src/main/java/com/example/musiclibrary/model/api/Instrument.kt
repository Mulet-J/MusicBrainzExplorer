package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class Instrument(
    val name: String?,
    val disambiguation: String?,
    val description: String?,
    val id: String?,
    val type: String?,
    val aliases: ArrayList<Alias>,
    @SerializedName("type-id") val typeId: String?,
)
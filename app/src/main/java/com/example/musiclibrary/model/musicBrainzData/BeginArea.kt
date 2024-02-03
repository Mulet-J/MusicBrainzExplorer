package com.example.musiclibrary.model.musicBrainzData

import com.google.gson.annotations.SerializedName

data class BeginArea(
    val id: String,
    val type: String,
    @SerializedName("type-id")
    val typeId: String,
    val name: String,
    @SerializedName("sort-name")
    val sortName: String,
    @SerializedName("life-span")
    val lifeSpan: LifeSpan2,
)
package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class Place(
    val area: Area?,
    val disambiguation: String?,
    val coordinates: Coordinates?,
    @SerializedName("life-span") val lifeSpan: LifeSpan?,
    val address: String?,
    val type: String?,
    val id: String?,
    val aliases: ArrayList<Alias>,
    val name: String?,
    @SerializedName("type-id") val typeId: String?,
)
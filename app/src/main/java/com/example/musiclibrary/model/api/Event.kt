package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("type-id") val typeId: String?,
    val disambiguation: String?,
    val setlist: String?,
    val type: String?,
    val time: String?,
    val cancelled: Boolean?,
    val aliases: ArrayList<String>,
    @SerializedName("life-span") val lifeSpan: LifeSpan?,
    val id: String?,
    val relations: ArrayList<Relation>,
    val name: String?,

    )
package com.example.musiclibrary.model.musicBrainzData

import com.google.gson.annotations.SerializedName

data class Artist(
    val id: String,
    val type: String?,
    @SerializedName("type-id")
    val typeId: String?,
    val score: Long,
    val name: String,
    @SerializedName("sort-name")
    val sortName: String,
    val country: String?,
    val area: Area?,
    @SerializedName("begin-area")
    val beginArea: BeginArea?,
    val disambiguation: String?,
    val isnis: List<String>?,
    @SerializedName("life-span")
    val lifeSpan: LifeSpan3,
    val aliases: List<Alias>?,
    val tags: List<Tag>?,
    @SerializedName("gender-id")
    val genderId: String?,
    val gender: String?,
    val ipis: List<String>?
)
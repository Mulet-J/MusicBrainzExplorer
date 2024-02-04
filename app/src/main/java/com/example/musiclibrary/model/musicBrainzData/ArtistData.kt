package com.example.musiclibrary.model.musicBrainzData

import com.example.musiclibrary.model.api.Alias
import com.example.musiclibrary.model.api.Area
import com.example.musiclibrary.model.api.BeginArea
import com.example.musiclibrary.model.api.LifeSpan
import com.example.musiclibrary.model.api.Tag

data class ArtistData(
    val id: String?,
    val type: String?,
    val typeId: String?,
    val score: Long,
    val name: String?,
    val sortName: String?,
    val country: String?,
    val area: Area?,
    val beginArea: BeginArea?,
    val disambiguation: String?,
    val isnis: List<String>,
    val lifeSpan: LifeSpan?,
    val aliases: List<Alias>?,
    val tags: List<Tag>?,
    val genderId: String?,
    val gender: String?,
    val ipis: List<String>,
    val endArea: String?,
)
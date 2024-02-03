package com.example.musiclibrary.model.musicBrainzData

data class MusicData(
    val created: String,
    val count: Long,
    val offset: Long,
    val artists: List<Artist>,
)
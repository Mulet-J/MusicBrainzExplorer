package com.example.musiclibrary.model.musicBrainzData

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording

data class MusicData(
    val created: String,
    val count: Long,
    val offset: Long,
    val artists: List<Artist>?,
    val recordings: List<Recording>?,
)
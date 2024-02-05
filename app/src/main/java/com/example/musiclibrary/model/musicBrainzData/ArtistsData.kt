package com.example.musiclibrary.model.musicBrainzData

import com.example.musiclibrary.model.api.Artist
data class ArtistsData(
    val created: String,
    val count: Int,
    val offset: Int,
    val artists: List<Artist>
)

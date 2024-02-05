package com.example.musiclibrary.model.api

import java.io.Serializable

data class ArtistCredit(
    val artist: Artist?,
    val name: String?,
    val joinphrase: String?,
) : Serializable
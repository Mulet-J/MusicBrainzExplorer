package com.example.musiclibrary.model.api

import java.io.Serializable

data class CoverArtArchive(
    val count: Int?,
    val back: Boolean?,
    val darkened: Boolean?,
    val artwork: Boolean?,
    val front: Boolean?,
) : Serializable
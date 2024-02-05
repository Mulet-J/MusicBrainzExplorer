package com.example.musiclibrary.model.api

import java.io.Serializable

data class Genre(
    val id: String?,
    val name: String?,
    val disambiguation: String?,
) : Serializable
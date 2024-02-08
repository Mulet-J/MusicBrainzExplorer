package com.example.musiclibrary.model.api

import java.io.Serializable

data class TextRepresentation(
    val language: String?,
    val script: String?,
) : Serializable
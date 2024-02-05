package com.example.musiclibrary.model.api

import java.io.Serializable

data class Tag(
    val count: Long,
    val name: String,
) : Serializable
package com.example.musiclibrary.model.api

import java.io.Serializable

data class LifeSpan(
    val ended: Boolean?,
    val begin: String?,
    val end: String?,
) : Serializable
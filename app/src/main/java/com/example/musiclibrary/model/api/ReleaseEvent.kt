package com.example.musiclibrary.model.api

import java.io.Serializable

data class ReleaseEvent(
    val date : String?,
    val area : Area?,
) : Serializable
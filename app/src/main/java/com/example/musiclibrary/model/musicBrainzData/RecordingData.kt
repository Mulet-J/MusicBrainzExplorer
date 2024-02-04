package com.example.musiclibrary.model.musicBrainzData

import com.example.musiclibrary.model.api.Recording

data class RecordingData(
    val created: String,
    val count: Int,
    val offset: Int,
    val recordings: List<Recording>
)
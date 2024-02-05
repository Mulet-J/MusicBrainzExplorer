package com.example.musiclibrary.model

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording

data class ArtistDto(
    val artistData: Artist
):DataDto()
data class RecordingDto(
    val recordingData: Recording
): DataDto()
sealed class DataDto
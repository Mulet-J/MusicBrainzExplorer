package com.example.musiclibrary.model

import com.example.musiclibrary.model.musicBrainzData.ArtistData
import com.example.musiclibrary.model.musicBrainzData.RecordingData

data class ArtistDto2(
    val artistData: ArtistData
):DataDto()
data class RecordingDto2(
    val recordingData: RecordingData
): DataDto()
sealed class DataDto
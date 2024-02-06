package com.example.musiclibrary.model

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording
import com.example.musiclibrary.model.api.ReleaseGroup

data class ArtistDto(
    val artistData: Artist
):DataDto()
data class RecordingDto(
    val recordingData: Recording
): DataDto()
data class ReleaseGroupDto(
    val releaseGroupData: ReleaseGroup
): DataDto()
sealed class DataDto
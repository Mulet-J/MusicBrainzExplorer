package com.example.musiclibrary.model

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.model.api.Track

// Data transfer objects (DTOs) for various entities
data class ArtistDto(
    val artistData: Artist // Data related to an artist
):DataDto() // Inherit from DataDto
data class RecordingDto(
    val recordingData: Recording // Data related to a recording
): DataDto() // Inherit from DataDto
data class TrackDto(
    val trackData: Track // Data related to a track
): DataDto() // Inherit from DataDto
data class ReleaseGroupDto(
    val releaseGroupData: ReleaseGroup // Data related to a release group
): DataDto() // Inherit from DataDto

// Sealed class to represent the data transfer objects
sealed class DataDto
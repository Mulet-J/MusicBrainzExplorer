package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.api.ArtistWithRecordings
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import com.example.musiclibrary.model.musicBrainzData.TracksData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class RecordRepository(
    private val musicDataApiService: MusicDataApiService,
) {
    // Searches for recordings based on a query string
    fun searchRecord(input:String): Flowable<RecordingsData>{
        return musicDataApiService.searchRecord(input)
    }

    // Gets an artist's recordings by its ID
    fun searchRecordsByArtist(artistId: String): Flowable<ArtistWithRecordings>{
        return musicDataApiService.getArtistRecordings(artistId)
    }

    // Gets all releases by a release group's ID
    fun getAllReleasesByReleaseGroups(releaseGroupId: String): Flowable<ReleaseGroup>{
        return musicDataApiService.getAllReleases(releaseGroupId)
    }

    // Gets all tracks by a release's ID
    fun getAllTracksByRelease(releaseId: String): Flowable<TracksData>{
        return musicDataApiService.getAllRecordsByRelease(releaseId)
    }
}
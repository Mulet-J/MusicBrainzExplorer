package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.api.ArtistWithRecordings
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.model.api.Track
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import com.example.musiclibrary.model.musicBrainzData.TracksData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class RecordRepository(
    private val musicDataApiService: MusicDataApiService,
) {
    fun searchRecord(input:String): Flowable<RecordingsData>{
        return musicDataApiService.searchRecord(input)
    }

    fun searchRecordsByArtist(artistId: String): Flowable<ArtistWithRecordings>{
        return musicDataApiService.getArtistRecordings(artistId)
    }

    fun getAllReleasesByReleaseGroups(releaseGroupId: String): Flowable<ReleaseGroup>{
        return musicDataApiService.getAllReleases(releaseGroupId)
    }


    fun getAllTracksByRelease(releaseId: String): Flowable<TracksData>{
        return musicDataApiService.getAllRecordsByRelease(releaseId)
    }
}
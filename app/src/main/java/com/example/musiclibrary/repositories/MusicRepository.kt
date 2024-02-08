package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.api.ArtistWithRecordings
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class MusicRepository(
    private val musicDataApiService: MusicDataApiService,
) {
    fun getNirvana(): Flowable<RecordingsData> {
        return musicDataApiService.getNirvana()
    }

    fun searchRecord(input:String): Flowable<RecordingsData>{
        return musicDataApiService.searchRecord(input)
    }

    fun searchRecordsByArtist(artistId: String): Flowable<ArtistWithRecordings>{
        return musicDataApiService.getArtistRecordings(artistId)
    }
}
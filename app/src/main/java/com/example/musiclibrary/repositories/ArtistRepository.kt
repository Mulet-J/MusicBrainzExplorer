package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.musicBrainzData.ArtistsData
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class ArtistRepository(
    private val musicDataApiService: MusicDataApiService,
) {
    fun getNirvana(): Flowable<RecordingsData> {
        return musicDataApiService.getNirvana()
    }
    fun getArtistByGuid(guid: String): Flowable<Artist> {
        return musicDataApiService.getArtistByGuid(guid)
    }

    fun getArtistAndItsReleaseGroupsByGuid(guid: String): Flowable<Artist> {
        return musicDataApiService.getArtistAndItsReleaseGroups(guid)
    }

    fun searchArtist(input:String): Flowable<ArtistsData>{
        return musicDataApiService.searchArtist(input)
    }
}
package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.musicBrainzData.ArtistsData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class ArtistRepository(
    private val musicDataApiService: MusicDataApiService,
) {
    // Gets an artist by its GUID
    fun getArtistByGuid(guid: String): Flowable<Artist> {
        return musicDataApiService.getArtistByGuid(guid)
    }

    // Gets an artist and its recordings by its ID
    fun getArtistAndItsReleaseGroupsByGuid(guid: String): Flowable<Artist> {
        return musicDataApiService.getArtistAndItsReleaseGroups(guid)
    }

    // Searches for an artist based on a query string
    fun searchArtist(input:String): Flowable<ArtistsData>{
        return musicDataApiService.searchArtist(input)
    }
}
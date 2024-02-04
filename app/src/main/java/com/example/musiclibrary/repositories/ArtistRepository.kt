package com.example.musiclibrary.repositories

import android.util.Log
import com.example.musiclibrary.model.musicBrainzData.ArtistData
import com.example.musiclibrary.model.musicBrainzData.MusicData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class ArtistRepository(
    private val musicDataApiService: MusicDataApiService,
) {
    fun getNirvana(): Flowable<MusicData> {
        return musicDataApiService.getNirvana()
    }
    fun getArtistByGuid(guid: String): Flowable<ArtistData> {
        return musicDataApiService.getArtistByGuid(guid)
    }

    fun searchMusic(input:String): Flowable<MusicData>{
        return musicDataApiService.searchMusic(input)
    }

    fun searchArtist(input:String): Flowable<ArtistData>{
        return musicDataApiService.searchArtist(input)
    }
}
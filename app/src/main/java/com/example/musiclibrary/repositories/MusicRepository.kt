package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.musicBrainzData.MusicData
import com.example.musiclibrary.network.services.MusicDataApiService
import io.reactivex.rxjava3.core.Flowable

class MusicRepository(
    private val musicDataApiService: MusicDataApiService,

) {
    fun getNirvana(): Flowable<MusicData> {
        return musicDataApiService.getNirvana("androidMusicBrainz/1.0")    }
}
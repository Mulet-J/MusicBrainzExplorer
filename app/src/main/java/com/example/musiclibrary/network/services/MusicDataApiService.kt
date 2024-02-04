package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.musicBrainzData.MusicData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface MusicDataApiService {

    @GET("artist/?query=nirvana&fmt=json")
    fun getNirvana(): Flowable<MusicData>
}
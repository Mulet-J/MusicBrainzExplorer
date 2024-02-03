package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.musicBrainzData.MusicData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Header

interface MusicDataApiService {

    @GET("artist/?query=nirvana&fmt=json")
    fun getNirvana(@Header("User-Agent") userAgent: String): Flowable<MusicData>
}
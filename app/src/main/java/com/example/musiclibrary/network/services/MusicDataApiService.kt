package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.api.Artist

import com.example.musiclibrary.model.musicBrainzData.ArtistsData
import com.example.musiclibrary.model.musicBrainzData.MusicData
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicDataApiService {

    @GET("artist/?query=nirvana&fmt=json")
    fun getNirvana(): Flowable<RecordingsData>

    @GET("artist/?fmt=json")
    fun searchArtist(@Query("query") query: String): Flowable<ArtistsData>

    @GET("recording/?fmt=json")
    fun searchRecord(@Query("query") query: String): Flowable<RecordingsData>

    @GET("artist/{guid}/?fmt=json")
    fun getArtistByGuid(@Path("guid") guid: String): Flowable<Artist>

}

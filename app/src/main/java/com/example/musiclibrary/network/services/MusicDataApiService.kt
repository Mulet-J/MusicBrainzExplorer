package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.musicBrainzData.ArtistData
import com.example.musiclibrary.model.musicBrainzData.MusicData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicDataApiService {

    @GET("artist/?query=nirvana&fmt=json")
    fun getNirvana(): Flowable<MusicData>

    @GET("artist/?fmt=json")
    fun searchArtist(@Query("query") query: String): Flowable<ArtistData>

    @GET("recording/?fmt=json")
    fun searchMusic(@Query("query") query: String): Flowable<MusicData>

    @GET("artist/{guid}/?fmt=json")
    fun getArtistByGuid(@Path("guid") guid: String): Flowable<ArtistData>

    @GET("{queryType}/?fmt=json")
    fun search(@Path("queryType") queryType: String, @Query("query") input: String): Flowable<MusicData>
}
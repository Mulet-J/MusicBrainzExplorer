package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.ArtistWithRecordings
import com.example.musiclibrary.model.musicBrainzData.ArtistsData
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicDataApiService {

    @GET("artist/?query=nirvana&fmt=json")
    fun getNirvana(): Flowable<RecordingsData>

    @GET("artist/?fmt=json&limit=20")
    fun searchArtist(@Query("query") query: String): Flowable<ArtistsData>

    @GET("recording/?fmt=json&limit=20")
    fun searchRecord(@Query("query") query: String): Flowable<RecordingsData>

    @GET("artist/{guid}/?fmt=json")
    fun getArtistByGuid(@Path("guid") guid: String): Flowable<Artist>

    @GET("artist/{artist_id}/?inc=recordings&fmt=json")
    fun getArtistRecordings(@Path("artist_id") artist_id: String):Flowable<ArtistWithRecordings>

}

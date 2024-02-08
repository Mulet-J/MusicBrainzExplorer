package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.ArtistWithRecordings
import com.example.musiclibrary.model.api.Release
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.model.api.Track
import com.example.musiclibrary.model.musicBrainzData.ArtistsData
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import com.example.musiclibrary.model.musicBrainzData.TracksData
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
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
    fun getArtistRecordings(@Path("artist_id") artistId: String):Flowable<ArtistWithRecordings>

     @GET("artist/{artist_id}/?inc=release-groups+releases&fmt=json")
    fun getArtistAndItsReleaseGroups(@Path("artist_id") artistId: String): Flowable<Artist>

    @GET("release-group/{release_group_id}/&?fmt=json&inc=releases")
    fun getAllReleases(@Path("release_group_id") releaseGroupId: String): Flowable<ReleaseGroup>

    @GET("release/{release_id}/&?fmt=json&inc=recordings")
    fun getAllRecordsByRelease(@Path("release_id") releaseId: String): Flowable<TracksData>

}

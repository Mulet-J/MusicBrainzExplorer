package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.ArtistWithRecordings
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.model.musicBrainzData.ArtistsData
import com.example.musiclibrary.model.musicBrainzData.RecordingsData
import com.example.musiclibrary.model.musicBrainzData.TracksData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicDataApiService {

    // Searches for artists based on a query string
    @GET("artist/?fmt=json&limit=20")
    fun searchArtist(@Query("query") query: String): Flowable<ArtistsData>

    // Searches for recordings based on a query string
    @GET("recording/?fmt=json&limit=20")
    fun searchRecord(@Query("query") query: String): Flowable<RecordingsData>

    // Gets an artist by its GUID
    @GET("artist/{guid}/?fmt=json")
    fun getArtistByGuid(@Path("guid") guid: String): Flowable<Artist>

    // Gets an artist's recordings by its ID
    @GET("artist/{artist_id}/?inc=recordings&fmt=json")
    fun getArtistRecordings(@Path("artist_id") artistId: String):Flowable<ArtistWithRecordings>

    // Gets an artist and its release groups by its ID
     @GET("artist/{artist_id}/?inc=release-groups+releases&fmt=json")
    fun getArtistAndItsReleaseGroups(@Path("artist_id") artistId: String): Flowable<Artist>

    // Gets all releases by a release group's ID
    @GET("release-group/{release_group_id}/&?fmt=json&inc=releases")
    fun getAllReleases(@Path("release_group_id") releaseGroupId: String): Flowable<ReleaseGroup>

    // Gets all tracks by a release's ID
    @GET("release/{release_id}/&?fmt=json&inc=recordings")
    fun getAllRecordsByRelease(@Path("release_id") releaseId: String): Flowable<TracksData>

}

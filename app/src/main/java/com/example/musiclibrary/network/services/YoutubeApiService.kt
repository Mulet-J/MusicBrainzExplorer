package com.example.musiclibrary.network.services

import com.example.musiclibrary.model.youtubeData.YoutubeSearchResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    // Searches for videos based on a query string
    @GET("search")
    fun search(
        @Query("part") part: String,
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String
    ): Flowable<YoutubeSearchResponse>
}
package com.example.musiclibrary.repositories

import com.example.musiclibrary.model.youtubeData.YoutubeSearchResponse
import com.example.musiclibrary.network.services.YoutubeApiService
import io.reactivex.rxjava3.core.Flowable

class YoutubeRepository(
    private val youtubeApi: YoutubeApiService
) {

    fun searchYoutubeVideos(query: String): Flowable<YoutubeSearchResponse> {
        return this.youtubeApi.search("snippet", query, "video", 10, "AIzaSyA7TMRbDsYVswMRRT2GUEtzqXYhmics_Z4")
    }
}
package com.example.musiclibrary.repositories

import com.example.musiclibrary.network.services.ListenBrainzApiService

class ListenBrainzRepository(

    private val listenBrainzApiService: ListenBrainzApiService
) {
    fun getListenBrainzData(): Any {
        return listenBrainzApiService.getRecordingMetadata()
    }
}
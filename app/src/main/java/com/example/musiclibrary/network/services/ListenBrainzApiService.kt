package com.example.musiclibrary.network.services

import retrofit2.http.GET

interface ListenBrainzApiService {
    @GET("metadata/lookup/?recording_name=Paper+Cuts&artist_name=Nirvana&metadata=true&inc=artist+tag+release")
    fun getRecordingMetadata(): Any
}
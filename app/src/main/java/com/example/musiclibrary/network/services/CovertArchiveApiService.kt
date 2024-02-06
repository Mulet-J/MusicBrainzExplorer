package com.example.musiclibrary.network.services

import io.reactivex.rxjava3.core.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface CovertArchiveApiService {
    @GET("release/{release_id}/front-250")
    fun getReleaseCover(@Path("release_id") releaseId: String): Flowable<ResponseBody>
}

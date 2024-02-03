package com.example.musiclibrary
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TestApi {
    private lateinit var retrofit: Retrofit

    private fun initRetrofitInstance(){
        this.retrofit = Retrofit
            .Builder()
            .baseUrl("https://musicbrainz.org/ws/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private inline fun <reified T> createFakeUserService(): T{
        return this.retrofit.create(T::class.java)
    }
}
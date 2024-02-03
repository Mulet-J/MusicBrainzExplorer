package com.example.musiclibrary.di.modules
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal val remoteModule = module {
    single(
        named(apiMusicBrainzClient)
    ) {
        createRetrofit(get(), apiUrl)
    }
    single { createOkHttpClient() }
}

private fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    val gsonConverter =
        GsonConverterFactory.create(
            GsonBuilder().create()
        )

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverter)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

const val apiUrl: String = "https://musicbrainz.org/ws/2/"
const val apiMusicBrainzClient: String = "apiMusicBrainzClient"
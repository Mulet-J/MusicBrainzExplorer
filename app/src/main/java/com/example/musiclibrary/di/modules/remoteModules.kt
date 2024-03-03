package com.example.musiclibrary.di.modules
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
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
        createRetrofit(get(named("apiMusicBrainzHttpClient")), musicBrainzApiUrl)
    }
    single(named("apiMusicBrainzHttpClient")) { createOkHttpClient() }

    single(
        named(apiListenBrainzClient)
    ) {
        createRetrofit(get(named("apiListenBrainzHttpClient")), listenBrainzApiUrl)
    }
    single(named("apiListenBrainzHttpClient")) { createOkHttpClientForListenBrainzApi() }

    single(
        named(apiYoutubeClient)
    ) {
        createRetrofit(get(named("apiYoutubeHttpClient")), googleApiUrl)
    }
    single(named("apiYoutubeHttpClient")) { createOkHttpClient() }
    /*
    single(
        named(apiCovertArchiveClient)
    ) { 
        createRetrofit(get(), covertArchiveApiUrl)
    }
     */
}

private fun createOkHttpClientForListenBrainzApi(): OkHttpClient{
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val userAgentInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Authorization", apiListenBrainzToken)
            .build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(userAgentInterceptor)
        .build()
}
private fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val userAgentInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("User-Agent", userAgent)
            .build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(userAgentInterceptor)
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

const val musicBrainzApiUrl: String = "https://musicbrainz.org/ws/2/"
const val covertArchiveApiUrl: String = "https://coverartarchive.org/"
const val listenBrainzApiUrl: String = "https://api.listenbrainz.org/1/"
const val googleApiUrl : String = "https://www.googleapis.com/youtube/v3/"
const val apiMusicBrainzClient: String = "apiMusicBrainzClient"
const val apiCovertArchiveClient: String = "apiCovertArchiveClient"
const val apiListenBrainzClient: String = "apiListenBrainzClient"
const val apiYoutubeClient : String = "apiYoutubeClient"
const val apiListenBrainzToken: String = "74a5831f-845e-458a-a21d-3bfad7b67e4d"
const val userAgent = "androidMusicBrainz/1.0"
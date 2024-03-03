package com.example.musiclibrary.di.modules

import com.example.musiclibrary.network.services.ListenBrainzApiService
import com.example.musiclibrary.network.services.MusicDataApiService
import com.example.musiclibrary.network.services.YoutubeApiService
import com.example.musiclibrary.repositories.ArtistRepository
import com.example.musiclibrary.repositories.RecordRepository
import com.example.musiclibrary.repositories.YoutubeRepository
import com.example.musiclibrary.viewmodel.MusicViewModel
import com.example.musiclibrary.viewmodel.RecordingsViewModel
import com.example.musiclibrary.viewmodel.ReleaseGroupsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val coreModules = module {
    //single { ArtistRepository(get()) }
    single { RecordRepository(get(named("apiMusicBrainz"))) }
    single { ArtistRepository(get(named("apiMusicBrainz"))) }
    //single { ListenBrainzRepository(get(named("apiListenBrainz"))) }
    single { YoutubeRepository(get(named("apiYoutube")))}
    //single { CovertArtRepository(get()) }

    single { MusicViewModel(get(),get()) }
    single { ReleaseGroupsViewModel(get()) }
    single { RecordingsViewModel(get(),get()) }

    single(named("apiMusicBrainz")) {
        createWebService<MusicDataApiService>(
            get(
                named(apiMusicBrainzClient)
            )
        )
    }
    single(named("apiListenBrainz")) {
        createWebService<ListenBrainzApiService>(
            get(
                named(apiListenBrainzClient)
            )
        )
    }

    single(named("apiYoutube")) {
        createWebService<YoutubeApiService>(
            get(
                named(apiYoutubeClient)
            )
        )
    }
    /*
    single {
        createWebService<CovertArchiveApiService>(
            get(
                named(apiCovertArchiveClient)
            )
        )
    }
    */
}
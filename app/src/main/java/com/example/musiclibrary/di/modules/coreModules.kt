package com.example.musiclibrary.di.modules

import com.example.musiclibrary.di.Constants
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

// Module that provides core dependencies for the application,
// such as the repositories, the view models and the services which are are singletons,
// so they will be created only once and will be reused
internal val coreModules = module {

    //The repositories
    single { RecordRepository(get(named("apiMusicBrainz"))) }
    single { ArtistRepository(get(named("apiMusicBrainz"))) }
    single { YoutubeRepository(get(named("apiYoutube")))}

    //The view models
    single { MusicViewModel(get(),get()) }
    single { ReleaseGroupsViewModel(get()) }
    single { RecordingsViewModel(get(),get()) }

    // The API MusicBrain service will provide us with the data we need to display the artists, albums, and songs
    single(named("apiMusicBrainz")) {
        createWebService<MusicDataApiService>(
            get(
                named(Constants.apiMusicBrainzClient)
            )
        )
    }
    // The Youtube API service will provide us with the data we need to play music videos
    single(named("apiYoutube")) {
        createWebService<YoutubeApiService>(
            get(
                named(Constants.apiYoutubeClient)
            )
        )
    }
}
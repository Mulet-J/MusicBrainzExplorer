package com.example.musiclibrary.di.modules

import com.example.musiclibrary.network.services.CovertArchiveApiService
import com.example.musiclibrary.network.services.MusicDataApiService
import com.example.musiclibrary.repositories.ArtistRepository
import com.example.musiclibrary.repositories.CovertArtRepository
import com.example.musiclibrary.repositories.MusicRepository
import com.example.musiclibrary.viewmodel.MusicViewModel
import com.example.musiclibrary.viewmodel.ReleaseGroupsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val coreModules = module {
    single { ArtistRepository(get()) }
    single { MusicRepository(get()) }
    single { CovertArtRepository(get()) }

    single { MusicViewModel(get(),get(), get()) }
    single { ReleaseGroupsViewModel(get()) }

    single {
        createWebService<MusicDataApiService>(
            get(
                named(apiMusicBrainzClient)
            )
        )
    }
    single {
        createWebService<CovertArchiveApiService>(
            get(
                named(apiCovertArchiveClient)
            )
        )
    }
}
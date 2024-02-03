package com.example.musiclibrary.di.modules

import com.example.musiclibrary.network.services.MusicDataApiService
import com.example.musiclibrary.repositories.MusicRepository
import com.example.musiclibrary.viewmodel.MusicViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val coreModules = module {
    single { MusicRepository(get()) }

    single {
        MusicViewModel(get())
    }

    single {
        createWebService<MusicDataApiService>(
            get(
                named(apiMusicBrainzClient)
            )
        )
    }
}
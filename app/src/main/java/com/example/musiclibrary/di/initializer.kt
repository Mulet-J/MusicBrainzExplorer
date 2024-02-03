package com.example.musiclibrary.di

import android.content.Context
import com.example.musiclibrary.di.modules.coreModules
import com.example.musiclibrary.di.modules.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException

fun injectModuleDependencies(context: Context) {
    try {
        startKoin {
            androidContext(context)
            modules(modules)
        }
    } catch (alreadyStart: KoinAppAlreadyStartedException) {
        loadKoinModules(modules)
    }
}

private val modules = mutableListOf(coreModules, remoteModule)
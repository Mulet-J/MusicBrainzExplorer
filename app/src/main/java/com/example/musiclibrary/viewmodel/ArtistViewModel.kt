package com.example.musiclibrary.viewmodel

import com.example.musiclibrary.repositories.MusicRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ArtistViewModel(
    private val musicRepository: MusicRepository
) {
    private val disposeBag = CompositeDisposable()

}
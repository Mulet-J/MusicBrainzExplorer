package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.musicBrainzData.MusicData
import com.example.musiclibrary.repositories.MusicRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MusicViewModel(
    private val musicRepository: MusicRepository
): ViewModel() {
    private val disposeBag = CompositeDisposable()
    private val musicData: BehaviorSubject<List<MusicData>> = BehaviorSubject.createDefault(listOf())

    init {
//        this.getMusic()
    }

//    fun getMusic(): Call<MusicData>{
//        return this.musicRepository.getSomeMusic()
//    }


    fun getSomeMusic(callback : ()->Unit): Disposable {
        return this.musicRepository.getNirvana().subscribe({
                music -> this.musicData.onNext(listOf(music))
            callback()
        },{
                error-> Log.d("abazerazerazer", "aezzfdqsd")
        }).addTo(disposeBag)
    }
}
package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.musicBrainzData.ArtistData
import com.example.musiclibrary.model.musicBrainzData.MusicData
import com.example.musiclibrary.repositories.ArtistRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MusicViewModel(
    private val artistRepository: ArtistRepository
): ViewModel() {
    private val disposeBag = CompositeDisposable()
    private val artistData: BehaviorSubject<List<ArtistData>> = BehaviorSubject.createDefault(listOf())
    private val musicData: BehaviorSubject<List<MusicData>> = BehaviorSubject.createDefault(listOf())
    val artistList: MutableLiveData<ArtistData> = MutableLiveData()
    val musicList: MutableLiveData<MusicData> = MutableLiveData()


//    init {
//        this.getMusic()
//    }

//    fun getMusic(): Call<ArtistData>{
//        return this.musicRepository.getSomeMusic()
//    }


    fun getSomeMusic(callback : ()->Unit): Disposable {
        return this.artistRepository.getArtistByGuid("f27ec8db-af05-4f36-916e-3d57f91ecf5e").subscribe({
                music -> this.artistData.onNext(listOf(music))
            this.artistList.postValue(music)
            callback()
        },{
                error-> Log.d("abazerazerazer", "aezzfdqsd")
        }).addTo(disposeBag)
    }

    fun search(input: String, queryType: String) : Disposable{
        when (queryType) {
            "Artiste" -> return this.artistRepository.searchArtist(input).subscribe({ artist ->
                this.artistData.onNext(listOf(artist))
                this.artistList.postValue(artist)
            }, { error ->
                Log.d("abazerazerazer", "aezzfdqsd")
            }).addTo(disposeBag)

           // default =  "Musique"
            else ->  return this.artistRepository.searchMusic(input).subscribe({ music ->
                this.musicData.onNext(listOf(music))
                this.musicList.postValue(music)
            }, { error ->
                Log.d("abazerazerazer", "aezzfdqsd")
            }).addTo(disposeBag)
        }
    }
}
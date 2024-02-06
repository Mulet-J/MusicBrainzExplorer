package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording
import com.example.musiclibrary.repositories.ArtistRepository
import com.example.musiclibrary.repositories.CovertArtRepository
import com.example.musiclibrary.repositories.MusicRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

class MusicViewModel(
    private val artistRepository: ArtistRepository,
    private val recordingRepository: MusicRepository,
    private val covertArtRepository: CovertArtRepository,
): ViewModel() {
    private val disposeBag = CompositeDisposable()
    val artistsList: MutableLiveData<List<Artist>> = MutableLiveData()
    val recordingsList: MutableLiveData<List<Recording>> = MutableLiveData()
    fun getSomeMusic(callback : ()->Unit): Disposable {
        return this.artistRepository.getArtistByGuid("f27ec8db-af05-4f36-916e-3d57f91ecf5e").subscribe({
                music -> this.artistsList.postValue(listOf(music))
            callback()
        },{
                _-> Log.d("abazerazerazer", "aezzfdqsd")
        }).addTo(disposeBag)
    }

    fun search(input: String, queryType: String) : Disposable{
        return when (queryType) {
            "Artiste" -> this.artistRepository.searchArtist(input).subscribe({ result ->
                this.artistsList.postValue(result.artists)
            }, { _ ->
                Log.d("abazerazerazer", "aezzfdqsd")
            }).addTo(disposeBag)

            // default =  "Musique"
            else -> this.recordingRepository.searchRecord(input).subscribe({ result ->
                this.recordingsList.postValue(result.recordings)
            }, { _ ->
                Log.d("abazerazerazer", "aezzfdqsd")
            }).addTo(disposeBag)
        }
    }

    fun getRecordingsByArtist(artist: Artist):Disposable{
        val artistId = artist.id!!
        return this.recordingRepository.searchRecordsByArtist(artistId).subscribe({
                result -> this.recordingsList.postValue(result.recordings)
        }, { _ ->
            Log.d("abazerazerazer", "aezzfdqsd")
        }).addTo(disposeBag)
    }

    fun getCovert(){
        //TODO
        this.covertArtRepository.getCovertFromMBID("7d166a44-cfb5-4b08-aacb-6863bbe677d6").subscribe({
            result ->
            val a = 1
        })
    }
}
package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.repositories.ArtistRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

class ReleaseGroupsViewModel(
    private val artistRepository: ArtistRepository,
): ViewModel() {
    private val disposeBag = CompositeDisposable()
    val artistsList: MutableLiveData<List<Artist>> = MutableLiveData()
    val releaseGroupList: MutableLiveData<List<ReleaseGroup>> = MutableLiveData()



    fun getReleaseGroupsByArtist(artist: Artist): Disposable {
        val artistId = artist.id!!
        return this.artistRepository.getArtistByGuid2(artistId).subscribe({
                result -> this.releaseGroupList.postValue(result.releaseGroups)
        }, { _ ->
            Log.d("abazerazerazer", "aezzfdqsd")
        }).addTo(disposeBag)
    }
}
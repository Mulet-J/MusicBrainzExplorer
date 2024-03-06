package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.repositories.ArtistRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

class ReleaseGroupsViewModel(

    // Repositories for artist
    private val artistRepository: ArtistRepository,
): ViewModel() {

    // Composite disposable to manage the disposables
    private val disposeBag = CompositeDisposable()

    // LiveData for the list of release groups
    val releaseGroupList: MutableLiveData<List<ReleaseGroup>> = MutableLiveData()



    // Function to get the release groups by artist
    fun getReleaseGroupsByArtist(artist: Artist): Disposable {
        val artistId = artist.id!!
        return this.artistRepository.getArtistAndItsReleaseGroupsByGuid(artistId).subscribe({
                artist -> this.releaseGroupList.postValue(artist.releaseGroups)
        }, { error ->
            Log.d("Error in function getReleaseGroupsByArtist", error.message ?: "error")
        }).addTo(disposeBag)
    }
}
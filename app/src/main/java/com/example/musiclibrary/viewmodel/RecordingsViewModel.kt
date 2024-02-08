package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.api.Release
import com.example.musiclibrary.model.api.Track
import com.example.musiclibrary.repositories.RecordRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

class RecordingsViewModel(
    private val musicRepository: RecordRepository

): ViewModel() {

    private val disposeBag = CompositeDisposable()
    //val releaseGroupList : MutableLiveData<List<Release>> = MutableLiveData()
    val releases: MutableLiveData<List<Release>> = MutableLiveData()
    val trackList: MutableLiveData<List<Track>> = MutableLiveData()


    fun getAllTrackByRelease(releaseId: String): Disposable{
        return this.musicRepository.getAllTracksByRelease(releaseId).subscribe({
            result -> this.trackList.postValue(result.media[0].tracks)
        },{ error->
            Log.d("Error in function getAllTrackByRelease ", error.message?:"error")
        }).addTo(disposeBag)
    }
    fun getAllreleasesByReleaseGroup(releaseGroupId: String): Disposable {
        //val releaseGroupId = releaseGroup.id!!
        return this.musicRepository.getAllReleasesByReleaseGroups(releaseGroupId).subscribe({
                result -> this.releases.postValue(result.releases)
        }, { error ->
            Log.d("Error in function getAllreleasesByReleaseGroup ", error.message?:"error")
        }).addTo(disposeBag)
    }



}
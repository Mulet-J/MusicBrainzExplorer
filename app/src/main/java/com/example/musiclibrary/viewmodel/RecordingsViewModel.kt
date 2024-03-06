package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.api.Release
import com.example.musiclibrary.model.api.Track
import com.example.musiclibrary.model.youtubeData.YoutubeSearchResponse
import com.example.musiclibrary.repositories.RecordRepository
import com.example.musiclibrary.repositories.YoutubeRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

class RecordingsViewModel(

    // Repositories for music data
    private val musicRepository: RecordRepository,
    private  val youtubeRepository: YoutubeRepository
): ViewModel() {

    // Composite disposable to manage the disposables
    private val disposeBag = CompositeDisposable()

    // LiveData for the list of releases
    val releases: MutableLiveData<List<Release>> = MutableLiveData()

    // LiveData for the list of tracks
    val trackList: MutableLiveData<List<Track>> = MutableLiveData()

    // LiveData for the YouTube search result
    val youtubeSearchResult: MutableLiveData<YoutubeSearchResponse> = MutableLiveData()


    // Function to get all the tracks by release group
    fun getAllTracksByRelease(releaseId: String): Disposable{
        return this.musicRepository.getAllTracksByRelease(releaseId).subscribe({
            result -> this.trackList.postValue(result.media[0].tracks)
        },{ error->
            Log.d("Error in function getAllTrackByRelease ", error.message?:"error")
        }).addTo(disposeBag)
    }

    // Function to get all the releases by release group
    fun getAllreleasesByReleaseGroup(releaseGroupId: String): Disposable {
        //val releaseGroupId = releaseGroup.id!!
        return this.musicRepository.getAllReleasesByReleaseGroups(releaseGroupId).subscribe({
                result -> this.releases.postValue(result.releases)
        }, { error ->
            Log.d("Error in function getAllreleasesByReleaseGroup ", error.message?:"error")
        }).addTo(disposeBag)
    }


    // Function to get the YouTube music clip for a query
    fun getYoutubeMusicClips(query: String): Disposable {
        return this.youtubeRepository.searchYoutubeVideos(query).subscribe({
            result -> this.youtubeSearchResult.postValue(result)
            //result.items.get(0).id.videoId
            Log.d("YoutubeMusicClips", result.items.get(0).id.videoId)
        }, { error ->
            Log.d("Error in function getYoutubeMusicClips ", error.message?:"error")
        }).addTo(disposeBag)
    }



}
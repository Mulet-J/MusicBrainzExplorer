package com.example.musiclibrary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.Recording
import com.example.musiclibrary.repositories.ArtistRepository
import com.example.musiclibrary.repositories.RecordRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo

class MusicViewModel(
    // Repositories for artist and recording data
    private val artistRepository: ArtistRepository,
    private val recordingRepository: RecordRepository,
): ViewModel() {
    // Composite disposable to manage the disposables
    private val disposeBag = CompositeDisposable()

    // LiveData for the list of artists
    val artistsList: MutableLiveData<List<Artist>> = MutableLiveData()

    // LiveData for the list of recordings
    val recordingsList: MutableLiveData<List<Recording>> = MutableLiveData()

    // List of artists and recordings
    var currentArtistList: MutableList<Artist> = mutableListOf()

    // List of recordings
    var currentRecordingList: MutableList<Recording> = mutableListOf()

    // LiveData for the filtered list of artists
    val filteredArtistList: LiveData<List<Artist>> get() = artistsList

    // LiveData for the filtered list of recordings
    val filteredRecordingsList: LiveData<List<Recording>> get() = recordingsList

    // Filter for the search query
    var filter: String = ARTIST_QUERY



    companion object {
        private const val ARTIST_QUERY = "Artiste"
        private const val RECORD_QUERY = "Musique"
    }


    // Function to search for music data based on the input and the query type
    fun search(input: String, queryType: String) : Disposable{
        return when (queryType) {

            // Search for artist data
            ARTIST_QUERY -> this.artistRepository.searchArtist(input).subscribe({ result ->
                // Set the list of artists
                this.artistsList.postValue(result.artists)

                // Set the current list of artists
                this.currentArtistList = result.artists.toMutableList()

                // Set the filter to artist query
                this.filter = ARTIST_QUERY
            }, { error ->
                Log.d("Error in function search", error.message ?: "error Artist Query")
            }).addTo(disposeBag)

            // Search for recording data
            RECORD_QUERY -> this.recordingRepository.searchRecord(input).subscribe({ result ->
                // Set the list of recordings
                this.recordingsList.postValue(result.recordings)

                // Set the current list of recordings
                this.currentRecordingList = result.recordings.toMutableList()

                // Set the filter to recording query
                this.filter = RECORD_QUERY
            }, { error ->
                Log.d("Error in function search", error.message ?: "error Record Query")
            }).addTo(disposeBag)

            //Cannot happened as QueryType is init
            else -> {
                disposeBag
            }
        }
    }

    // Function to get the release groups by artist
    fun getRecordingsByArtist(artist: Artist):Disposable{
        val artistId = artist.id!!
        return this.recordingRepository.searchRecordsByArtist(artistId).subscribe({
                result -> this.recordingsList.postValue(result.recordings)
        }, { error ->
            Log.d("Error in function getRecordingsByArtist", error.message ?: "error")
        }).addTo(disposeBag)
    }


    // Function to filter the music list based on query
    fun filterMusicList(query: String) {
        if (query != null) {
            when(filter) {
                ARTIST_QUERY -> {
                    val filteredList = this.currentArtistList.filter { artist ->
                        artist.name.contains(query, ignoreCase = true)
                    }
                    this.artistsList.value = filteredList
                }
                RECORD_QUERY -> {
                    val filteredList = this.currentRecordingList.filter { recording ->
                        recording.title.contains(query, ignoreCase = true)
                    }
                    this.recordingsList.value = filteredList
                }
            }
        }
    }
}
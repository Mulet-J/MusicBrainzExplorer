package com.example.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.TrackDto
import com.example.musiclibrary.model.api.Release
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnCellClicked
import com.example.musiclibrary.viewmodel.RecordingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordingDetailsActivity : ComponentActivity(), OnCellClicked {

    // View model for the music data
    private val recordingsViewModel: RecordingsViewModel by viewModel()

    // Views
    private lateinit var albumTitleTv: TextView
    private var releaseGroupId : String? = ""
    private var releaseId: String? = ""
    private lateinit var backBtn: ImageButton
    private var cellClick: Boolean = false
    private lateinit var recordingsListRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.recordings_details_layout)
        this.albumTitleTv = findViewById(R.id.album_name_tv)
        this.backBtn = findViewById(R.id.back_button)
        this.backBtn.setOnClickListener{
            finish()
        }

        // Get the release group or release data from the intent
        val intent = this.intent
        val type = intent.getStringExtra("type")
        when(type){
            "Track" -> {
                // Get the release group data from the intent
                val releaseGroup = intent.getSerializableExtra("release-group") as ReleaseGroup
                this.albumTitleTv.text = releaseGroup.title
                this.releaseGroupId = releaseGroup.id
                this.releaseGroupId?.let { getAllReleaseByReleaseGroup(it) }

                // Set up the listener for the YouTube search result to display the music video clip if the user clicks on a cell
                this.recordingsViewModel.youtubeSearchResult.observe(this@RecordingDetailsActivity) { value ->
                    val trackClip = value.items[0].id.videoId
                    val context = this // On utilise le contexte de la vue
                    val intent = Intent(context, YoutubePlayerActivity::class.java)
                    if (cellClick) {
                        // We send the video id to the YoutubePlayerActivity
                        intent.putExtra("VIDEO_ID", trackClip)
                        context.startActivity(intent)
                    }
                }

            }
            "Release"->{
                // Get the release data from the intent
                val release = intent.getSerializableExtra("releases") as List<Release>
                val releaseToDisplay = release[0]
                this.albumTitleTv.text = releaseToDisplay.title
                this.releaseId = releaseToDisplay.id
                this.releaseId?.let { getAllTracksByRelease(it) }

                // Set up the listener for the YouTube search result to display the music video clip if the user clicks on a cell
                this.recordingsViewModel.youtubeSearchResult.observe(this@RecordingDetailsActivity){
                        value ->  val trackClip = value.items[0].id.videoId
                    val context = this // We use the context of the view
                    val intent = Intent(context, YoutubePlayerActivity::class.java)
                    if (cellClick == true) {
                        // We send the video id to the YoutubePlayerActivity
                        intent.putExtra("VIDEO_ID", trackClip)
                        context.startActivity(intent)
                    }
                }
            }else->{
                Log.d("Error in function onCreate ", "error")
            }

        }
        // Set up the releases list
        this.recordingsViewModel.releases.observe(this@RecordingDetailsActivity){
            value -> val releaseToDisplay = value.get(0)
            releaseToDisplay.id?.let { getAllTracksByRelease(it) }
        }

        // Set up the track list
        this.recordingsViewModel.trackList.observe(this@RecordingDetailsActivity){
                value ->
            val trackList = value.map { TrackDto(it) }
            setUpTrackList(trackList)
        }

        this.recordingsListRv = findViewById(R.id.recordings_rv)
    }

    // Set up the track list
    private fun setUpTrackList(trackList: List<TrackDto>){
        val recordingListAdapter = MusicDataAdapter(trackList, this)
        recordingsListRv.layoutManager = LinearLayoutManager(this)
        recordingsListRv.adapter = recordingListAdapter
    }

    // Get all the releases for a release group
    fun getAllReleaseByReleaseGroup(releaseGroupId: String){
        this.recordingsViewModel.getAllreleasesByReleaseGroup(releaseGroupId)
    }

    // Get all the tracks for a release
    fun getAllTracksByRelease(releaseId: String){
        this.recordingsViewModel.getAllTracksByRelease(releaseId)
    }

    // Update the youtubeSearchResult live data with the search result to make the observer trigger
    // and display the music video clip
    override fun displayCellDetails(data: DataDto) {
        cellClick = true
        (data as TrackDto).trackData.title?.let { this.recordingsViewModel.getYoutubeMusicClips(it) }

    }

}



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

    private val recordingsViewModel: RecordingsViewModel by viewModel()
    private lateinit var albumTitleTv: TextView
    private var releaseGroupId : String? = ""
    private var releaseId: String? = ""
    private lateinit var backBtn: ImageButton
    private var cellClick: Boolean = false
    private lateinit var recordingsListRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recordings_details_layout)
        this.albumTitleTv = findViewById(R.id.album_name_tv)
        this.backBtn = findViewById(R.id.back_button)
        this.backBtn.setOnClickListener{
            finish()
        }


        val intent = this.intent
        val type = intent.getStringExtra("type")
        when(type){
            "Track" -> {
                val releaseGroup = intent.getSerializableExtra("release-group") as ReleaseGroup
                this.albumTitleTv.text = releaseGroup.title
                this.releaseGroupId = releaseGroup.id
                this.releaseGroupId?.let { getAllReleaseByReleaseGroup(it) }

                this.recordingsViewModel.youtubeSearchResult.observe(this@RecordingDetailsActivity) { value ->
                    val trackClip = value.items[0].id.videoId
                    val context = this // On utilise le contexte de la vue
                    val intent = Intent(context, YoutubePlayerActivity::class.java)
                    if (cellClick) {
                        intent.putExtra("VIDEO_ID", trackClip)
                        context.startActivity(intent)
                    }
                }

            }
            "Release"->{
                val release = intent.getSerializableExtra("releases") as List<Release>
                val releaseToDisplay = release[0]
                this.albumTitleTv.text = releaseToDisplay.title
                this.releaseId = releaseToDisplay.id
                this.releaseId?.let { getAllTracksByRelease(it) }
                this.recordingsViewModel.youtubeSearchResult.observe(this@RecordingDetailsActivity){
                        value ->  val trackClip = value.items[0].id.videoId
                    val context = this // On utilise le contexte de la vue
                    val intent = Intent(context, YoutubePlayerActivity::class.java)
                    if (cellClick == true) {
                        intent.putExtra("VIDEO_ID", trackClip)
                        context.startActivity(intent)
                    }
                }
            }else->{
                Log.d("Error in function onCreate ", "error")
            }

        }
        this.recordingsViewModel.releases.observe(this@RecordingDetailsActivity){
            value -> val releaseToDisplay = value.get(0)
            releaseToDisplay.id?.let { getAllTracksByRelease(it) }
        }

        this.recordingsViewModel.trackList.observe(this@RecordingDetailsActivity){
                value ->
            val recordingsList = value.map { TrackDto(it) }
            setUpRecordings(recordingsList)
        }

        this.recordingsListRv = findViewById(R.id.recordings_rv)
    }

    private fun setUpRecordings(recordingsList: List<TrackDto>){
        val recordingListAdapter = MusicDataAdapter(recordingsList, this)
        recordingsListRv.layoutManager = LinearLayoutManager(this)
        recordingsListRv.adapter = recordingListAdapter
    }

    fun getAllReleaseByReleaseGroup(releaseGroupId: String){
        this.recordingsViewModel.getAllreleasesByReleaseGroup(releaseGroupId)
    }

    fun getAllTracksByRelease(releaseId: String){
        this.recordingsViewModel.getAllTrackByRelease(releaseId)
    }

    override fun displayCellDetails(data: DataDto) {
        cellClick = true
        (data as TrackDto).trackData.title?.let { this.recordingsViewModel.getYoutubeMusicClips(it) }

    }

//    override fun onDestroy() {
//        super.onDestroy()
//        // Supprime tous les observateurs attachés à l'activité ou au fragment
//        this.recordingsViewModel.youtubeSearchResult.removeObservers(this)
//    }


}



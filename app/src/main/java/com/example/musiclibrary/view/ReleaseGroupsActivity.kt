package com.example.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.ReleaseGroupDto
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnCellClicked
import com.example.musiclibrary.viewmodel.ReleaseGroupsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseGroupsActivity : ComponentActivity(), OnCellClicked {

    // View model for the release groups
    private val releaseGroupsViewModel: ReleaseGroupsViewModel by viewModel()

    // Views
    private lateinit var artistNameTv: TextView
    private lateinit var releaseGroupsRv: RecyclerView
    private lateinit var backBtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.activity_release_groups)

        // Views initialization
        this.artistNameTv = findViewById(R.id.artist_name_tv)
        this.backBtn = findViewById(R.id.back_button)
        this.backBtn.setOnClickListener{
            finish()
        }

        // Get the artist data from the intent
        val intent = this.intent
        val artist = intent.getSerializableExtra("artist") as Artist
        this.artistNameTv.text = artist.name

        // Get the release groups by artist
        getReleaseGroupsByArtist(artist)

        // Set up the recycler view for the release groups
        this.releaseGroupsViewModel.releaseGroupList.observe(this@ReleaseGroupsActivity){
            value ->
            val releaseGroupDtoList = value.map { ReleaseGroupDto(it) }
            setUpReleaseGroupList(releaseGroupDtoList)
        }
        this.releaseGroupsRv = findViewById(R.id.release_groups_rv)
    }


    // Function to set up the recycler view for the release groups
    private fun setUpReleaseGroupList(releaseGroupList: List<ReleaseGroupDto>){
        val releaseGroupDataAdapter = MusicDataAdapter(releaseGroupList, this)
        releaseGroupsRv.layoutManager = LinearLayoutManager(this)
        releaseGroupsRv.adapter = releaseGroupDataAdapter
    }


    // Function to get the release groups by artist
    fun getReleaseGroupsByArtist(artist: Artist){
        this.releaseGroupsViewModel.getReleaseGroupsByArtist(artist)
    }

    // Function to launch the RecordingDetailsActivity when a cell is clicked to display the selected
    // release group's track list
    override fun displayCellDetails(data: DataDto) {

        val releaseGroup= data as ReleaseGroupDto
        val intentReleases = Intent(this, RecordingDetailsActivity::class.java)
        intentReleases.putExtra("type", "Track")
        intentReleases.putExtra("release-group", releaseGroup.releaseGroupData)
        startActivity(intentReleases)
    }
}



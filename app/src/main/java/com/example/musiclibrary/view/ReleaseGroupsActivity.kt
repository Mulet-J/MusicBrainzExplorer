package com.example.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
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

    private val releaseGroupsViewModel: ReleaseGroupsViewModel by viewModel()
    private lateinit var userNameTv: TextView
    private lateinit var releaseGroupsRv: RecyclerView
    private lateinit var backBtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_release_groups)
        this.userNameTv = findViewById(R.id.artist_name_tv)
        this.backBtn = findViewById(R.id.back_button)
        this.backBtn.setOnClickListener{
            finish()
        }

        val intent = this.intent
        val artist = intent.getSerializableExtra("artist") as Artist
        this.userNameTv.text = artist.name
        getReleaseGroupsByArtist(artist)
        this.releaseGroupsViewModel.releaseGroupList.observe(this@ReleaseGroupsActivity){
            value ->
            val releaseGroupDtoList = value.map { ReleaseGroupDto(it) }
            setUpReleaseGroupList(releaseGroupDtoList)
        }
        this.releaseGroupsRv = findViewById(R.id.release_groups_rv)
    }

    private fun setUpReleaseGroupList(releaseGroupList: List<ReleaseGroupDto>){
        val releaseGroupDataAdapter = MusicDataAdapter(releaseGroupList, this)
        releaseGroupsRv.layoutManager = LinearLayoutManager(this)
        releaseGroupsRv.adapter = releaseGroupDataAdapter
    }

    fun getReleaseGroupsByArtist(artist: Artist){
        this.releaseGroupsViewModel.getReleaseGroupsByArtist(artist)
    }

    override fun displayCellDetails(data: DataDto) {

        val releaseGroup= data as ReleaseGroupDto
        val intentReleases = Intent(this, RecordingDetailsActivity::class.java)
        intentReleases.putExtra("type", "ReleaseGroupDto")
        intentReleases.putExtra("release-group", releaseGroup.releaseGroupData)
        startActivity(intentReleases)
    }
}



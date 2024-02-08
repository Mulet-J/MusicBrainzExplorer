package com.example.musiclibrary.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.model.ArtistDto
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.ReleaseGroupDto
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.model.api.ReleaseGroup
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnConversationClicked
import com.example.musiclibrary.viewmodel.ReleaseGroupsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReleaseGroupsActivity : AppCompatActivity(), OnConversationClicked {

    private val releaseGroupsViewModel: ReleaseGroupsViewModel by viewModel()
    private lateinit var userNameTextView: TextView

    private lateinit var releaseGroupsRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_release_groups)
        this.userNameTextView = findViewById(R.id.artist_name)

        val intent = this.intent
        val valeur = intent.getSerializableExtra("artist") as Artist
        this.userNameTextView.text = valeur.name
        getReleaseGroupsByArtist(valeur)
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

    override fun displayConversation(data: DataDto) {
        TODO("Not yet implemented")
    }
}



package com.example.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.di.injectModuleDependencies
import com.example.musiclibrary.model.ArtistDto
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.RecordingDto
import com.example.musiclibrary.model.ReleaseGroupDto
import com.example.musiclibrary.ui.theme.MusicLibraryTheme
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnCellClicked
import com.example.musiclibrary.viewmodel.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), OnCellClicked{

    private val musicViewModel : MusicViewModel by viewModel()
    private lateinit var datasListRv: RecyclerView
    private var selectedValue: String = "Artist"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)

        injectModuleDependencies(this@MainActivity)

        val searchButton: Button = findViewById(R.id.query_search_btn)
        val textField : EditText = findViewById(R.id.query_input_et)
        val filter: Spinner = findViewById(R.id.query_input_type_sp)

        musicViewModel.getCovert()

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.query_value,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Appliquer l'adaptateur au Spinner
        filter.adapter = adapter

        // Écouteur d'événements pour le Spinner
        filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: android.view.View,
                position: Int,
                id: Long
            ) {
                // Récupérer la valeur sélectionnée
                selectedValue = parentView.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Géré lorsque rien n'est sélectionné
            }
        }
        searchButton.setOnClickListener{
            val textInput = textField.text.toString()
            val queryType = selectedValue
            searchByFilter(textInput, queryType)
        }

        this.musicViewModel.artistsList.observe(this@MainActivity){
            value ->
            val artistsDto = value.map { ArtistDto(it)}
            setUpUsersConversationsList(artistsDto)

        }

        this.musicViewModel.recordingsList.observe(this@MainActivity){
                value ->
            val recordingDto = value.map { RecordingDto(it)}
            setUpUsersConversationsList(recordingDto)

        }

        this.datasListRv = findViewById(R.id.data_list_rv)
        val searchEditText: EditText = findViewById(R.id.filter_search_et)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                musicViewModel.filterMusicList(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

    private fun setUpUsersConversationsList(conversations: List<DataDto>) {
        val musicDataAdapter = MusicDataAdapter(conversations, this)
        datasListRv.layoutManager = LinearLayoutManager( this)
        datasListRv.adapter = musicDataAdapter
        this.musicViewModel.filteredArtistList.observe(this) { filteredList ->
            val artistDto = filteredList.map { ArtistDto(it) }
            musicDataAdapter.submitList(artistDto)
        }
        this.musicViewModel.filteredRecordingsList.observe(this) { filteredList ->
            val recordDto = filteredList.map { RecordingDto(it) }
            musicDataAdapter.submitList(recordDto)
        }
    }

    private fun searchByFilter(input: String, queryType: String ){
        this.musicViewModel.search(input, queryType)
    }
    override fun displayCellDetails(data: DataDto) {
        when(data) {
            is ArtistDto ->{
                val intent = Intent(this, ReleaseGroupsActivity::class.java)
                intent.putExtra("artist", data.artistData)
                startActivity(intent)
            }
            is RecordingDto ->{

                val release = data.recordingData.releases
                val intentReleases = Intent(this, RecordingDetailsActivity::class.java)
                intentReleases.putExtra("type", "Release")
                intentReleases.putExtra("releases", release)
                startActivity(intentReleases)

            }
            is ReleaseGroupDto -> {
                //Not implemented in main filter

            }else->{

            }
        }
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Appli de fou!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicLibraryTheme {
        Greeting("Android")
    }
}
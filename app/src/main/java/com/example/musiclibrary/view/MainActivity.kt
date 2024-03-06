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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.di.injectModuleDependencies
import com.example.musiclibrary.model.ArtistDto
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.RecordingDto
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnCellClicked
import com.example.musiclibrary.viewmodel.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), OnCellClicked{

    // ViewModel instance for music-related operations
    private val musicViewModel : MusicViewModel by viewModel()

    // RecyclerView instance for displaying music data
    private lateinit var datasListRv: RecyclerView

    // Spinner instance for filtering the search, the default value is "Artist"
    private var selectedValue: String = "Artist"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.main_activity_layout)

        // Inject the dependencies
        injectModuleDependencies(this@MainActivity)

        // Views initialization
        val searchButton: Button = findViewById(R.id.query_search_btn)
        val textField : EditText = findViewById(R.id.query_input_et)
        val filter: Spinner = findViewById(R.id.query_input_type_sp)


        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.query_value,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        filter.adapter = adapter

        // Event listener for the spinner
        filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: android.view.View,
                position: Int,
                id: Long
            ) {
                // Retrieve the selected value
                selectedValue = parentView.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing
            }
        }
        // Event listener for the search button
        searchButton.setOnClickListener{
            val textInput = textField.text.toString()
            val queryType = selectedValue
            // Call the searchByFilter function with the input and the query type
            searchByFilter(textInput, queryType)
        }

        // Observe the artists list
        this.musicViewModel.artistsList.observe(this@MainActivity){
            value ->
            val artistsDto = value.map { ArtistDto(it)}
            setUpUsersArtistList(artistsDto)

        }

        // Observe the recordings list
        this.musicViewModel.recordingsList.observe(this@MainActivity){
                value ->
            val recordingDto = value.map { RecordingDto(it)}
            setUpUsersArtistList(recordingDto)

        }

        // Views initialization
        this.datasListRv = findViewById(R.id.data_list_rv)
        val searchEditText: EditText = findViewById(R.id.filter_search_et)

        // Filter the music list based on the search input
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                musicViewModel.filterMusicList(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }

    // Set up the list of artists or recordings depending on the query type
    private fun setUpUsersArtistList(conversations: List<DataDto>) {
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

    // Search for music data based on the input and the query type
    private fun searchByFilter(input: String, queryType: String ){
        this.musicViewModel.search(input, queryType)
    }

    // Launch the activity to display the details of the selected cell in the recycler view
    // if the cell is an artist, launch the ReleaseGroupsActivity
    // if the cell is a recording, launch the RecordingDetailsActivity
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
            else->{
                Log.d("Error in function displayCellDetails ", "error")
            }
        }
    }

}
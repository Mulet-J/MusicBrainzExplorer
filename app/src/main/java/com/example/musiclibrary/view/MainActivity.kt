package com.example.musiclibrary.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
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
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.ui.theme.MusicLibraryTheme
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnConversationClicked
import com.example.musiclibrary.viewmodel.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), OnConversationClicked{

    private val musicViewModel : MusicViewModel by viewModel()
    private lateinit var datasListRv: RecyclerView
    private var selectedValue: String = "Artist"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
//        setContent {
//            MusicLibraryTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
        injectModuleDependencies(this@MainActivity)

        var textView: TextView = findViewById(R.id.textViewTest)
        var searchButton: Button = findViewById(R.id.querySearchBtn)
        var textField : EditText = findViewById(R.id.queryInputET)
        var filter: Spinner = findViewById(R.id.queryInputTypeSp)

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

                // Faites quelque chose avec la valeur sélectionnée
                // Par exemple, affichez-la dans la console
                println("Selected Value: $selectedValue")
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Géré lorsque rien n'est sélectionné
            }
        }
        searchButton.setOnClickListener{
            val textInput = textField.text.toString()
            val queryType = selectedValue
            //this.musicViewModel.getSomeMusic {  }
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

        this.datasListRv = findViewById(R.id.datas_rv);
    }

    private fun setUpUsersConversationsList(conversations: List<DataDto>) {
        val musicDataAdapter = MusicDataAdapter(conversations, this)
        datasListRv.layoutManager = LinearLayoutManager( this)
        datasListRv.adapter = musicDataAdapter
    }

    private fun searchByFilter(input: String, queryType: String ){
        this.musicViewModel.search(input, queryType)
    }
    override fun displayConversation(data: DataDto) {
        when(data) {
            is ArtistDto ->{
                Log.d("artistClicked", "you have clicked!!!!")

                val intent = Intent(this, ArtistDetailsActivity::class.java)
                intent.putExtra("artist", data.artistData.name)
                startActivity(intent)
            }
            is RecordingDto ->{
                Log.d("recordingClicked", "you have clicked!!!!")
                //Intent(
                   // this,
                   // RecordingDetailsActivity::class.java
                //).also {
                    //this.musicViewModel.currentArtistId = data.artistData.id
                   // startActivity(it)
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
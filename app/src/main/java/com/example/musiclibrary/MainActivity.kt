package com.example.musiclibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musiclibrary.di.injectModuleDependencies
import com.example.musiclibrary.ui.theme.MusicLibraryTheme
import com.example.musiclibrary.viewmodel.MusicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val musicViewModel : MusicViewModel by viewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        injectModuleDependencies(this@MainActivity)
        this.musicViewModel.getSomeMusic {  }

    }
    /*
    private fun getNirvanaData(){
        val api = Retrofit
            .Builder()
            .baseUrl("https://musicbrainz.org/ws/2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicDataApiService::class.java)
        api.getNirvana("PostmanRuntime/7.36.1").enqueue(object : Callback<MusicData>{

            override fun onResponse(call: Call<MusicData>, response: Response<MusicData>) {
                if(response.isSuccessful){
                    Log.i("CHECK_RESPONSE","onResponse:${response.body()}")
                    val nirvanaInfos = response.body();
                }
            }

            override fun onFailure(call: Call<MusicData>, t: Throwable) {
                Log.i("CHECK_RESPONSE","onFailure: ${t.message}")

            }
        })
    }
    */
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
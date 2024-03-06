package com.example.musiclibrary.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.musiclibrary.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubePlayerActivity : AppCompatActivity() {
    // Views
    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var backBtn: ImageButton
    private var youTubePlayerListener: AbstractYouTubePlayerListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.activity_youtube_player)
        this.backBtn = findViewById(R.id.back_button)

        // Set the back button click listener
        this.backBtn.setOnClickListener{
            finish()
        }

        youtubePlayerView = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youtubePlayerView)

        // Get the video id from the intent
        val videoId = intent.getStringExtra("VIDEO_ID") ?: ""

        // Initialize the YouTube player
        initializeYouTubePlayer(videoId)
    }

    // Function to initialize the YouTube player
    private fun initializeYouTubePlayer(videoId: String) {
        // Set the listener for the YouTube player
        youTubePlayerListener = object : AbstractYouTubePlayerListener() {
            // Load the video when the player is ready
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        }
        // Add the listener to the YouTube player
        youtubePlayerView.addYouTubePlayerListener(youTubePlayerListener!!)
    }

    override fun onDestroy() {
        // Release the YouTube player
        youtubePlayerView.release()
        super.onDestroy()
        finish()
    }
}
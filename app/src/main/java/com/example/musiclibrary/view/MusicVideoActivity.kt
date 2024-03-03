package com.example.musiclibrary.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.musiclibrary.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubePlayerActivity : AppCompatActivity() {

    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var backBtn: ImageButton
    private var youTubePlayerListener: AbstractYouTubePlayerListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)
        this.backBtn = findViewById(R.id.back_button)
        this.backBtn.setOnClickListener{
            finish()
        }

        youtubePlayerView = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youtubePlayerView)

        val videoId = intent.getStringExtra("VIDEO_ID") ?: ""
        initializeYouTubePlayer(videoId)
    }

    private fun initializeYouTubePlayer(videoId: String) {
        youTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        }

        youtubePlayerView.addYouTubePlayerListener(youTubePlayerListener!!)
    }

    override fun onDestroy() {
        //removeCurrentYouTubePlayerListener()
        youtubePlayerView.release()
        super.onDestroy()
        finish()
    }
}
package com.example.musiclibrary.view

//import com.example.musiclibrary.model.conversation_model.MessageData
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.RecordingDto
import com.example.musiclibrary.model.api.Artist
import com.example.musiclibrary.view.adapters.MusicDataAdapter
import com.example.musiclibrary.view.adapters.OnCellClicked
import com.example.musiclibrary.viewmodel.MusicViewModel
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArtistDetailsActivity : ComponentActivity(), OnCellClicked {

    private val musicViewModel: MusicViewModel by viewModel()
    //private lateinit var messagesListRv: RecyclerView
    //private lateinit var messageEditText: EditText
    private lateinit var userNameTv: TextView
    private lateinit var recordingsListRv: RecyclerView
    private lateinit var backBtn: ImageButton
    //private lateinit var backButtonImageButton: ImageButton
    //private lateinit var userProfilePictureImageView: ImageView
    //private lateinit var conversationAdapter: MusicDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.artist_details_layout)

        // XML views linking to the Activity
        //this.messageEditText = findViewById(R.id.new_message_et)
        this.userNameTv = findViewById(R.id.user_complete_name_tv)
        this.backBtn = findViewById(R.id.back_button)
        this.backBtn.setOnClickListener{
            finish()
        }
        //this.backButtonImageButton = findViewById(R.id.back_button)
        //this.userProfilePictureImageView = findViewById(R.id.user_picture_iv)

        val intent = this.intent
        val artist = intent.getSerializableExtra("artist") as Artist
        this.userNameTv.text = artist.name
        getRecordingsByArtist(artist);
        // Views setup
        //this.setUConversationsList()
        //this.fillConversationViewWithUserData()

        // Views listeners
        //this.setUpViewsListeners()

        //this.messagesListRv.layoutManager?.smoothScrollToPosition(this.messagesListRv, null, 10)

        this.musicViewModel.recordingsList.observe(this@ArtistDetailsActivity){
                value ->
            val artistsDto = value.map { RecordingDto(it) }
            setUpRecordingList(artistsDto)
        }
        this.recordingsListRv = findViewById(R.id.recordings_rv)
    }

    private fun setUpRecordingList(recordingsList: List<RecordingDto>) {
        val musicDataAdapter = MusicDataAdapter(recordingsList, this)
        recordingsListRv.layoutManager = LinearLayoutManager( this)
        recordingsListRv.adapter = musicDataAdapter
    }

    private fun getRecordingsByArtist(artist: Artist):Disposable{
        return this.musicViewModel.getRecordingsByArtist(artist)
    }
    override fun displayCellDetails(data: DataDto) {
        TODO("Not yet implemented")
    }

    /*
    private fun setUpViewsListeners() {
        this.setUpEditTextMessageListener()
        this.setUpBackButtonClickListener()
    }

    private fun setUpBackButtonClickListener() {
        this.backButtonImageButton.setOnClickListener {
            finish()
        }
    }

    private fun setUpEditTextMessageListener(){
        this.messageEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                v.text?.let {
                    //this.sendMessage(it.toString())
                }
                true
            } else false
        }
    }

    private fun setUConversationsList() {
        messagesListRv = findViewById(R.id.messages_list)

        //this.conversationAdapter = MessagesListAdapter(this.usersViewModel)
        messagesListRv.layoutManager = LinearLayoutManager(this)
        messagesListRv.adapter = conversationAdapter
    }


    private fun sendMessage(text: String) {
        val currentUser = this.usersViewModel.completeUsersList.value?.firstOrNull {
            this.usersViewModel.currentUserId == it.infos.id
        }
        currentUser?.conversations!!.add(MessageData(true, text))
        this.conversationAdapter.updateMessagesToDisplay()
        this.conversationAdapter.notifyItemInserted(currentUser.conversations.count())
        this.messagesListRv.smoothScrollToPosition(currentUser.conversations.count() - 1)
        messageEditText.setText("")
    }

    private fun fillConversationViewWithUserData() {
        val currentUserData = this.usersViewModel.completeUsersList.value!!.first {
            it.infos.id == this.usersViewModel.currentUserId
        }
        this.userNameTextView.text = currentUserData.getFormattedFullUserName()
        Glide
            .with(this)
            .load("https://robohash.org/${currentUserData.infos.profilePicture}")
            .into(this.userProfilePictureImageView)
    }
    */


}
package com.example.musiclibrary.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musiclibrary.R
import com.example.musiclibrary.model.ArtistDto
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.RecordingDto
import com.example.musiclibrary.model.musicBrainzData.ArtistData
class MusicDataAdapter (
    private val artistsDataList: List<DataDto>,
//    private val recordingDataList: List<RecordingDto>,
    private val onClickHandler: OnConversationClicked
    ): RecyclerView.Adapter<MusicDataAdapter.MusicDataCellViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDataCellViewHolder {
            val musicDataView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return MusicDataCellViewHolder(musicDataView)
        }
        override fun getItemCount(): Int {
            return artistsDataList.size
        }

    override fun getItemViewType(position: Int): Int {
        return when(artistsDataList[position]) {
            is ArtistDto -> 1
            else -> 2
        }
    }
        override fun onBindViewHolder(holder: MusicDataCellViewHolder, position: Int) {
            val artist = this.artistsDataList[position]
            when(holder.itemViewType) {
                1 -> "coucou"
                else ->  "kikoo"
            }
            holder.userNameTv.text = artist.artistData.name
//            holder.lastMessageTv.text = this.formatLastMessage(user.conversations.last())

            holder.itemView.setOnClickListener {
                onClickHandler.displayConversation(artist.artistData)
            }
//            Glide
//                .with(holder.itemView)
//                .load("https://robohash.org/${user.infos.profilePicture}")
//                .into(holder.userProfilePictureIv)
        }

//        private fun formatLastMessage(data: MessageData): String {
//            return if(data.isMyMessage) "Moi: ${data.message}" else data.message
//        }

        inner class MusicDataCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var userProfilePictureIv: ImageView
            var userNameTv: TextView
            var lastMessageTv: TextView


            init {
                userProfilePictureIv = itemView.findViewById(R.id.user_picture_iv)
                userNameTv = itemView.findViewById(R.id.user_name_tv)
                lastMessageTv = itemView.findViewById(R.id.last_message_tv)
            }
        }
    }

    interface OnConversationClicked {
        fun displayConversation(artistData: ArtistData)
    }

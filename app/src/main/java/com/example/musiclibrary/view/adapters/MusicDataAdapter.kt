package com.example.musiclibrary.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.R
import com.example.musiclibrary.model.ArtistDto
import com.example.musiclibrary.model.DataDto
import com.example.musiclibrary.model.RecordingDto
import com.example.musiclibrary.model.ReleaseGroupDto

class MusicDataAdapter (
    private val dataList: List<DataDto>,
//    private val recordingDataList: List<RecordingDto>,
    private val onClickHandler: OnCellClicked
    ): RecyclerView.Adapter<MusicDataAdapter.MusicDataCellViewHolder>() {

    companion object {
        private const val ARTIST_TYPE = 1
        private const val RECORD_TYPE = 2
        private const val RELEASE_GROUP_TYPE = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDataCellViewHolder {
        val musicDataView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MusicDataCellViewHolder(musicDataView)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun getItemViewType(position: Int): Int {
      return when(dataList[position]) {
        is ArtistDto -> ARTIST_TYPE
        is RecordingDto -> RECORD_TYPE
        is ReleaseGroupDto -> RELEASE_GROUP_TYPE
        }
    }
    override fun onBindViewHolder(holder: MusicDataCellViewHolder, position: Int) {
        val item = this.dataList[position]
        when(holder.itemViewType) {
            ARTIST_TYPE -> {
                holder.userNameTv.text = (item as ArtistDto).artistData.name
                holder.itemView.setOnClickListener {
                    onClickHandler.displayCellDetails(item)
                }
            }
            RECORD_TYPE ->  {
                holder.userNameTv.text = (item as RecordingDto).recordingData.title
                holder.itemView.setOnClickListener {
                    onClickHandler.displayCellDetails(item)
                }
            }
            RELEASE_GROUP_TYPE -> {
                holder.userNameTv.text = (item as ReleaseGroupDto).releaseGroupData.title
                holder.lastMessageTv.text = (item as ReleaseGroupDto).releaseGroupData.firstReleaseDate!!.substring(0,4)
            }
        }
    }

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

    interface OnCellClicked {
        fun displayCellDetails(data: DataDto)
    }

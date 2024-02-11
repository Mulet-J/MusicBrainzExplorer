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
import com.example.musiclibrary.model.TrackDto

class MusicDataAdapter (
    private var dataList: List<DataDto>,
//    private val recordingDataList: List<RecordingDto>,
    private val onClickHandler: OnCellClicked
    ): RecyclerView.Adapter<MusicDataAdapter.MusicDataCellViewHolder>() {

    companion object {
        private const val ARTIST_TYPE = 1
        private const val RECORD_TYPE = 2
        private const val RELEASE_GROUP_TYPE = 3
        private const val TRACK_TYPE = 4
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
          is TrackDto -> TRACK_TYPE
        }
    }

        override fun onBindViewHolder(holder: MusicDataCellViewHolder, position: Int) {
            val item = this.dataList[position]
            when(holder.itemViewType) {
             ARTIST_TYPE-> {
                    holder.titleTv.text = (item as ArtistDto).artistData.name
//            holder.lastMessageTv.text = this.formatLastMessage(user.conversations.last())

                    holder.itemView.setOnClickListener {
                        onClickHandler.displayCellDetails(item)
                    }
                }
                RECORD_TYPE ->  {
                    val record = (item as RecordingDto).recordingData

                    holder.titleTv.text = record.title
                    holder.additionalInfoTv.text = record.releases?.get(0)?.title

                    holder.itemView.setOnClickListener {
                        onClickHandler.displayCellDetails(item)
                    }
                }
                RELEASE_GROUP_TYPE->{
                    val releaseGroup = (item as ReleaseGroupDto).releaseGroupData
                    holder.titleTv.text =  releaseGroup.title
                    holder.additionalInfoTv.text = releaseGroup.firstReleaseDate!!.substring(0,4)
                    holder.itemView.setOnClickListener {
                        onClickHandler.displayCellDetails(item)
                    }
                }
                TRACK_TYPE->{
                    val track = (item as TrackDto).trackData
                    val textToDisplay = track.position.toString()+" - "+track.title
                    val titleDurationMinutes = track.length?.div(1000)?.div(60)
                    val titleDurationSeconds = track.length?.div(1000)?.mod(60)
                    val titleDuration = titleDurationMinutes.toString()+":"+ String.format("%02d",titleDurationSeconds)
                    holder.titleTv.text = textToDisplay
                    holder.additionalInfoTv.text = titleDuration
                }
            }
        }

    fun submitList(newList: List<DataDto>) {
        this.dataList = newList
        notifyDataSetChanged()
    }
    inner class MusicDataCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userProfilePictureIv: ImageView
        var titleTv: TextView
        var additionalInfoTv: TextView
        init {
            userProfilePictureIv = itemView.findViewById(R.id.item_picture_iv)
            titleTv = itemView.findViewById(R.id.title_tv)
            additionalInfoTv = itemView.findViewById(R.id.additional_info_tv)
        }
    }
    }

    interface OnCellClicked {
        fun displayCellDetails(data: DataDto)
    }

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

// This Adapter class is used to display the right datas in the recycler views used in the various
// activities of the app
class MusicDataAdapter (
    private var dataList: List<DataDto>, // List of data to display in the recycler view
    private val onClickHandler: OnCellClicked // Click handler for cell interactions
    ): RecyclerView.Adapter<MusicDataAdapter.MusicDataCellViewHolder>() {

    companion object {
        // Constants representing different types of data items
        private const val ARTIST_TYPE = 1
        private const val RECORD_TYPE = 2
        private const val RELEASE_GROUP_TYPE = 3
        private const val TRACK_TYPE = 4
    }

    // Function to create ViewHolder instances
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicDataCellViewHolder {
        val musicDataView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MusicDataCellViewHolder(musicDataView)
    }

    // Function to determine the number of items in the list
    override fun getItemCount(): Int {
        return dataList.size
    }

    // This function is used to determine the type of data to display in the recycler view
    override fun getItemViewType(position: Int): Int {
      return when(dataList[position]) {
        is ArtistDto -> ARTIST_TYPE
        is RecordingDto -> RECORD_TYPE
        is ReleaseGroupDto -> RELEASE_GROUP_TYPE
          is TrackDto -> TRACK_TYPE
        }
    }

    // Function to bind data to the ViewHolder
        override fun onBindViewHolder(holder: MusicDataCellViewHolder, position: Int) {
            val item = this.dataList[position]
            when(holder.itemViewType) {
             ARTIST_TYPE-> {
                    // Display the artist's name and type
                    holder.titleTv.text = (item as ArtistDto).artistData.name
                    holder.additionalInfoTv.text = item.artistData.type
                    holder.itemView.setOnClickListener {
                        onClickHandler.displayCellDetails(item)
                    }
                }
                RECORD_TYPE ->  {
                    // Display the recording's title and release title
                    val record = (item as RecordingDto).recordingData

                    holder.titleTv.text = record.title
                    holder.additionalInfoTv.text = record.releases?.get(0)?.title

                    holder.itemView.setOnClickListener {
                        onClickHandler.displayCellDetails(item)
                    }
                }
                RELEASE_GROUP_TYPE->{
                    // Display the release group's title and first release date
                    val releaseGroup = (item as ReleaseGroupDto).releaseGroupData
                    holder.titleTv.text =  releaseGroup.title
                    holder.additionalInfoTv.text = releaseGroup.firstReleaseDate!!.substring(0,4)
                    holder.itemView.setOnClickListener {
                        onClickHandler.displayCellDetails(item)
                    }
                }
                TRACK_TYPE->{
                    // Display the track's position, title and duration in the format mm:ss
                    val track = (item as TrackDto).trackData
                    val textToDisplay = track.position.toString()+" - "+track.title
                    val titleDurationMinutes = track.length?.div(1000)?.div(60)
                    val titleDurationSeconds = track.length?.div(1000)?.mod(60)
                    val titleDuration = titleDurationMinutes.toString()+":"+ String.format("%02d",titleDurationSeconds)

                    holder.titleTv.text = textToDisplay
                    holder.additionalInfoTv.text = titleDuration

                    // Set the click listener for the play button to display the music video
                    holder.playButtonIv.setOnClickListener {
                        if(holder.itemViewType == TRACK_TYPE ){
                            onClickHandler.displayCellDetails(item)
                        }
                    }
                }
            }
        }

    // Function to submit a new list of data to display in the recycler view
    fun submitList(newList: List<DataDto>) {
        this.dataList = newList
        notifyDataSetChanged()
    }

    // Inner class to represent the ViewHolder for the recycler view
    inner class MusicDataCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Views in the ViewHolder
        var playButtonIv: ImageView
        var titleTv: TextView
        var additionalInfoTv: TextView
        init {
            // Initialize the views
            playButtonIv = itemView.findViewById(R.id.item_picture_iv)
            titleTv = itemView.findViewById(R.id.title_tv)
            additionalInfoTv = itemView.findViewById(R.id.additional_info_tv)
        }
    }
    }

    // Interface to handle click events on the recycler view cells
    interface OnCellClicked {
        fun displayCellDetails(data: DataDto)
    }

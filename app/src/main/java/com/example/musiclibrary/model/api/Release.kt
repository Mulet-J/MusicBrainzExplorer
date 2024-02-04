package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class Release(
    @SerializedName("packaging-id") val packagingId: String?,
    val disambiguation: String?,
    @SerializedName("cover-art-archive") val coverArtArchive: CoverArtArchive?,
    @SerializedName("release-events") val releaseEvents: ArrayList<ReleaseEvent>,
    @SerializedName("artist-credit") val artistCredit: ArrayList<ArtistCredit>,
    @SerializedName("text-representation" ) val textRepresentation : TextRepresentation?,
    val barcode: String?,
    val quality: String?,
    val date: String?,
    val asin: String?,
    val media: ArrayList<Media>,
    val packaging: String?,
    val status: String?,
    val country: String?,
    val id: String?,
    @SerializedName("status-id") val statusId: String?,
    val title: String?,
    @SerializedName("label-info") val labelInfo: ArrayList<LabelInfo>,
)
package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class ReleaseGroup(
    @SerializedName("secondary-types") val secondaryTypes: ArrayList<String>,
    @SerializedName("first-release-date") val firstReleaseDate: String?,
    @SerializedName("secondary-type-ids") val secondaryTypeIds: ArrayList<String>,
    val releases: ArrayList<Release>,
    @SerializedName("primary-type-id") val primaryTypeId: String?,
    @SerializedName("primary-type") val primaryType: String?,
    val title: String?,
    val id: String?,
    @SerializedName("artist-credit") val artistCredit: ArrayList<ArtistCredit>,
    val disambiguation: String?,
)
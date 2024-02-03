package com.example.musiclibrary.model.musicBrainzData

import com.google.gson.annotations.SerializedName

data class Alias(
    @SerializedName("sort-name")
    val sortName: String,
    val name: String,
    val locale: String?,
    val type: String?,
    val primary: Boolean?,
    @SerializedName("begin-date")
    val beginDate: String?,
    @SerializedName("end-date")
    val endDate: String?,
    @SerializedName("type-id")
    val typeId: String?,
)
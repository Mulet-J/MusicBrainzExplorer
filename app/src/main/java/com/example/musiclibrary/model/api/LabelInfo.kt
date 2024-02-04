package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class LabelInfo(
    @SerializedName("catalog-number") val catalogNumber: String?,
    val label: Label?,
)
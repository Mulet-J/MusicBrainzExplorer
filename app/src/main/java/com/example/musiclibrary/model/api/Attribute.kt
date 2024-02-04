package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class Attribute(
    val type: String?,
    @SerializedName("type-id" ) val typeId: String?,
    val value: String?,
)
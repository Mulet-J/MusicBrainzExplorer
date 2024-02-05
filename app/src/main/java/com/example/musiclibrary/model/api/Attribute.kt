package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attribute(
    val type: String?,
    @SerializedName("type-id" ) val typeId: String?,
    val value: String?,
) : Serializable
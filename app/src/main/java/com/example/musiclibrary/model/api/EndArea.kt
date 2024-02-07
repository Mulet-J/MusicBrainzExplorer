package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EndArea(
    val id: String?,
    val type: String?,
    @SerializedName("type-id")
    val typeId: String?,
    val name: String?,
    @SerializedName("sort-name")
    val sortName: String?,
    @SerializedName("life-span")
    val lifeSpan: LifeSpan?,
) : Serializable

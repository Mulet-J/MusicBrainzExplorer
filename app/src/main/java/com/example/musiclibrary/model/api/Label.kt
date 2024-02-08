package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Label(
    @SerializedName("type-id") val typeId: String?,
    val country: String?,
    val ipis: ArrayList<String>,
    val aliases: ArrayList<Alias>,
    @SerializedName("sort-name") val sortName: String?,
    val area: Area?,
    val type: String?,
    val isnis: ArrayList<String>,
    @SerializedName("label-code") val labelCode: Int?,
    val id: String?,
    @SerializedName("life-span") val lifeSpan: LifeSpan?,
    val name: String?,
    val disambiguation: String?,
) : Serializable
package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Work(
    val relations: ArrayList<Relation>,
    val language: String?,
    val disambiguation: String?,
    val languages: ArrayList<String>,
    val iswcs: ArrayList<String>,
    val type: String?,
    val title: String?,
    val id: String?,
    @SerializedName("type-id") val typeId: String?,
    val attributes: ArrayList<Attribute>,
) : Serializable
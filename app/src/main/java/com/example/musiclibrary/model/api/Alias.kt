package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Alias(
    @SerializedName("sort-name") val sortName: String?,
    val name: String?,
    val locale: String?,
    val type: String?,
    val primary: Boolean?,
    @SerializedName("begin-date") val beginDate: String?,
    @SerializedName("end-date") val endDate: String?,
    @SerializedName("type-id") val typeId: String?,
    val ended: Boolean?,
    val begin: String?,
    val end: String?,
) : Serializable
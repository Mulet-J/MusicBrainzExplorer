package com.example.musiclibrary.model.api

import com.google.gson.annotations.SerializedName

data class Relation(
    val artist: Artist?,
    //@SerializedName("attribute-ids") val attributeIds: AttributeIds?,
    val direction: String?,
    @SerializedName("target-type") val targetType: String?,
    //@SerializedName("attribute-values") val attributeValues: AttributeValues?,
    @SerializedName("target-credit") val targetCredit: String?,
    val type: String?,
    val attributes: ArrayList<String>,
    @SerializedName("source-credit") val sourceCredit: String?,
    val begin: String?,
    val ended: Boolean?,
    @SerializedName("type-id") val typeId: String?,
    val end: String?,
)
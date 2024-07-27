package com.cme.clef.data.remote.model


import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("author")
    val author: Author,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated")
    val updated: String
)
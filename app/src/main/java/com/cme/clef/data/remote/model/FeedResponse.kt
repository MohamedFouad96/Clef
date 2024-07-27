package com.cme.clef.data.remote.model


import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("feed")
    val feed: Feed
)
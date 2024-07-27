package com.cme.clef.data.remote.model


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("self")
    val self: String
)
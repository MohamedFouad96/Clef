package com.cme.clef.data.remote.api

import com.cme.clef.data.remote.util.ApiResponse
import com.cme.clef.data.remote.model.FeedResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ClefApi {

    @GET(GET_ALBUMS)
    fun getAlbums(): Flow<ApiResponse<FeedResponse>>


    companion object {
        const val GET_ALBUMS = "/api/v2/us/music/most-played/100/albums.json"
    }

}
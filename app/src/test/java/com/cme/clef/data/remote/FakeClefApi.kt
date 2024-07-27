package com.cme.clef.data.remote


import com.cme.clef.data.util.NetworkState
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.data.remote.model.Author
import com.cme.clef.data.remote.model.Feed
import com.cme.clef.data.remote.model.FeedResponse
import com.cme.clef.data.remote.model.Result
import com.cme.clef.data.remote.util.ApiResponse
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response

class FakeClefApi(var state: NetworkState = NetworkState.Normal): ClefApi {


    private val albumsResponse = generateFakeAlbumsList()



    override fun getAlbums() = flow<ApiResponse<FeedResponse>> {
        when(state) {
            NetworkState.Normal -> {


                emit(ApiResponse.Companion.create(Response.success(albumsResponse)))
            }
            NetworkState.BackendError -> {
                val mediaType = "application/json".toMediaTypeOrNull()
                val response = ResponseBody.create(mediaType, error_response)
                emit(ApiResponse.Companion.create(Response.error(400,response)))
            }

            NetworkState.EmptyData -> {

                val albums = FeedResponse(Feed(Author("",""),"copyright", "country","icon", "id", listOf(),
                    listOf(),"title", "") )


                emit(ApiResponse.Companion.create(Response.success(albums)))

            }
        }
    }


    companion object {
        const val error_response = "error_happened"
        const val backend_error_response = "backend_error_happened"

    }

}


fun generateFakeAlbumsList(): FeedResponse {

    val fakeAlbumsList = mutableListOf<Result>()
    for (i in 1..100) {
        val fakeAlbum = Result(
            "artistId$i",
            "artistName$i",
            "artistUrl$i",
            "artwork$i",
            "content$i",
            listOf(),
            "id$i",
            "kind$i",
            "name$i",
            "2024-08-04",
            "url$i",
        )
        fakeAlbumsList.add(fakeAlbum)
    }
    return FeedResponse(Feed(Author("",""),"copyright", "country","icon", "id", listOf(),fakeAlbumsList,"title", "") )
}
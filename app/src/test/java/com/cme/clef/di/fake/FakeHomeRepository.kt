package com.cme.clef.di.fake

import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.Resource
import com.cme.clef.repo.IHomeRepository
import kotlinx.coroutines.flow.flow

class FakeHomeRepository: IHomeRepository {


    private var isWithError = false

    fun setIsWithError(isWithError: Boolean){
        this.isWithError = isWithError
    }


    override fun getMusicAlbums() = flow<Resource<List<AlbumInfo>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
            AlbumInfo("1","name", "artist", "releaseDate", "iamge", "link",false, listOf()),
            AlbumInfo("2","name", "artist", "releaseDate", "iamge", "link",false, listOf()),
            AlbumInfo("3","name", "artist", "releaseDate", "iamge", "link",false, listOf()),
            )

        emit(Resource.success(response))
    }

    override fun setFavoriteAlbum(albumId: String, isFavorite: Boolean) {}

}
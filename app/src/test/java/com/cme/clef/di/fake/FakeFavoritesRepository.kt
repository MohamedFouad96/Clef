package com.cme.clef.di.fake

import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.Resource
import com.cme.clef.repo.IFavoritesRepository
import kotlinx.coroutines.flow.flow

class FakeFavoritesRepository: IFavoritesRepository {


    private var isWithError = false

    fun setIsWithError(isWithError: Boolean){
        this.isWithError = isWithError
    }


    override fun getFavoriteMusicAlbums()=  flow<Resource<List<AlbumInfo>>> {
        emit(Resource.loading())

        if (isWithError){
            emit(Resource.error(msg = "Something Wrong"))
        }

        val response = listOf(
            AlbumInfo("1","name", "artist", "releaseDate", "iamge", "link",true, listOf()),
            AlbumInfo("2","name", "artist", "releaseDate", "iamge", "link",true, listOf()),
            AlbumInfo("3","name", "artist", "releaseDate", "iamge", "link",true, listOf()),
        )

        emit(Resource.success(response))
    }

}
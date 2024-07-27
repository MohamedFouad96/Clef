package com.cme.clef.repo



import android.net.Network
import com.cme.clef.data.cache.dao.AlbumsDao
import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.cache.entity.MusicAlbumEntity
import com.cme.clef.data.cache.entity.mapToDomain
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.remote.api.ClefApi
import com.cme.clef.data.remote.model.FeedResponse
import com.cme.clef.data.remote.model.mapToEntity
import com.cme.clef.data.remote.util.ApiSuccessResponse
import com.cme.clef.data.util.NetworkCacheResource
import com.cme.clef.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


interface IFavoritesRepository {
    fun getFavoriteMusicAlbums() : Flow<Resource<List<AlbumInfo>>>
}

 class FavoritesRepository(
    private val albumsDao: IAlbumsDao,
): IFavoritesRepository {
     override fun getFavoriteMusicAlbums()= flow<Resource<List<AlbumInfo>>> {
         emit(Resource.success(albumsDao.getFavoriteAlbums().mapToDomain()))
     }
 }
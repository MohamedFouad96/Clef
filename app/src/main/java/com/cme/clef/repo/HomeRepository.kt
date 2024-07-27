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
import kotlinx.coroutines.flow.map


interface IHomeRepository {

    fun getMusicAlbums() : Flow<Resource<List<AlbumInfo>>>
    fun setFavoriteAlbum(albumId: String, isFavorite: Boolean)
}

class HomeRepository(
    private val webService: ClefApi,
    private val albumsDao: IAlbumsDao,
): IHomeRepository {

    private val TAG = "HomeRepository"

    override fun getMusicAlbums()  = object : NetworkCacheResource<List<MusicAlbumEntity>, FeedResponse>() {
        override suspend fun fetchFromLocal() = albumsDao.getMusicAlbums()
        override suspend fun fetchFromRemote() = webService.getAlbums()

        override suspend fun saveRemoteData(remote: FeedResponse) {
            albumsDao.insertMusicAlbums(remote.feed.results.mapToEntity())
        }

        override suspend fun shouldFetchFromRemote(local: List<MusicAlbumEntity>?) = true

        override suspend fun processRemoteResponse(response: ApiSuccessResponse<FeedResponse>): Boolean {
            val localCache = albumsDao.getMusicAlbums()
            val remoteResponse = response.body?.feed?.results


            val updateData = localCache.isNotEmpty() && !remoteResponse.isNullOrEmpty() &&  localCache.first().id != remoteResponse.first().id

            if (updateData) {
                albumsDao.clearAllAlbums()
            }

            return  updateData || localCache.isEmpty()
        }


    }.getResult().map { it.run { Resource(status, data?.mapToDomain(), message, throwable) } }


    override fun setFavoriteAlbum(albumId: String, isFavorite: Boolean) {
        albumsDao.setFavoriteAlbum(albumId, isFavorite)
    }

}
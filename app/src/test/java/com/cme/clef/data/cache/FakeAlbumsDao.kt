package com.cme.clef.data.cache

import com.cme.clef.data.util.CacheState
import com.cme.clef.data.remote.generateFakeAlbumsList
import com.cme.clef.data.cache.dao.IAlbumsDao
import com.cme.clef.data.cache.entity.MusicAlbumEntity
import com.cme.clef.data.remote.model.mapToEntity


class FakeAlbumsDao(var state: CacheState = CacheState.Empty) : IAlbumsDao {

    private var albumsList = mutableListOf<MusicAlbumEntity>()

    private fun initializeData() {
        when (state) {
            CacheState.WithFullData -> {

                generateFakeAlbumsList().feed.results.mapToEntity().toMutableList().forEachIndexed { i, musicAlbumEntity ->
                    albumsList.add(musicAlbumEntity.apply { isFavorite =  i % 2 == 0})
                }

            }
            CacheState.WithHalfData -> {
                    val albums = generateFakeAlbumsList().feed.results.mapToEntity().toMutableList()
                    val firstHalf = albums.take(albums.size / 2)
                    albumsList = firstHalf.toMutableList()
            }
            CacheState.WithOldData -> {
                albumsList.add(MusicAlbumEntity().apply { id = "234234" })

            }
            else -> {}
        }
    }

    override fun insertMusicAlbums(albums: List<MusicAlbumEntity>) {
        albumsList.addAll(albums)
    }

    override fun getMusicAlbums(): List<MusicAlbumEntity> {
        initializeData()
        return albumsList
    }

    override fun getFavoriteAlbums() : List<MusicAlbumEntity> {
        initializeData()
        return albumsList.filter { it.isFavorite }
    }

    override fun setFavoriteAlbum(albumId: String, isFavorite: Boolean) {
        albumsList.find { it.id == albumId }?.isFavorite = isFavorite
    }

    override fun clearAllAlbums() {
        albumsList.clear()
    }
}

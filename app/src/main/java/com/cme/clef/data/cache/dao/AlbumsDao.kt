package com.cme.clef.data.cache.dao

import com.cme.clef.data.cache.entity.MusicAlbumEntity
import com.cme.clef.data.cache.util.getDefaultRealmInstance
import io.realm.Realm



interface IAlbumsDao {

     fun insertMusicAlbums(albums: List<MusicAlbumEntity>)
     fun getMusicAlbums(): List<MusicAlbumEntity>
     fun getFavoriteAlbums(): List<MusicAlbumEntity>
     fun setFavoriteAlbum(albumId: String, isFavorite: Boolean)
     fun clearAllAlbums()
}

class AlbumsDao(): IAlbumsDao {



    override  fun insertMusicAlbums(albums: List<MusicAlbumEntity>) {

            getDefaultRealmInstance { realm ->
                realm.executeTransaction { transaction ->
                    transaction.insert(albums)
                }

            }

    }

    override  fun getMusicAlbums(): List<MusicAlbumEntity> =   getDefaultRealmInstance { realm ->
            realm.where(MusicAlbumEntity::class.java).findAll().toList()
        }



    override fun getFavoriteAlbums(): List<MusicAlbumEntity> = getDefaultRealmInstance { realm ->
        realm.where(MusicAlbumEntity::class.java).equalTo("isFavorite", true).findAll()
    }



    override fun setFavoriteAlbum(albumId: String, isFavorite: Boolean) {

        getDefaultRealmInstance { realm ->
            realm.beginTransaction()

            val album =  realm.where(MusicAlbumEntity::class.java).equalTo("id", albumId).findFirst()

            album?.apply {
                this.isFavorite = isFavorite
            }

            realm.commitTransaction()

        }


    }

    override fun clearAllAlbums() {

        getDefaultRealmInstance { realm ->
            realm.executeTransaction { transaction ->
                transaction.where(MusicAlbumEntity::class.java).findAll().deleteAllFromRealm()
            }
        }

    }
}
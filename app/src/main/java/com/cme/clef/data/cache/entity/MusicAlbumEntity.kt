package com.cme.clef.data.cache.entity

import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.toMMMddDateFormat
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.UUID

@RealmClass
open class MusicAlbumEntity: RealmObject()  {

    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var name = ""
    var artistName = ""
    var releaseDate = ""
    var imageUrl = ""
    var albumLink = ""
    var isFavorite = false
    var genres: RealmList<String> = RealmList()

}


fun List<MusicAlbumEntity>.mapToDomain() = map {

    it.run {
        AlbumInfo(
           id,name,artistName,releaseDate.toMMMddDateFormat(),imageUrl,albumLink,isFavorite, genres.toList()
        )
    }

}
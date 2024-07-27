package com.cme.clef.data.remote.model


import com.cme.clef.data.cache.entity.MusicAlbumEntity
import com.cme.clef.data.remote.model.Result
import com.google.gson.annotations.SerializedName
import io.realm.RealmList

data class Result(
    @SerializedName("artistId")
    val artistId: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artistUrl")
    val artistUrl: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("url")
    val url: String
)


fun List<Result>.mapToEntity() = map { album -> MusicAlbumEntity().apply {
   val albumGenres =  RealmList<String>()
    albumGenres.addAll(album.genres.map { it.name }.toList())
    id = album.id
    name = album.name
    artistName = album.artistName
    releaseDate = album.releaseDate
    imageUrl = album.artworkUrl100
    albumLink = album.url
    genres = albumGenres
} }
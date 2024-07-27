package com.cme.clef.data.domain


data class AlbumInfo(
    var id: String,
    var name: String,
    var artistName: String,
    var releaseDate: String,
    var imageUrl: String,
    var albumLink: String,
    var isFavorite: Boolean,
    var genres: List<String>
)
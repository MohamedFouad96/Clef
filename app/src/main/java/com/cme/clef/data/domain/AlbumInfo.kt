package com.cme.clef.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumInfo(
    var id: String,
    var name: String,
    var artistName: String,
    var releaseDate: String,
    var imageUrl: String,
    var albumLink: String,
    var isFavorite: Boolean,
    var genres: List<String>
): Parcelable {

    fun changeImageUrlResolution(newWidth: Int, newHeight: Int): String {
        val regex = Regex("""(\d+)x(\d+)bb\.jpg""")
        val newResolution = "${newWidth}x${newHeight}bb.jpg"
        return regex.replace(imageUrl, newResolution)
    }


    fun joinGenresWithSeparator(separator: String = " | "): String {
        return genres.joinToString(separator)
    }
}
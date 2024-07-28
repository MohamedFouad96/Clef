package com.cme.clef.ui.main.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.Resource
import com.cme.clef.repo.IHomeRepository
import com.cme.clef.ui.common.BaseViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(private val repo: IHomeRepository): BaseViewModel() {

    private val _isFavorite = mutableStateOf<Boolean?>(null)
    val isFavorite: State<Boolean?> = _isFavorite


    fun changeAlbumFavorite(albumId: String, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setFavoriteAlbum(albumId, isFavorite)
            _isFavorite.value = isFavorite
        }
    }

}
package com.cme.clef.ui.main.tabs.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.Resource
import com.cme.clef.repo.IFavoritesRepository
import com.cme.clef.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repo: IFavoritesRepository): BaseViewModel() {


    private val _musicAlbums = mutableStateOf<List<AlbumInfo>?>(null)
    val musicAlbums: State<List<AlbumInfo>?> = _musicAlbums

    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState


    fun fetchFavoriteMusicAlbums() {

        viewModelScope.launch(Dispatchers.IO) {

            repo.getFavoriteMusicAlbums().collectLatest {
                showLoading.value = (it.status == Resource.Status.LOADING)

                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        it.data?.let { albums ->
                                _musicAlbums.value = albums
                        }
                    }
                    Resource.Status.ERROR -> {
                        it.message?.let { message ->
                            if (!_musicAlbums.value.isNullOrEmpty())
                                showSnackBarError.emit(message)
                           else
                               _errorState.value = message
                        }
                    }
                    else -> {}
                }
            }
        }
    }



}
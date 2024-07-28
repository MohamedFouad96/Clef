package com.cme.clef.ui.main.tabs.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.data.util.Resource
import com.cme.clef.repo.IHomeRepository
import com.cme.clef.ui.common.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: IHomeRepository): BaseViewModel() {


    private val _musicAlbums = mutableStateOf<List<AlbumInfo>?>(null)
    val musicAlbums: State<List<AlbumInfo>?> = _musicAlbums

    private val _errorState = mutableStateOf<String?>(null)
    val errorState: State<String?> = _errorState


    fun fetchMusicAlbums(coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main) {

        viewModelScope.launch(coroutineDispatcher) {

            repo.getMusicAlbums().collectLatest {
                showLoading.value = (it.status == Resource.Status.LOADING && _musicAlbums.value.isNullOrEmpty())

                when (it.status) {
                    Resource.Status.SUCCESS, Resource.Status.LOADING -> {
                        it.data?.let { albums ->
                            if (albums.isNotEmpty() && albums != _musicAlbums.value)
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

                }
            }
        }
    }



}
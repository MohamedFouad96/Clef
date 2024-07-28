package com.cme.clef.ui.main.tabs.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.common.components.DefaultContainer
import com.cme.clef.ui.common.components.ErrorState
import com.cme.clef.ui.main.tabs.home.components.HomePageTopBar
import com.cme.clef.ui.main.tabs.home.components.albums_grid.MusicAlbumGridList
import com.cme.clef.ui.theme.ClefTheme
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navigateToAlbumDetailsScreen: (album: AlbumInfo) -> Unit) {

    val viewModel = koinViewModel<HomeViewModel>()
    val errorState by rememberSaveable { viewModel.errorState }
    val albums by rememberSaveable { viewModel.musicAlbums }


    LaunchedEffect(key1 = true) {
        viewModel.fetchMusicAlbums()
    }

    Scaffold {

        Column(modifier = Modifier.fillMaxSize()) {
            HomePageTopBar()
            Spacer(modifier = Modifier.height(21.dp))

            DefaultContainer(viewModel = viewModel) {

                albums?.let {
                    if (it.isNotEmpty()) {
                        MusicAlbumGridList(it) { album ->
                            navigateToAlbumDetailsScreen.invoke(album)
                        }
                    }
                }


                errorState?.let {
                    if (albums == null) {
                        ErrorState {
                            viewModel.fetchMusicAlbums()
                        }
                    }
                }

            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ClefTheme {
        HomePage() {}
    }
}
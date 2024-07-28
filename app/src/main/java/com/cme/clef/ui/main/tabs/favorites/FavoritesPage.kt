package com.cme.clef.ui.main.tabs.favorites


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cme.clef.R
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.common.components.DefaultContainer
import com.cme.clef.ui.common.components.EmptyState
import com.cme.clef.ui.main.tabs.favorites.components.FavoritesTopBar
import com.cme.clef.ui.main.tabs.favorites.components.favorites_list.FavoriteMusicAlbumList
import com.cme.clef.ui.theme.ClefTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesPage(navigateToAlbumDetailsScreen: (album: AlbumInfo) -> Unit) {

    val viewModel = koinViewModel<FavoritesViewModel>()
    val albums by remember { viewModel.musicAlbums }

    LaunchedEffect(key1 = true) {
        viewModel.fetchFavoriteMusicAlbums()
    }

    Scaffold(
        topBar = { FavoritesTopBar() },
    ) { innerPadding ->

        DefaultContainer(modifier = Modifier
            .padding(innerPadding), viewModel = viewModel) {

            albums?.let {

                if (it.isNotEmpty()) {
                    FavoriteMusicAlbumList(
                        albums = it,
                        onClick = { album -> navigateToAlbumDetailsScreen.invoke(album) })
                } else {
                    EmptyState(
                        title = stringResource(id = R.string.there_is_no_albums_yet),
                        message = stringResource(id = R.string.find_your_favorite_album),
                        image = painterResource(
                            id = R.drawable.ic_empty_favorite_list
                        )
                    )
                }

            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesPagePreview() {
    ClefTheme {
        FavoritesPage { }
    }
}
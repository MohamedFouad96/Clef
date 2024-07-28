package com.cme.clef.ui.main.tabs.home.components.albums_grid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.theme.ClefTheme


@Composable
fun MusicAlbumGridList(
    albums: List<AlbumInfo>,
    onClick: (AlbumInfo) -> Unit,
) {

    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 24.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(albums, {it.id} ) { album ->
            MusicAlbumItem(album = album, modifier = Modifier.clickable {
                onClick.invoke(album)
            })
        }

    }


}


@Preview(showBackground = true)
@Composable
fun MusicAlbumGridListPreview() {
    ClefTheme {
        MusicAlbumGridList(listOf()) {}
    }
}
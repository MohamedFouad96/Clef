package com.cme.clef.ui.main.tabs.favorites.components.favorites_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cme.clef.data.domain.AlbumInfo
import com.cme.clef.ui.theme.ClefTheme


@Composable
fun FavoriteMusicAlbumList(
    albums: List<AlbumInfo>,
    onClick: (AlbumInfo) -> Unit,
) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(albums, {it.id}) { album ->
            FavoriteMusicAlbumItem(album = album, modifier = Modifier.clickable {
                onClick.invoke(album)
            })
        }

    }


}


@Preview(showBackground = true)
@Composable
fun FavoriteMusicAlbumListPreview() {
    ClefTheme {
        FavoriteMusicAlbumList(listOf()) {}
    }
}